package Team.project.repository.Enroll;

import Team.project.dto.grade.GradeDto;
import Team.project.entity.Course;
import Team.project.entity.Enroll;
import Team.project.entity.Student;

import java.util.List;

public interface EnrollRepositoryCustom {
    boolean existsByStudentAndCourse(Student student, Course course);

    Enroll save(Enroll enroll);
}
