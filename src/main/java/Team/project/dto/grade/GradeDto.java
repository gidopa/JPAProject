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
    private Student student;
    private Course course;
    private GradeType gradeType;

    @QueryProjection
    public GradeDto(Student student, Course course, GradeType gradeType) {
        this.student = student;
        this.course = course;
        this.gradeType = gradeType;
    }
}
