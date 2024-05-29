package Team.project.repository.grade;

import Team.project.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long>, GradeRepositoryCustom {

}
