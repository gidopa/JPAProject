package Team.project.repository.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.grade.GradeEditDto;
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

    @Test
    void 강의별_성적_조회() {
        long courseIdJava = 1L;
        long courseIdPython  = 2L;
        List<GradeDto> gradeListByCourseJava = gradeRepository.findAllGradeByCourseId(courseIdJava);
        List<GradeDto> gradeListByCoursePython = gradeRepository.findAllGradeByCourseId(courseIdPython);
        assertThat(gradeListByCourseJava.size()).isEqualTo(10);
        assertThat(gradeListByCoursePython.size()).isEqualTo(1);
        assertThat(gradeListByCourseJava).extracting(GradeDto::getCourseName).allSatisfy(
                s -> {
                    assertThat(s).isEqualTo("JAVA");
                }
        );
        assertThat(gradeListByCourseJava).extracting(GradeDto::getCourseName).containsOnly("JAVA");
    }

    @Test
    void 성적_부여() {
        long courseIdJava = 1L;
        GradeEditDto johnDoe = new GradeEditDto(1L, 1L, "John Doe", 95, 85, 100);
        gradeRepository.assignGrade(johnDoe);
        List<GradeDto> list = gradeRepository.findAllGradeByCourseId(courseIdJava);
        GradeDto johnDto = list.get(0);
        assertThat(johnDto.getMidTermScore()).isEqualTo(95);
        assertThat(johnDto.getFinalTermScore()).isEqualTo(85);
        assertThat(johnDto.getReportScore()).isEqualTo(100);
    }




}