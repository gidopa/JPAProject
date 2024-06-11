package Team.project.service.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.grade.GradeEditDto;
import Team.project.entity.Enroll;
import Team.project.entity.Grade;

import java.util.List;

public interface GradeService {

    List<GradeDto> findAllGradeByStudentId(Long studentId);
    List<GradeDto> findAllGradeByCourseId(Long studentId);

    void assignGrade(GradeEditDto editDto);

}
