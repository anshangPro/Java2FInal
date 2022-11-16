package live.anshang.java2final.DTO;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Developer {

    @Id
    @GeneratedValue
    private long id;

    String name;

    @ManyToMany
    private List<Commit> commits = new ArrayList<>();

}
