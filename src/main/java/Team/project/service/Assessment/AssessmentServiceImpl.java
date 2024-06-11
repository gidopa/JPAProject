package Team.project.service.Assessment;

import Team.project.dto.grade.GradeEditDto;
import Team.project.entity.Assessment;
import Team.project.repository.Assessment.AssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService{

    private final AssessmentRepository assessmentRepository;

    @Override
    public GradeEditDto findCourseGradeByCourseId(Long courseId, Long studentId) {
        return assessmentRepository.findCourseGradeByCourseId(courseId,studentId);
    }

    @Override
    public Assessment findAssessmentByEnroll_StudentAndEnroll_Course(Long studentId, Long courseId) {
        return assessmentRepository.findAssessmentByEnroll_StudentAndEnroll_Course(studentId, courseId);
    }
}
