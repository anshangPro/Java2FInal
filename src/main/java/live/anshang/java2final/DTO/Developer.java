package live.anshang.java2final.DTO;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Developer {

  String name;
  @Id
  @GeneratedValue
  private long id;
  private int commits = 0;

  private String url;

  private String avatarUrl;

}
