package Team.project.repository.professor;

import Team.project.entity.Grade;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessorRepositoryImpl implements ProfessorRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ProfessorRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


}
