package Team.project.service.Student;

import Team.project.entity.Student;

import java.util.Optional;

public interface StudentService {

    Optional<Student> findById(Long studentId);
    Optional<Student> findByHakbun(Long hakbun);
}
