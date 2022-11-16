package live.anshang.java2final.DTO;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Developer {

    @Id
    @GeneratedValue
    private long id;

    String name;

    private int commits = 0;

}
