package Team.project.service.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.grade.GradeEditDto;
import Team.project.entity.Enroll;
import Team.project.repository.Enroll.EnrollRepository;
import Team.project.repository.grade.GradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class GradeServiceImpl implements GradeService{

    private final GradeRepository gradeRepository;
    private final EnrollRepository enrollRepository;


    @Override
    public List<GradeDto> findAllGradeByStudentId(Long studentId) {
        return gradeRepository.findAllGradeByStudentId(studentId);
    }

    @Override
    public List<GradeDto> findAllGradeByCourseId(Long courseId) {
        return gradeRepository.findAllGradeByCourseId(courseId);
    }

    @Transactional
    @Override
    public void assignGrade(GradeEditDto editDto) {
        gradeRepository.assignGrade(editDto);
        List<Enroll> findEnrollList = enrollRepository.findByCourseId(editDto.getCourseId());
        for (Enroll enroll : findEnrollList) {
            System.out.println("enroll.getStudent().get = " + enroll.getStudent().getName());
            System.out.println("enroll = " + enroll.getAssessment().getMidTermScore());
        }
        Enroll.assignGrade(findEnrollList);
    }
}
