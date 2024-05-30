package Team.project.service.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.entity.Enroll;

import java.util.List;

public interface GradeService {

    List<GradeDto> findAllGradeByStudentId(Long studentId);
    List<GradeDto> findAllGradeByCourseId(Long studentId);
}
