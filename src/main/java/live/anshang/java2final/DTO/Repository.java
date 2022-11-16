package live.anshang.java2final.DTO;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Repository {

    @Id
    @GeneratedValue
    private long id;

    String name;
    String author;

    @OneToMany
    private List<Commit> commits = new ArrayList<>();

    @OneToMany
    private List<Release> releases = new ArrayList<>();

    @OneToMany
    private List<Issue> issues = new ArrayList<>();

    @OneToMany
    private List<Developer> developers = new ArrayList<>();

}
