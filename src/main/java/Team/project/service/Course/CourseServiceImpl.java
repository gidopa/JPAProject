package Team.project.service.Course;

import Team.project.dto.course.CourseDto;
import Team.project.entity.Course;
import Team.project.repository.Course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public List<CourseDto> findAllCourses() {
        return courseRepository.findAllCourses();
    }
}
