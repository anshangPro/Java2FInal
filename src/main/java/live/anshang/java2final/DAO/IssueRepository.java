package live.anshang.java2final.DAO;

import live.anshang.java2final.DTO.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}
