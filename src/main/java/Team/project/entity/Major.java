package Team.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Major {

    @Id
    @GeneratedValue
    @Column(name = "major_id")
    private String id;
    private String name;
    @OneToMany(mappedBy = "major")
    private List<Professor> professors = new ArrayList<>();
}
