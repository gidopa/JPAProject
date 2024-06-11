package Team.project.dto.course;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto {
    private Long courseId;
    private String courseName;
    private String professor;
    private int credit;
    private int semester;
    private int year;

    @QueryProjection
    public CourseDto(Long courseId, String courseName, String professor, int credit, int semester, int year) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.professor = professor;
        this.credit = credit;
        this.semester = semester;
        this.year = year;
    }
}
