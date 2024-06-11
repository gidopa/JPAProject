package Team.project.dto.studentRecord;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class StudentHistoryDto {

    private Long student_history_id;
    private Long student_id;
    private Long hakbun;
    private String name;
    private String old_status;
    private String new_status;
    private LocalDate updated_date;

    @QueryProjection
    public StudentHistoryDto(Long student_history_id, Long student_id, Long hakbun, String name, String old_status, String new_status, LocalDate updated_date) {
        this.student_history_id = student_history_id;
        this.student_id = student_id;
        this.hakbun = hakbun;
        this.name = name;
        this.old_status = old_status;
        this.new_status = new_status;
        this.updated_date = updated_date;
    }
}
