package Team.project.repository.StudentHistory;

import Team.project.entity.StudentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentHistoryRepository extends JpaRepository<StudentHistory, Long>, StudentHistoryRepositoryCustom {


}
