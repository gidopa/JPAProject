package Team.project.controller;

import Team.project.dto.course.CourseDto;
import Team.project.dto.login.LoginDto;
import Team.project.entity.Category;
import Team.project.entity.Professor;
import Team.project.service.Course.CourseService;
import Team.project.service.CourseOpen.CourseOpenService;
import Team.project.service.professor.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Year;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CourseOpenController {

    private final ProfessorService professorService;
    private final CourseOpenService courseOpenService;

    @GetMapping("/web/professor/courseOpen")
    public String showView(Model model) {

        model.addAttribute("course", new CourseDto());
        return "courseOpen/courseOpen";
    }

    @PostMapping("/web/professor/openCourse")
    public String addCourse(@ModelAttribute("course") CourseDto courseDto,
                                 @SessionAttribute("loginDto") LoginDto loginDto,
                                 @RequestParam("category") String category,
                                 @RequestParam("file") MultipartFile file,
                            Model model) {
        String filePath = saveFile(file);
        courseDto.setFilePath(filePath);
        log.info("File path received: {}", courseDto.getFilePath());
        log.info("LoginDto : {}", loginDto);

        Professor professor = professorService.findProfessorByLoginId(loginDto.getId());

        try {
            courseOpenService.addCourse(courseDto, professor);
            model.addAttribute("success", true);
        } catch (Exception e) {
            log.error("Error adding course: ", e);
            model.addAttribute("success", false);
        }
        model.addAttribute("course", new CourseDto()); // 다시 빈 폼으로 리셋
        return "courseOpen/courseOpen";
    }

    private String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();  // 디렉토리 생성
        }
        String filePath = uploadDir + fileName;
        File dest = new File(filePath);
        try {
            file.transferTo(dest);
            log.info("File saved to: {}", filePath); // 파일 경로 로그 출력
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        return filePath;
    }

}
