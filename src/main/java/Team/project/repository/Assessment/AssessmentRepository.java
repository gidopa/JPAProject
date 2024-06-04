package Team.project.repository.Assessment;

import Team.project.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, Long>, AssessmentRepositoryCustom{

}
