package Team.project.repository.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.entity.*;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GradeRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    GradeRepository gradeRepository;


    @Test
    void 학생별_성적_조회() {
        long studentId = 1L;
        List<GradeDto> gradeListByStudent = gradeRepository.findAllGradeByStudentId(studentId);
        assertThat(gradeListByStudent.size()).isEqualTo(2);
        assertThat(gradeListByStudent).extracting(gradeDto -> gradeDto.getCourseName())
                .containsExactly("JAVA", "Python");
    }


}