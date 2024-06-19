package Team.project.api.courseOpen;

import Team.project.dto.course.CourseDto;
import Team.project.entity.Professor;
import Team.project.service.CourseOpen.CourseOpenService;
import Team.project.service.professor.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/professor")
@Slf4j
@RequiredArgsConstructor
public class ApiCourseOpenController {
    private final ProfessorService professorService;
    private final CourseOpenService courseOpenService;

    @GetMapping("/courseOpen")
    public ResponseEntity<?> showView() {
        CourseDto course = new CourseDto();
        return ResponseEntity.ok(course);
    }

    @PostMapping("/openCourse")
    public ResponseEntity<?> addCourse(@ModelAttribute CourseDto courseDto,
                                       @RequestParam("loginId") String loginId,
                                       @RequestParam("file") MultipartFile file) {
        String filePath = saveFile(file);
        courseDto.setFilePath(filePath);
        log.info("File path received: {}", courseDto.getFilePath());
        log.info("LoginId : {}", loginId);

        Professor professor = professorService.findProfessorByLoginId(loginId);

        try {
            courseOpenService.addCourse(courseDto, professor);
            return new ResponseEntity<>("강의 등록이 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error adding course: ", e);
            return new ResponseEntity<>("강의 등록 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
