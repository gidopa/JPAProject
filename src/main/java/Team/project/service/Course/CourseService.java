package Team.project.service.course;

import Team.project.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAllByProfessorId(Long professorId);
}
