package Team.project.repository.Enroll;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.grade.QGradeDto;
import Team.project.entity.Course;
import Team.project.entity.Enroll;
import Team.project.entity.Student;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class EnrollRepositoryImpl implements EnrollRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public EnrollRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public boolean existsByStudentAndCourse(Student student, Course course) {
        return false;
    }

    @Override
    public Enroll save(Enroll enroll) {
        return null;
    }
}
