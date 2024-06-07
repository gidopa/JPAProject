package Team.project.repository.Course;

import Team.project.entity.Course;
import Team.project.repository.CourseRegistration.CourseRegistrationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom {

}
