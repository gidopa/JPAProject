package Team.project.repository.login;

import Team.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentLoginRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByHakbun(Long hakbun);
}
