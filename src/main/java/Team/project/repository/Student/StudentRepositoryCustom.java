package Team.project.repository.Student;

import Team.project.entity.Student;

import java.util.Optional;

public interface StudentRepositoryCustom {

    Optional<Student> findByHakbun(Long hakbun);
}
