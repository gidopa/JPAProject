package Team.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue
    @Column(name = "professor_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @Embedded
    private Address address;
}
