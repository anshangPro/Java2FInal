package live.anshang.java2final.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue
    private long id;

    private String status;

    private Date createAt;

    private Date closeAt;
}
