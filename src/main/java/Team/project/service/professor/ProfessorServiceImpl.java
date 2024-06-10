package Team.project.service.professor;

import Team.project.entity.Course;
import Team.project.entity.Grade;
import Team.project.repository.professor.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService{

    private final ProfessorRepository professorRepository;

    @Override
    public List<Course> findGradeListBy(Long professorId) {
        return professorRepository.findGradeListBy(professorId);
    }

}
