package Team.project.repository.Assessment;

import Team.project.dto.grade.GradeEditDto;

public interface AssessmentRepositoryCustom {
    GradeEditDto findCourseGradeByCourseId(Long courseId, Long studentId);
}
