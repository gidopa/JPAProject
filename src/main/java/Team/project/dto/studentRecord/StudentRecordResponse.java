package Team.project.dto.studentRecord;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.info.InfoDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentRecordResponse {

    private List<StudentHistoryDto> studentHistoryDtos;
    private List<GradeDto> gradeDtos;
    private StudentCreditDto studentCreditDto;
    private InfoDto infoDto;

    public StudentRecordResponse(List<StudentHistoryDto> studentHistoryDtos, List<GradeDto> gradeDtos, StudentCreditDto studentCreditDto, InfoDto infoDto) {
        this.studentHistoryDtos = studentHistoryDtos;
        this.gradeDtos = gradeDtos;
        this.studentCreditDto = studentCreditDto;
        this.infoDto = infoDto;
    }

}
