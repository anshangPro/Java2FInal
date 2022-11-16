package live.anshang.java2final.Controller;

import io.swagger.annotations.Api;
import live.anshang.java2final.DTO.Developer;
import live.anshang.java2final.DTO.Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Api
@EnableAsync
@Controller
@RequestMapping("/api/data")
public class DataController {

    @GetMapping("repo")
    public List<Repository> getRepoList() {
        return null;
    }

    @GetMapping("developer")
    public List<Developer> getDeveloperList() {
        return null;
    }

}
