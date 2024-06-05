package Team.project.api.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.login.LoginDto;
import Team.project.exception.GradeNotFoundException;
import Team.project.exception.UnauthorizedException;
import Team.project.service.grade.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grades")
@Slf4j
public class ApiGradeController {

    private final GradeService gradeService;

    /**
     * 학생이 자기가 수강한 강의의 성적 조회 /
     * @param loginDto
     */
    @GetMapping("/students")
    public ResponseEntity<?> getStudentGrades(@SessionAttribute(required = false) LoginDto loginDto) {
        try {
            if (loginDto == null) {
                throw new UnauthorizedException("로그인이 되지 않았습니다");
            }
            Long studentId = loginDto.getStudentId();
            List<GradeDto> gradeList = gradeService.findAllGradeByStudentId(studentId);
            return ResponseEntity.ok(gradeList);
        } catch (GradeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    /**
     * 특정 강의를 수강중인 학생들의 성적 조회하는 API
     */
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<?> getGradeListByCourse(@PathVariable Long courseId, @SessionAttribute(required = false) LoginDto loginDto) {
        try {
            if (loginDto == null) {
                throw new UnauthorizedException("로그인이 되지 않았습니다");
            } else if (loginDto.getProfessorId() == null) {
                throw new UnauthorizedException("교수님이 아닙니다");
            }
            List<GradeDto> list = gradeService.findAllGradeByCourseId(courseId);
            return ResponseEntity.ok(list);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }



}
