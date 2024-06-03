package Team.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Long id;

    @Column(unique = true)
    private Long loginId;
    private String name;

    private String password;

    @OneToMany(mappedBy = "professor")
    @JsonManagedReference
    private List<Course> courses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @Embedded
    private Address address;

    public void changeMajor(Major major){
         this.major = major;
         major.getProfessors().add(this);
     }

    public Professor(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    public Professor (Long loginId,String name, String password, Major major) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.major = major;
    }
}
