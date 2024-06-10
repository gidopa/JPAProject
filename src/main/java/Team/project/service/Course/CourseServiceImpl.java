package Team.project.service.course;

import Team.project.entity.Course;
import Team.project.repository.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAllByProfessorId(Long professorId) {
        return courseRepository.findAllByProfessorId(professorId);
    }
}
