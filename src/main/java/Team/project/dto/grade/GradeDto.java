package Team.project.dto.grade;

import Team.project.entity.Course;
import Team.project.entity.GradeType;
import Team.project.entity.Student;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class GradeDto {

    /*private Long studentId;
    private Long courseId;*/
    private Long studentId;
    private Long courseId;
    private String studentName;
    private String courseName;
    private double midTermScore; // 중간고사 점수
    private double finalTermScore; // 기말고사 점수
    private double reportScore; // 레포트 점수
    private GradeType gradeType;

    @QueryProjection
    public GradeDto(Long studentId, Long courseId,String studentName, String courseName, double midTermScore, double finalTermScore, double reportScore, GradeType gradeType) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.midTermScore = midTermScore;
        this.finalTermScore = finalTermScore;
        this.reportScore = reportScore;
        this.gradeType = gradeType;
    }
}
