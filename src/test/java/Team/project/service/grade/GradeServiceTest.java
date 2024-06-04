package Team.project.service.grade;

import Team.project.dto.grade.GradeEditDto;
import Team.project.entity.Enroll;
import Team.project.entity.Grade;
import Team.project.entity.GradeType;
import Team.project.repository.Enroll.EnrollRepository;
import Team.project.repository.grade.GradeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class GradeServiceTest {

    @Autowired
    GradeService gradeService;
    @Autowired
    EnrollRepository enrollRepository;
    @Autowired
    GradeRepository gradeRepository;

    @Test
    void assignGrade() {
        List<Enroll> all = enrollRepository.findAll();
        Enroll.assignGrade(all);
        assertThat(all.get(0).getGradeType()).isEqualTo(GradeType.F);
    }

}