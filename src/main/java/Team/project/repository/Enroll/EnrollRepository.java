package Team.project.repository.Enroll;

import Team.project.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollRepository extends JpaRepository<Enroll, Long>, EnrollRepositoryCustom {

    List<Enroll> findByCourseId(Long courseId);
    @Query("select e from Enroll e join fetch e.assessment")
    List<Enroll> findAll();

}
