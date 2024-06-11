package Team.project.repository.studentRecord;

import Team.project.dto.studentRecord.StudentHistoryDto;
import Team.project.entity.Student;

import java.util.List;

public interface StudentHistoryRepositoryCustom {

    List<StudentHistoryDto> findStudentHistories(Student entity);
}
