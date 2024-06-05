package Team.project.repository.CourseRegistration;

import Team.project.entity.Course;
import Team.project.entity.CourseRegistration;
import Team.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);

}
