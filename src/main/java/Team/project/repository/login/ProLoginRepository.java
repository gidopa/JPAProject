package Team.project.repository.login;

import Team.project.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProLoginRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByLoginId(Long loginId);
}
