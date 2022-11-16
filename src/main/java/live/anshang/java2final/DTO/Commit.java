package live.anshang.java2final.DTO;


import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    private Developer developer;
}
