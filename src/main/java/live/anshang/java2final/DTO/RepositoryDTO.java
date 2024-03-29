package live.anshang.java2final.DTO;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RepositoryDTO {

  String name;
  String author;
  private long id;
  private int commits;

  private int releases;

  private int issues;

  private int developers;

}
