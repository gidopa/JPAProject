package Team.project.repository.Course;

import Team.project.dto.course.CourseDto;
import Team.project.entity.Course;
import Team.project.entity.QCourse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseRepositoryImpl implements CourseRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    // 모든 강의 조회
    @Override
    public List<CourseDto> findAllCourses() {
        List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
        return courses.stream()
                .map(course -> new CourseDto(course.getId(), course.getCourseName(), course.getProfessor().getName(), course.getCredit()))
                .collect(Collectors.toList());
    }
}
