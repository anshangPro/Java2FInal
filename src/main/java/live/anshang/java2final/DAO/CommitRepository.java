package live.anshang.java2final.DAO;

import live.anshang.java2final.DTO.Commit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommitRepository extends JpaRepository<Commit, Long> {

}
