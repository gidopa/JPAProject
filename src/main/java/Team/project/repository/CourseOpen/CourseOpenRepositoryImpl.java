package Team.project.repository.CourseOpen;

import Team.project.entity.Course;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class CourseOpenRepositoryImpl implements CourseOpenRepositoryCustom {

    private final EntityManager em;
    private final QueryFactory queryFactory;

    public CourseOpenRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    @Transactional
    public Course saveCourse(Course course) {
        if (course.getId() == null) {
            em.persist(course);
            return course;
        } else {
            return em.merge(course);
        }
    }
}
