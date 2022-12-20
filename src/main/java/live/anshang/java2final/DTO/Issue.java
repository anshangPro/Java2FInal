package live.anshang.java2final.DTO;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Issue {

  @Id
  @GeneratedValue
  private long id;

  private String title;

  private String status;

  private Date createAt;

  private Date closeAt;
//
//    @OneToMany
//    private List<Comment> comments = new ArrayList<>();
}
