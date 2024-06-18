package Team.project.api.courseRegistration;

import Team.project.dto.course.CourseDto;
import Team.project.entity.Enroll;
import Team.project.service.Course.CourseService;
import Team.project.service.CourseRegistration.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/courseRegistration")
@RequiredArgsConstructor
public class ApiCourseRegistrationController {
    private final CourseRegistrationService courseRegistrationService;
    private final CourseService courseService;

    // 강의목록 보이기
    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses() {
        List<CourseDto> courses = courseService.findAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCourse(@RequestParam Long hakbun, @RequestParam Long courseId) {
        try {
            log.info("Registering course for hakbun: {} and courseId: {}", hakbun, courseId);
            Enroll enroll = courseRegistrationService.registerCourse(hakbun, courseId);
            log.info("Enroll: {}", enroll);
            return new ResponseEntity<>("수강신청이 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error during course registration: ", e);
            return new ResponseEntity<>("수강신청 실패 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}