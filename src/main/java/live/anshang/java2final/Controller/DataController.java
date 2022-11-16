package live.anshang.java2final.Controller;

import io.swagger.annotations.Api;
import live.anshang.java2final.DAO.*;
import live.anshang.java2final.DTO.Developer;
import live.anshang.java2final.DTO.Repository;
import live.anshang.java2final.Spider.GithubSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<Repository> getRepoList(@PathVariable("repo") String name) {
        return repoRepository.findRepositoriesByName(name);
    }

    @GetMapping("repoAll")
    public List<Repository> getRepoList() {
        return repoRepository.findAllByNameLike("");
    }

    @GetMapping("developer")
    public List<Developer> getDeveloperList() {
        return null;
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
