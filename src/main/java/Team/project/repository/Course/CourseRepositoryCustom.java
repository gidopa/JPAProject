package Team.project.repository.Course;

import Team.project.dto.course.CourseDto;
import Team.project.entity.Course;

import java.util.List;

public interface CourseRepositoryCustom {

    List<CourseDto> findAllCourses();
}
