package Team.project.service.CourseOpen;

import Team.project.dto.course.CourseDto;
import Team.project.entity.Category;
import Team.project.entity.Course;
import Team.project.entity.Professor;
import Team.project.entity.SemesterInfo;
import Team.project.repository.CourseOpen.CourseOpenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseOpenServiceImpl implements CourseOpenService {
    private final CourseOpenRepository courseOpenRepository;

    @Override
    public Course addCourse(CourseDto courseDto, Professor professor) {
        Category category = Category.valueOf(courseDto.getCategory().toUpperCase());
        SemesterInfo semesterInfo = new SemesterInfo(courseDto.getYear(), courseDto.getSemester());
        Course course = new Course(
                    courseDto.getCourseName(),
                    professor,
                    courseDto.getMidTermWeight(),
                    courseDto.getFinalTermWeight(),
                    courseDto.getReportWeight(),
                    courseDto.getCredit(),
                    category,
                    semesterInfo,
                    courseDto.getFilePath()
            );
        log.info("course 정보 : {}", course.toString());

        return courseOpenRepository.saveCourse(course);
    }
}
