package Team.project.controller;

import Team.project.entity.Enroll;
import Team.project.service.CourseRegistration.CourseRegistrationService;
import Team.project.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CourseRegistrationController {

    private final CourseRegistrationService courseRegistrationService;
    private final CourseService courseService;

    @GetMapping("/courseRegistration")
    public String courseRegistration(Model model) {

//        List<CourseDto> courses = courseService.findAllCourses();
//        model.addAttribute("courses", courses);

        return "courseRegistration/courseRegistration";
    }

    @PostMapping("/courseRegistration/register")
    public String registerCourse(@RequestParam Long hakbun, @RequestParam Long courseId, Model model) {


        try {
            Enroll enroll = courseRegistrationService.registerCourse(hakbun, courseId);
            model.addAttribute("enrollmentResult", new RegistrationResult(true, "수강신청이 완료되었습니다."));
        } catch (Exception e) {
            log.error("CourseRegistrationController : {}", e.getMessage());
            model.addAttribute("enrollmentResult", new RegistrationResult(false, e.getMessage()));
        }
        return "redirect:/";
    }
}

    // 수강신청 성공 실패 여부
    class RegistrationResult {
        private boolean success;
        private String message;

        public RegistrationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }
