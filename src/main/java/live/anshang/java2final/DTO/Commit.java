package live.anshang.java2final.DTO;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Commit {

  @Id
  @GeneratedValue
  private long id;

  @Column(length = 255)
  private String name;

  private Date date;

  @ManyToOne
  private Developer developer;
}
