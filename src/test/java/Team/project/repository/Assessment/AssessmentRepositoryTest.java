package Team.project.repository.Assessment;

import Team.project.dto.grade.GradeEditDto;
import Team.project.entity.Course;
import Team.project.repository.Course.CourseRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AssessmentRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    AssessmentRepository assessmentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Test
    void form_요청시_얻는_dto() {
        // JAVA 강의 수강생 중 StudentId 가 1L인 "John Doe" 학생의 gradeEditDto
        GradeEditDto StudentGradeEditDto = assessmentRepository.findCourseGradeByCourseId(1L, 1L);
        Long courseId = StudentGradeEditDto.getCourseId();
        Course course1 = courseRepository.findCourseById(courseId);
        assertThat(course1.getCourseName()).isEqualTo("JAVA");
        assertThat(StudentGradeEditDto.getStudentName()).isEqualTo("John Doe");
        assertThat(StudentGradeEditDto.getMidTermScore()).isEqualTo(90);
        // Python 강의 수강생 중 StudentId 가 1L인 "John Doe" 학생의 gradeEditDto
        GradeEditDto StudentGradeEditDtoPy = assessmentRepository.findCourseGradeByCourseId(2L, 1L);
        Long courseId2 = StudentGradeEditDtoPy.getCourseId();
        Course course2 = courseRepository.findCourseById(courseId2);
        assertThat(course2.getCourseName()).isEqualTo("Python");
        assertThat(StudentGradeEditDtoPy.getStudentName()).isEqualTo("John Doe");
        assertThat(StudentGradeEditDtoPy.getMidTermScore()).isEqualTo(98);

    }

}