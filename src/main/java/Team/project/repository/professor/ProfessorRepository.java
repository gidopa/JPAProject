package Team.project.repository.professor;

import Team.project.entity.Course;
import Team.project.entity.Grade;
import Team.project.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long>, ProfessorRepositoryCustom {

    @Query("select c from Course c where c.professor.id = :professorId")
    List<Course> findGradeListBy(Long professorId);
}
