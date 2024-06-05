package Team.project.controller;

import Team.project.entity.CourseRegistration;
import Team.project.service.CourseRegistration.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/courseRegistration")
@RequiredArgsConstructor
public class CourseRegistrationController {
    private final CourseRegistrationService courseRegistrationService;

    @PostMapping("/register")
    public String registerCourse(@RequestParam Long hakbun, @RequestParam Long courseId, Model model) {


        try {
            CourseRegistration courseRegistration = courseRegistrationService.registerCourse(hakbun, courseId);
            model.addAttribute("enrollmentResult", new RegistrationResult(true, "수강신청이 완료되었습니다."));
        } catch (Exception e) {
            log.error("CourseRegistrationController : {}", e.getMessage());
            model.addAttribute("enrollmentResult", new RegistrationResult(false, e.getMessage()));
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
