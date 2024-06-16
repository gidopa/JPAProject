package Team.project.repository.Course;

import Team.project.dto.course.CourseDto;
import Team.project.dto.course.QCourseDto;
import Team.project.entity.Category;
import Team.project.entity.Course;
import Team.project.entity.QCourse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Slf4j
public class CourseRepositoryImpl implements CourseRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CourseRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CourseDto> findAllCourses() {
        QCourse course = QCourse.course;
        return queryFactory.select(new QCourseDto(
                        course.id,
                        course.courseName,
                        course.professor.name,
                        course.credit,
                        course.semesterInfo.semester,
                        course.semesterInfo.years,
                        course.midTermWeight,
                        course.finalTermWeight,
                        course.reportWeight,
                        course.category.stringValue(),
                        course.filePath
                ))
                .from(course)
                .fetch();
    }

}
