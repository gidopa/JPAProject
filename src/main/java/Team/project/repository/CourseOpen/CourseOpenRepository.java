package Team.project.repository.CourseOpen;

import Team.project.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CourseOpenRepository extends JpaRepository<Course, Long>, CourseOpenRepositoryCustom {

}
