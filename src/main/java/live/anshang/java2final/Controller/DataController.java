package live.anshang.java2final.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import live.anshang.java2final.DAO.*;
import live.anshang.java2final.DTO.*;
import live.anshang.java2final.Spider.GithubSpider;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Api(tags = "数据库相关交互")
@EnableAsync
@RestController
@RequestMapping("/api/data")
public class DataController {

  private final RepoRepository repoRepository;
  private final CommitRepository commitRepository;
  private final DeveloperRepository developerRepository;
  private final ReleaseRepository releaseRepository;
  private final IssueRepository issueRepository;

  public DataController(RepoRepository repoRepository, CommitRepository commitRepository,
      DeveloperRepository developerRepository, ReleaseRepository releaseRepository,
      IssueRepository issueRepository) {
    this.repoRepository = repoRepository;
    this.commitRepository = commitRepository;
    this.developerRepository = developerRepository;
    this.releaseRepository = releaseRepository;
    this.issueRepository = issueRepository;
  }

  @GetMapping("{author}/{repo}")
  @ApiOperation(value = "获取仓库基础信息", notes = "通过 作者 和 仓库名 获取仓库基础信息")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "author",
          value = "author",
          required = true,
          paramType = "path"
      ),
      @ApiImplicitParam(name = "repo",
          value = "repo",
          required = true,
          paramType = "path"
      )
  })
  public RepositoryDTO getRepoList(@PathVariable("repo") String name,
      @PathVariable("author") String author) {
    Repository repo = repoRepository.findRepositoriesByNameAndAuthor(name, author).get(0);
    RepositoryDTO res = new RepositoryDTO();
    res.setId(repo.getId());
    res.setAuthor(repo.getAuthor());
    res.setName(repo.getName());
    res.setIssues(repo.getIssues().size());
    res.setCommits(repo.getCommits().size());
    res.setDevelopers(repo.getDevelopers().size());
    res.setReleases(repo.getReleases().size());
    return res;
  }

  @GetMapping("repoAll")
  @ApiOperation(value = "获取仓库列表", notes = "获取一共有多少个仓库")
  public List<Pair<String, String>> getRepoList() {
    return repoRepository.findAll().stream().map(repository ->
        Pair.of(repository.getAuthor(), repository.getName())).distinct().toList();
  }

  @GetMapping("{author}/{repo}/developer")
  @ApiOperation(value = "获取仓库的开发者列表", notes = "通过 作者 和 仓库名 获取仓库的开发者列表，仅返回前9个")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "author",
          value = "author",
          required = true,
          paramType = "path"
      ),
      @ApiImplicitParam(name = "repo",
          value = "repo",
          required = true,
          paramType = "path"
      )
  })
  public List<Developer> getDeveloperList(@PathVariable("repo") String repo,
      @PathVariable("author") String author) {
    Repository repository = repoRepository.findRepositoriesByNameAndAuthor(repo, author).get(0);
    List<Developer> developers = repository.getDevelopers();
    return developers.stream().sorted(Comparator.comparing(Developer::getCommits).reversed())
        .limit(9).toList();
  }

  @GetMapping("{author}/{repo}/issue")
  @ApiOperation(value = "获取仓库issue", notes = "通过 作者 和 仓库名 获取仓库issue信息")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "author",
          value = "author",
          required = true,
          paramType = "path"
      ),
      @ApiImplicitParam(name = "repo",
          value = "repo",
          required = true,
          paramType = "path"
      )
  })
  public IssueDTO getIssueList(@PathVariable("repo") String repo,
      @PathVariable("author") String author) {
    Repository repository = repoRepository.findRepositoriesByNameAndAuthor(repo, author).get(0);
    List<Issue> issues = repository.getIssues();
    List<Issue> closed = issues.stream().filter(issue -> issue.getStatus().equals("closed"))
        .toList();
    IssueDTO res = new IssueDTO();
    res.setClosed(closed.size());
    res.setTotal(issues.size());
    res.setOpening(issues.size() - closed.size());

    List<Long> time = closed.stream()
        .map(issue -> issue.getCloseAt().getTime() - issue.getCreateAt().getTime()).toList();
    Long max = time.stream().max(Long::compareTo).orElse(-1L);
    Long min = time.stream().min(Long::compareTo).orElse(-1L);
    double avg = time.stream().mapToDouble(Long::doubleValue).average().orElse(-1);
    double variance = Math.sqrt(
        time.stream().mapToDouble(Long::doubleValue).map(t -> (t - avg) * (t - avg)).sum());

    res.setAvg(avg);
    res.setDiff(max - min);
    res.setMax(max);
    res.setMin(min);
    res.setVariance(variance);

    res.setIssueList(issues);
    return res;
  }

  @PutMapping("repo")
  @ApiOperation(value = "添加一个仓库", notes = "通过 作者 和 仓库名 获取仓库")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "author",
          value = "author",
          required = true,
          paramType = "query"
      ),
      @ApiImplicitParam(name = "repo",
          value = "repo",
          required = true,
          paramType = "query"
      )
  })
  public String addRepo(@RequestParam("author") String author,
      @RequestParam("repo") String repo) {
    Repository repository = GithubSpider.getInfoByRepoURL(author, repo);
    developerRepository.saveAll(repository.getDevelopers());
    issueRepository.saveAll(repository.getIssues());
    commitRepository.saveAll(repository.getCommits());
    releaseRepository.saveAll(repository.getReleases());
    repoRepository.save(repository);
    return "Success";
  }

}
