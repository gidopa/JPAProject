package Team.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue
    @Column(name = "grade_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enroll_id")
    private Enroll enroll;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @Enumerated(EnumType.STRING)
    private GradeType gradeType; // 최종 등급

    /*public void assignGrade(List<Enroll> enrolls) {
        // 상대 평가를 위한 로직 구현
        // 상위 10%는 A+, 상위 20%는 A, 30% B+
        List<Assessment> assessments = enrolls.stream()
                .map(Enroll::getAssessment)
                .sorted(Comparator.comparingDouble(Assessment::calculateTotalScore).reversed())
                .toList();
        int totalStudents = assessments.size();
        for(int i=0;i<totalStudents * 0.1;i++){
            enrolls.get(i).getGrade().setGradeType(GradeType.APLUS);


        }
    }*/
}