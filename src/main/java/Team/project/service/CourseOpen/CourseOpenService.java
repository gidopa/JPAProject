package Team.project.service.CourseOpen;

import Team.project.dto.course.CourseDto;
import Team.project.entity.Course;
import Team.project.entity.Professor;

public interface CourseOpenService {

    Course addCourse(CourseDto courseDto, Professor professor);
}
