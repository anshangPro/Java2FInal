package live.anshang.java2final.DAO;

import live.anshang.java2final.DTO.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoRepository extends JpaRepository<Repository, Long> {

  List<Repository> findRepositoriesByName(String name);

  List<Repository> findRepositoriesByNameAndAuthor(String name, String author);

  Repository getRepositoryByName(String name);
}
