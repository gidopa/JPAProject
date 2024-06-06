package Team.project.repository.CourseRegistration;

import Team.project.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistrationRepository extends JpaRepository<Enroll, Long>, CourseRegistrationRepositoryCustom {
}
