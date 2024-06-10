package Team.project.repository.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.grade.GradeEditDto;
import Team.project.dto.grade.QGradeDto;
import Team.project.entity.QAssessment;
import Team.project.entity.QCourse;
import Team.project.entity.QEnroll;
import Team.project.entity.QStudent;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static Team.project.entity.QAssessment.*;
import static Team.project.entity.QCourse.*;
import static Team.project.entity.QEnroll.*;
import static Team.project.entity.QStudent.*;

@Repository
public class GradeRepositoryImpl implements GradeRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public GradeRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 학생 학번으로 성적 조회 (primary key)
     */
    @Override
    public List<GradeDto> findAllGradeByStudentId(Long studentId) {
        return queryFactory
                .select(new QGradeDto(
                        student.id.as("studentId"),
                        course.id.as("courseId"),
                        student.name,
                        course.courseName,
                        assessment.midTermScore,
                        assessment.finalTermScore,
                        assessment.reportScore,
                        enroll.gradeType
                ))
                .from(enroll)
                .where(student.id.eq(studentId))
                .fetch();
    }

    /**
     * 강의 별로 학생 성적 조회
     */
    @Override
    public List<GradeDto> findAllGradeByCourseId(Long courseId) {
        return queryFactory
                .select(new QGradeDto(
                        student.id,
                        course.id,
                        student.name,
                        course.courseName,
                        assessment.midTermScore,
                        assessment.finalTermScore,
                        assessment.reportScore,
                        enroll.gradeType
                ))
                .from(enroll)
                .join(enroll.assessment, assessment)
                .on(enroll.id.eq(assessment.enroll.id))
                .where(course.id.eq(courseId))
                .fetch();
    }

    /**
     * 성적 부여
     */

    @Transactional
    @Override
    public void assignGrade(GradeEditDto editDto) {
        queryFactory
                .update(assessment)
                .set(assessment.midTermScore, editDto.getMidTermScore())
                .set(assessment.finalTermScore, editDto.getFinalTermScore())
                .set(assessment.reportScore, editDto.getReportScore())
                .where(assessment.enroll.id.eq(
                        JPAExpressions.select(enroll.id)
                                .from(enroll)
                                .where(enroll.course.id.eq(editDto.getCourseId())
                                        .and(enroll.student.id.eq(editDto.getStudentId())))
                ))
                .execute();
        em.flush();
        em.clear();
    }


}
