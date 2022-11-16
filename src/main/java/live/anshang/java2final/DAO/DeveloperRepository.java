package live.anshang.java2final.DAO;

import live.anshang.java2final.DTO.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
