package Team.project.service.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.entity.Enroll;
import Team.project.repository.grade.GradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class GradeServiceImpl implements GradeService{

    private final GradeRepository gradeRepository;


    @Override
    public List<GradeDto> findAllGradeByStudentId(Long studentId) {
        return gradeRepository.findAllGradeByStudentId(studentId);
    }

    @Override
    public List<GradeDto> findAllGradeByCourseId(Long courseId) {
        return gradeRepository.findAllGradeByCourseId(courseId);
    }
}
