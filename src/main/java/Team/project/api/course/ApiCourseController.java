package Team.project.api.course;

import Team.project.dto.login.LoginDto;
import Team.project.entity.Course;
import Team.project.exception.UnauthorizedException;
import Team.project.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
@Slf4j
public class ApiCourseController {

    private final CourseService courseService;

    @GetMapping("/professors/{professorId}")
    public ResponseEntity<?> getCourseList(@SessionAttribute(required = false) LoginDto loginDto) {
        try {
            if (loginDto == null) {
                throw new UnauthorizedException("로그인이 되지 않았습니다");
            }
            Long professorId = loginDto.getProfessorId();
            List<Course> courseList = courseService.findAllByProfessorId(professorId);
            return ResponseEntity.ok(courseList);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
