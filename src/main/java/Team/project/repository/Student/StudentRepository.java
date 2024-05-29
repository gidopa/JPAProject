package Team.project.repository.Student;

import Team.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> , StudentRepositoryCustom {
}
