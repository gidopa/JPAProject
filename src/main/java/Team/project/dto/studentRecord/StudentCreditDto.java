package Team.project.dto.studentRecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreditDto {

    private Long student_id;
    private Long credit_id;
    private Float credit;
}
