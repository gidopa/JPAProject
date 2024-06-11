package Team.project.service.Assessment;

import Team.project.dto.grade.GradeEditDto;
import Team.project.entity.Assessment;

public interface AssessmentService {

    GradeEditDto findCourseGradeByCourseId(Long courseId, Long studentId);

    Assessment findAssessmentByEnroll_StudentAndEnroll_Course(Long studentId, Long courseId);
}
