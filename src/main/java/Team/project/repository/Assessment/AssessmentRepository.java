package Team.project.repository.Assessment;

import Team.project.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AssessmentRepository extends JpaRepository<Assessment, Long>, AssessmentRepositoryCustom{

    @Query("select a from Assessment a join fetch Enroll e join fetch Student s join fetch Course c where s.id = :studentId and c.id = :courseId")
    Assessment findAssessmentByEnroll_StudentAndEnroll_Course(Long studentId, Long courseId);
}
