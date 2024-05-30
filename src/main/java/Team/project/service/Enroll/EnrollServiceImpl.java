package Team.project.service.Enroll;

import Team.project.dto.grade.GradeDto;
import Team.project.entity.Enroll;
import Team.project.repository.Enroll.EnrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollServiceImpl implements EnrollService{

    private final EnrollRepository enrollRepository;

    @Override
    public List<Enroll> findAllGrade() {
        return enrollRepository.findAll();
    }
}
