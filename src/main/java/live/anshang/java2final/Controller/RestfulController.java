package live.anshang.java2final.Controller;

import io.swagger.annotations.Api;
import live.anshang.java2final.DAO.*;
import live.anshang.java2final.DTO.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Api
@EnableAsync
@RestController
@RequestMapping("/api/restful")
public class RestfulController {
    private final RepoRepository repoRepository;
    private final CommitRepository commitRepository;
    private final DeveloperRepository developerRepository;
    private final ReleaseRepository releaseRepository;
    private final IssueRepository issueRepository;

    public RestfulController(RepoRepository repoRepository, CommitRepository commitRepository,
                          DeveloperRepository developerRepository, ReleaseRepository releaseRepository,
                          IssueRepository issueRepository) {
        this.repoRepository = repoRepository;
        this.commitRepository = commitRepository;
        this.developerRepository = developerRepository;
        this.releaseRepository = releaseRepository;
        this.issueRepository = issueRepository;
    }

    @GetMapping("{author}/{repo}")
    public Repository getRepo(@PathVariable("repo") String name, @PathVariable("author") String author) {
        return repoRepository.findRepositoriesByNameAndAuthor(name, author).get(0);
    }


    @GetMapping("{author}/{repo}/developer")
    public List<Developer> getDeveloperList(@PathVariable("repo") String repo, @PathVariable("author") String author) {
        Repository repository = repoRepository.findRepositoriesByNameAndAuthor(repo, author).get(0);
        return repository.getDevelopers();
    }

    @GetMapping("{author}/{repo}/issue")
    public List<Issue> getIssueList(@PathVariable("repo") String repo, @PathVariable("author") String author) {
        Repository repository = repoRepository.findRepositoriesByNameAndAuthor(repo, author).get(0);
        return repository.getIssues();
    }

    @GetMapping("{author}/{repo}/release")
    public List<Release> getReleaseList(@PathVariable("repo") String repo, @PathVariable("author") String author) {
        Repository repository = repoRepository.findRepositoriesByNameAndAuthor(repo, author).get(0);
        return repository.getReleases();
    }

    @GetMapping("{author}/{repo}/commit")
    public List<Commit> getCommitList(@PathVariable("repo") String repo, @PathVariable("author") String author) {
        Repository repository = repoRepository.findRepositoriesByNameAndAuthor(repo, author).get(0);
        return repository.getCommits();
    }

}
