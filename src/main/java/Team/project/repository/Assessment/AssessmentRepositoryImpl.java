package Team.project.repository.Assessment;

import Team.project.dto.grade.GradeEditDto;
import Team.project.dto.grade.QGradeDto;
import Team.project.dto.grade.QGradeEditDto;
import Team.project.entity.QAssessment;
import Team.project.entity.QCourse;
import Team.project.entity.QEnroll;
import Team.project.entity.QStudent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static Team.project.dto.grade.QGradeDto.*;
import static Team.project.entity.QAssessment.*;
import static Team.project.entity.QCourse.*;
import static Team.project.entity.QEnroll.*;
import static Team.project.entity.QStudent.*;

@Repository
public class AssessmentRepositoryImpl implements AssessmentRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public AssessmentRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public GradeEditDto findCourseGradeByCourseId(Long courseId, Long studentId) {
        return queryFactory
                .select(new QGradeEditDto(student.id.as("student_id"), course.id,student.name, assessment.midTermScore, assessment.finalTermScore, assessment.reportScore))
                .from(assessment)
                .join(assessment.enroll, enroll)
                .on(enroll.course.id.eq(courseId))
                .where(enroll.student.id.eq(studentId))
                .fetchOne();
    }
}
