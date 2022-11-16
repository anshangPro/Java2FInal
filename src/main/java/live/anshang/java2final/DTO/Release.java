package live.anshang.java2final.DTO;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Release {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private Date createAt;
}
