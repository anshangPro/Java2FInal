package live.anshang.java2final.DAO;

import live.anshang.java2final.DTO.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRepository extends JpaRepository<Repository, Long> {
    Repository findRepositoryByName(String name);
}
