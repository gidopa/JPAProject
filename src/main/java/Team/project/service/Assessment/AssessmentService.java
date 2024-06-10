package Team.project.service.Assessment;

import Team.project.dto.grade.GradeEditDto;

public interface AssessmentService {

    GradeEditDto findCourseGradeByCourseId(Long courseId, Long studentId);
}
