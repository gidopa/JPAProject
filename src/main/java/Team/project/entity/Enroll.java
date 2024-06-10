package Team.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        for (Assessment assessment : assessments) {
            System.out.println("assessment = " + assessment);
        }
        int totalStudents = assessments.size();

        // 상위 10% A+
        int aPlusCount = (int)Math.ceil((totalStudents * 0.1));
        System.out.println("aPlusCount = " + aPlusCount);
        // 상위 20%는 A
        int aCount = (int) Math.ceil((totalStudents * 0.2));
        System.out.println("aCount = " + aCount);
        // 상위 30%는 B+
        int bPlusCount = (int)Math.ceil((totalStudents * 0.3));
        System.out.println("bPlusCount = " + bPlusCount);
        int bCount = (int) Math.ceil((totalStudents * 0.4));
        System.out.println("bCount = " + bCount);
        int cPlusCount = (int) Math.ceil((totalStudents * 0.5));
        System.out.println("cPlusCount = " + cPlusCount);
        int cCount = (int) Math.ceil((totalStudents * 0.6));
        System.out.println("cCount = " + cCount);
        int dPlusCount = (int) Math.ceil((totalStudents * 0.7));
        System.out.println("dPlusCount = " + dPlusCount);
        int dCount = (int) Math.ceil((totalStudents * 0.8));
        System.out.println("dCount = " + dCount);


        int index = 0;
        for(;index<aPlusCount;index++){
            enrolls.get(index).setGradeType(GradeType.APLUS);
        }
        for(;index<aCount;index++){
            enrolls.get(index).setGradeType(GradeType.AZERO);
        }
        for(;index<bPlusCount;index++){
            enrolls.get(index).setGradeType(GradeType.BPLUS);
        }
        for(;index<bCount;index++){
            enrolls.get(index).setGradeType(GradeType.BZERO);
        }
        for(;index<cPlusCount;index++){
            enrolls.get(index).setGradeType(GradeType.CPLUS);
        }
        for(;index<cCount;index++){
            enrolls.get(index).setGradeType(GradeType.CZERO);
        }
        for(;index<dPlusCount;index++){
            enrolls.get(index).setGradeType(GradeType.DPLUS);
        }

        for(;index<dCount;index++){
            enrolls.get(index).setGradeType(GradeType.DZERO);
        }

        for(;index<totalStudents;index++){
            enrolls.get(index).setGradeType(GradeType.F);
        }



    }


}
