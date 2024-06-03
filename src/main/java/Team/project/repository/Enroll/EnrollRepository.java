package Team.project.repository.Enroll;

import Team.project.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollRepository extends JpaRepository<Enroll, Long>, EnrollRepositoryCustom {

    List<Enroll> findByCourseId(Long courseId);

}
