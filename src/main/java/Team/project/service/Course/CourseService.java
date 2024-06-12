package Team.project.service.Course;

import Team.project.dto.course.CourseDto;
import Team.project.entity.Course;

import java.util.List;

public interface CourseService {
    List<CourseDto> findAllCourses();
    List<Course> findAllByProfessorId(Long professorId);
}
