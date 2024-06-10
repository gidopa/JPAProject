package Team.project.service.professor;

import Team.project.entity.Course;
import Team.project.entity.Grade;

import java.util.List;

public interface ProfessorService {
    List<Course> findGradeListBy(Long professorId);
}
