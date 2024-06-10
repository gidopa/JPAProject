package Team.project.dto.grade;

import Team.project.entity.Assessment;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class GradeEditDto {
    private Long studentId;
    private Long courseId;
    private String studentName;
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "100.0", inclusive = true)
    private double midTermScore; // 중간고사 점수
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "100.0", inclusive = true)
    private double finalTermScore; // 기말고사 점수
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "100.0", inclusive = true)
    private double reportScore; // 레포트 점수

    @QueryProjection
    public GradeEditDto(Long studentId,  Long courseId,String studentName, double midTermScore, double finalTermScore, double reportScore) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.midTermScore = midTermScore;
        this.finalTermScore = finalTermScore;
        this.reportScore = reportScore;
    }
}
