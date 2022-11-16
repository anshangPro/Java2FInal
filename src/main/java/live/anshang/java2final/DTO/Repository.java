package live.anshang.java2final.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Repository {

    @Id
    @GeneratedValue
    private long id;

    String name;

    @OneToMany
    private List<Commit> commits = new ArrayList<>();

    @OneToMany
    private List<Release> releases = new ArrayList<>();

    @OneToMany
    private List<Issue> issues = new ArrayList<>();

    @OneToMany
    private List<Developer> developers = new ArrayList<>();

}
