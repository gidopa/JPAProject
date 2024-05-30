package Team.project.repository.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.grade.QGradeDto;
import Team.project.entity.QAssessment;
import Team.project.entity.QCourse;
import Team.project.entity.QEnroll;
import Team.project.entity.QStudent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

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
                        student.name.as("studentName"),
                        student.major.name.as("major"),
                        course.id.as("courseId"),
                        course.courseName.as("courseName"),
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
                        student.name.as("studentName"),
                        student.major.name.as("major"),
                        course.id.as("courseId"),
                        course.courseName.as("courseName"),
                        assessment.midTermScore,
                        assessment.finalTermScore,
                        assessment.reportScore,
                        enroll.gradeType
                ))
                .from(enroll)
                .innerJoin(enroll.student,student)
                .innerJoin(enroll.course, course)
                .innerJoin(enroll.assessment, assessment)
                .where(course.id.eq(courseId))
                .fetch();
    }
}
