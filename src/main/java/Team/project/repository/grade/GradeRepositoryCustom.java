package Team.project.repository.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.grade.GradeEditDto;

import java.util.List;

public interface GradeRepositoryCustom {

    List<GradeDto> findAllGradeByStudentId(Long studentId);
    List<GradeDto> findAllGradeByCourseId(Long courseId);

    void assignGrade(GradeEditDto editDto);
}
