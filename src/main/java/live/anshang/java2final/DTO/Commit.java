package live.anshang.java2final.DTO;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Commit {
    @Id
    @GeneratedValue
    private long id;

    private String name;
}
