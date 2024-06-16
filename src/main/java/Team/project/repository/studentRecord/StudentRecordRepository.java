package Team.project.repository.studentRecord;

import Team.project.entity.StudentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRecordRepository extends JpaRepository<StudentHistory, Long>, StudentRecordRepositoryCustom {

}
