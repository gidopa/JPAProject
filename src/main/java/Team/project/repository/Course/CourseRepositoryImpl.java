package Team.project.repository.Course;

import Team.project.entity.Course;
import Team.project.entity.QCourse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CourseRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 모든 강의 조회
    @Override
    public List<Course> findAllCourses() {
        QCourse qCourse = QCourse.course;
        return queryFactory.select(qCourse).from(qCourse).fetch();
    }
}
