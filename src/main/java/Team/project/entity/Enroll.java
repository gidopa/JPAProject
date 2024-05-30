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

    @OneToOne(mappedBy = "enroll", cascade = CascadeType.ALL)
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
        // 상대 평가를 위한 로직 구현
        // 상위 10%는 A+, 상위 20%는 A, 30% B+
        List<Assessment> assessments = enrolls.stream()
                .map(Enroll::getAssessment)
                .sorted(Comparator.comparingDouble(Assessment::calculateTotalScore).reversed())
                .toList();
        int totalStudents = assessments.size();
        // 상위 10% A+
        int aPlusCount = (int)Math.ceil((totalStudents * 0.1));
        System.out.println("aPlusCount = " + aPlusCount);
        // 상위 20%는 A
        int aCount = (int) Math.ceil((totalStudents * 0.2));
        // 상위 30%는 B+
        int bPlusCount = (int)Math.ceil((totalStudents * 0.3));
        int bCount = (int) Math.ceil((totalStudents * 0.4));
        int cPlusCount = (int) Math.ceil((totalStudents * 0.5));
        int cCount = (int) Math.ceil((totalStudents * 0.6));
        int dPlusCount = (int) Math.ceil((totalStudents * 0.7));
        int dCount = (int) Math.ceil((totalStudents * 0.8));


        int index = 0;
        for(;index<aPlusCount;index++){
            assessments.get(index).getEnroll().setGradeType(GradeType.APLUS);
            System.out.println("assessments.get(index) = " + assessments.get(index));
            
        }
        for(;index<aCount;index++){
            assessments.get(index).getEnroll().setGradeType(GradeType.AZERO);
            System.out.println("index = " + index);
            System.out.println("assessments.get(index) = " + assessments.get(index));
        }
        for(;index<bPlusCount;index++){
            assessments.get(index).getEnroll().setGradeType(GradeType.BPLUS);
            System.out.println("index = " + index);
            System.out.println("assessments.get(index) = " + assessments.get(index));
        }
        for(;index<bCount;index++){
            assessments.get(index).getEnroll().setGradeType(GradeType.BZERO);
            System.out.println("index = " + index);
            System.out.println("assessments.get(index) = " + assessments.get(index));
        }
        for(;index<cPlusCount;index++){
            assessments.get(index).getEnroll().setGradeType(GradeType.CPLUS);
            System.out.println("index = " + index);
            System.out.println("assessments.get(index) = " + assessments.get(index));
        }
        for(;index<cCount;index++){
            assessments.get(index).getEnroll().setGradeType(GradeType.CZERO);
            System.out.println("index = " + index);
            System.out.println("assessments.get(index) = " + assessments.get(index));
        }
        for(;index<dPlusCount;index++){
            assessments.get(index).getEnroll().setGradeType(GradeType.DPLUS);
            System.out.println("index = " + index);
            System.out.println("assessments.get(index) = " + assessments.get(index));
        }

        for(;index<dCount;index++){
            System.out.println("index = " + index);
            assessments.get(index).getEnroll().setGradeType(GradeType.DZERO);
        }

        for(;index<totalStudents;index++){
            assessments.get(index).getEnroll().setGradeType(GradeType.F);
        }



    }


}
