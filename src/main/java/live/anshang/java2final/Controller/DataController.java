package live.anshang.java2final.Controller;

import io.swagger.annotations.Api;
import live.anshang.java2final.DAO.*;
import live.anshang.java2final.DTO.Developer;
import live.anshang.java2final.DTO.Repository;
import live.anshang.java2final.DTO.RepositoryDTO;
import live.anshang.java2final.Spider.GithubSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Api
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

    @GetMapping("repo/{repo}")
    public RepositoryDTO getRepoList(@PathVariable("repo") String name) {
        Repository repo = repoRepository.findRepositoriesByName(name).get(0);
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
    public List<Pair<String, String>> getRepoList() {
        return repoRepository.findAll().stream().map(repository ->
                Pair.of(repository.getAuthor(), repository.getName())).distinct().toList();
    }

    @GetMapping("{repo}/developer")
    public List<Developer> getDeveloperList(@PathVariable("repo") String repo) {
        Repository repository = repoRepository.findRepositoriesByName(repo).get(0);
        List<Developer> developers = repository.getDevelopers();
        return developers.stream().sorted(Comparator.comparing(Developer::getCommits).reversed()).limit(9).toList();
    }

    @PutMapping("repo")
    public String addRepo(@RequestParam("author") String author,
                           @RequestParam("repo") String repo){
        Repository repository = GithubSpider.getInfoByRepoURL(author, repo);
        developerRepository.saveAll(repository.getDevelopers());
        issueRepository.saveAll(repository.getIssues());
        commitRepository.saveAll(repository.getCommits());
        releaseRepository.saveAll(repository.getReleases());
        repoRepository.save(repository);
        return "Success";
    }

}
