package Team.project.dto.student;

import Team.project.entity.StudentStatus;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentHistoryDto {

    private Long studentId;
    private StudentStatus oldStatus;
    private StudentStatus newStatus;
    @NotNull
    private String reason;
    @NotNull
    @FutureOrPresent
    private LocalDate startDate;
    private LocalDate updatedDate;

    @QueryProjection
    public StudentHistoryDto(Long studentId, StudentStatus oldStatus, StudentStatus newStatus, String reason, LocalDate startDate, LocalDate updatedDate) {
        this.studentId = studentId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.reason = reason;
        this.startDate = startDate;
        this.updatedDate = updatedDate;
    }
}
