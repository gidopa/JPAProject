package Team.project.repository.grade;

import Team.project.entity.Assessment;
import Team.project.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GradeRepository extends JpaRepository<Grade, Long>, GradeRepositoryCustom {



}
