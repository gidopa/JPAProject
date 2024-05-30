package Team.project.dto.grade;

import Team.project.entity.Assessment;
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
    private String studentName;
    private String major;
    private Long courseId;
    private String courseName;
    private double midTermScore;
    private double finalTermScore;
    private double reportScore;
    private GradeType gradeType;

    @QueryProjection
    public GradeDto(String studentId, String major, Long courseId,String courseName, double midTerm, double finalTerm, double report, GradeType gradeType) {
        this.studentName = studentId;
        this.major = major;
        this.courseId = courseId;
        this.courseName = courseName;
        this.midTermScore = midTerm;
        this.finalTermScore = finalTerm;
        this.reportScore = report;
        this.gradeType = gradeType;
    }
}
