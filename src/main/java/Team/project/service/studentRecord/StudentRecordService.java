package Team.project.service.studentRecord;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.studentRecord.StudentCreditDto;
import Team.project.dto.studentRecord.StudentHistoryDto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface StudentRecordService {

    List<StudentHistoryDto> studentRecord(Long id, HttpSession session);

    StudentCreditDto selectCredit(Long id, List<GradeDto> gradeDtos);
}
