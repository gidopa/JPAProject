package Team.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long id;
    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    /*@OneToMany(mappedBy = "course")
    private List<Enroll> enrollments;  // 추가: Course와 Enroll의 관계*/

    private int credit; // 몇 학점 짜리 강의인지

    @Enumerated(EnumType.STRING)
    private Category category; // 전공 / 교양
    @Embedded
    private SemesterInfo semesterInfo;


}
