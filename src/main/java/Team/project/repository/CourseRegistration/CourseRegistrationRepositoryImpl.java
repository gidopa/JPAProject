package Team.project.repository.CourseRegistration;

import Team.project.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRegistrationRepositoryImpl implements CourseRegistrationRepositoryCustom {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CourseRegistrationRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    @Transactional
    public Enroll registerCourse(Long hakbun, Long courseId) {
        Student student = em.find(Student.class, hakbun);
        Course course = em.find(Course.class, courseId);

        if (student == null || course == null) {
            throw new IllegalArgumentException("학생 또는 강좌를 찾을 수 없습니다.");
        }

        if (existsByStudentAndCourse(student, course)) {
            throw new IllegalStateException("이미 수강신청된 강좌입니다.");
        }

        Enroll enroll = new Enroll(student, course);
        em.persist(enroll);
        return enroll;
    }

    @Override
    public boolean existsByStudentAndCourse(Student student, Course course) {
        QEnroll qEnroll = QEnroll.enroll;

        Integer count = queryFactory
                .selectOne()
                .from(qEnroll)
                .where(qEnroll.student.eq(student).and(qEnroll.course.eq(course)))
                .fetchFirst();
        return count != null;
    }
}
