package Team.project.controller;

import Team.project.dto.course.CourseDto;
import Team.project.entity.Enroll;
import Team.project.service.Course.CourseService;
import Team.project.service.CourseRegistration.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CourseRegistrationController {

    private final CourseRegistrationService courseRegistrationService;
    private final CourseService courseService;

    // 강의목록 보이기
    @GetMapping("/courseRegistration")
    public String courseRegistration(Model model) {

        List<CourseDto> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);

        return "courseRegistration/courseRegistration";
    }

    // 수강신청
    @PostMapping("/courseRegistration/register")
    public String registerCourse(@RequestParam Long hakbun, @RequestParam Long courseId, Model model, RedirectAttributes redirectAttributes) {
        try {
            Enroll enroll = courseRegistrationService.registerCourse(hakbun, courseId);
            log.info("Enroll : {}", enroll);
            // 리다이렉트 후 뷰 페이지에서 수강신청 성공 실패여부 보여줌
            redirectAttributes.addFlashAttribute("enrollmentResult", "수강신청이 완료되었습니다.");
        } catch (Exception e) {
            log.error("CourseRegistrationController : {}", e.getMessage());
            redirectAttributes.addFlashAttribute("enrollmentResult", "수강신청 실패 : " + e.getMessage());
        }
        return "redirect:/courseRegistration";
    }
}

