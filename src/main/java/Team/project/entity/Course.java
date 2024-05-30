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

    @OneToMany(mappedBy = "course")
    private List<Enroll> enrollments;  // 추가: Course와 Enroll의 관계

    private double midTermWeight; // 중간고사 가중치
    private double finalTermWeight; // 기말고사 가중치
    private double reportWeight; // 레포트 가중치

    private int credit; // 몇 학점 짜리 강의인지

    @Enumerated(EnumType.STRING)
    private Category category; // 전공 / 교양
    @Embedded
    private SemesterInfo semesterInfo;

    public Course(String courseName, Professor professor, double midTermWeight, double finalTermWeight, double reportWeight, int credit, Category category) {
        this.courseName = courseName;
        this.professor = professor;
        this.midTermWeight = midTermWeight;
        this.finalTermWeight = finalTermWeight;
        this.reportWeight = reportWeight;
        this.credit = credit;
        this.category = category;
    }
}
