package Team.project.repository.Enroll;

import Team.project.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollRepository extends JpaRepository<Enroll, Long>, EnrollRepositoryCustom {

}
