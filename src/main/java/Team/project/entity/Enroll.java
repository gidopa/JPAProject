package Team.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class Enroll {

    @Id
    @GeneratedValue
    @Column(name = "enroll_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    private GradeType gradeType;

    @OneToOne(mappedBy = "enroll")
    private Assessment assessment;

    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }

    public Enroll(Student student, Course course, GradeType gradeType) {
        this.student = student;
        this.course = course;
        this.gradeType = gradeType;
    }

    public void changeAssessment(Assessment assessment){
        this.assessment = assessment;
    }


    public static void assignGrade(List<Enroll> enrolls) {
        // 모든 Enroll의 Assessment 리스트를 가져옴
        List<Assessment> assessments = new ArrayList<>(enrolls.stream()
                .map(Enroll::getAssessment)
                .toList());

        // 각 Assessment의 totalScore 계산
        assessments.forEach(Assessment::calculateTotalScore);

        // totalScore를 기준으로 내림차순 정렬
        assessments.sort(Comparator.comparingDouble(Assessment::getTotalScore).reversed());

        int totalStudents = assessments.size();

        // 상위 10% A+
        int aPlusCount = (int) Math.ceil(totalStudents * 0.1);
        // 상위 20% A
        int aCount = (int) Math.ceil(totalStudents * 0.2);
        // 상위 30% B+
        int bPlusCount = (int) Math.ceil(totalStudents * 0.3);
        // 상위 40% B
        int bCount = (int) Math.ceil(totalStudents * 0.4);
        // 상위 50% C+
        int cPlusCount = (int) Math.ceil(totalStudents * 0.5);
        // 상위 60% C
        int cCount = (int) Math.ceil(totalStudents * 0.6);
        // 상위 70% D+
        int dPlusCount = (int) Math.ceil(totalStudents * 0.7);
        // 상위 80% D
        int dCount = (int) Math.ceil(totalStudents * 0.8);

        int index = 0;
        for (; index < aPlusCount; index++) {
            assessments.get(index).getEnroll().setGradeType(GradeType.APLUS);
        }
        for (; index < aCount; index++) {
            assessments.get(index).getEnroll().setGradeType(GradeType.AZERO);
        }
        for (; index < bPlusCount; index++) {
            assessments.get(index).getEnroll().setGradeType(GradeType.BPLUS);
        }
        for (; index < bCount; index++) {
            assessments.get(index).getEnroll().setGradeType(GradeType.BZERO);
        }
        for (; index < cPlusCount; index++) {
            assessments.get(index).getEnroll().setGradeType(GradeType.CPLUS);
        }
        for (; index < cCount; index++) {
            assessments.get(index).getEnroll().setGradeType(GradeType.CZERO);
        }
        for (; index < dPlusCount; index++) {
            assessments.get(index).getEnroll().setGradeType(GradeType.DPLUS);
        }
        for (; index < dCount; index++) {
            assessments.get(index).getEnroll().setGradeType(GradeType.DZERO);
        }
        for (; index < totalStudents; index++) {
            assessments.get(index).getEnroll().setGradeType(GradeType.F);
        }


    }


}
