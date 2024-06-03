package Team.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(unique = true)
    private Long hakbun;
    private String name;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @OneToMany(mappedBy = "student")
    private List<Enroll> enrollments;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "student")
    private Credit credit;

    @Embedded
    private SemesterInfo semesterInfo;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    public Student(String name, Major major, Long hakbun,  String password) {
        this.hakbun = hakbun;
        this.name = name;
        this.password = password;
        this.major = major;
    }
}


