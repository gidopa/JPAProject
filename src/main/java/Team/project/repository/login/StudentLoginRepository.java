package Team.project.repository.login;

import Team.project.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentLoginRepository extends JpaRepository<Student, Long> {

    @EntityGraph(attributePaths = {"credit"})
    Optional<Student> findByHakbun(Long hakbun);

    @Query("SELECT s FROM Student s JOIN FETCH s.major m JOIN FETCH s.credit c WHERE s.id = :id")
    Optional<Student> findStudentById(Long id);

    @EntityGraph(attributePaths = {"credit"})
    Optional<Student> findByHakbunAndId(Long hakbun, Long id);
}
