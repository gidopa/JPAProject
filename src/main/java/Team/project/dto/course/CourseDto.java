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
    private double midTermWeight; // 중간고사 가중치
    private double finalTermWeight; // 기말고사 가중치
    private double reportWeight; // 레포트 가중치
    private String category;    // 전공 교양 여부
    private String filePath; // 파일 경로 필드 추가

    /*@QueryProjection
    public CourseDto(Long courseId, String courseName, String professor, int credit, int semester, int year) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.professor = professor;
        this.credit = credit;
        this.semester = semester;
        this.year = year;
    }*/

    @QueryProjection
    public CourseDto(Long courseId, String courseName, String professor, int credit, int semester, int year, double midTermWeight, double finalTermWeight, double reportWeight, String category, String filePath) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.professor = professor;
        this.credit = credit;
        this.semester = semester;
        this.year = year;
        this.midTermWeight = midTermWeight;
        this.finalTermWeight = finalTermWeight;
        this.reportWeight = reportWeight;
        this.category = category;
        this.filePath = filePath;
    }
}
