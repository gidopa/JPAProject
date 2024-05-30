package Team.project.repository.Student;

import Team.project.entity.QStudent;
import Team.project.entity.Student;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static Team.project.entity.QStudent.*;

@Repository
public class StudentRepositoryImpl implements StudentRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public StudentRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Student> findByHakbun(Long hakbun) {
        Student findStudent = queryFactory
                .selectFrom(student)
                .where(student.hakbun.eq(hakbun))
                .fetchOne();
        return Optional.ofNullable(findStudent);
    }
}
