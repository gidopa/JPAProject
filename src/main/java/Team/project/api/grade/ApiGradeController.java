package Team.project.api.grade;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.grade.GradeEditDto;
import Team.project.dto.login.LoginDto;
import Team.project.entity.Assessment;
import Team.project.entity.Grade;
import Team.project.exception.AssignGradeNotFoundException;
import Team.project.exception.GradeNotFoundException;
import Team.project.exception.UnauthorizedException;
import Team.project.repository.Assessment.AssessmentRepository;
import Team.project.service.grade.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grades")
@Slf4j
public class ApiGradeController {

    private final GradeService gradeService;
    private final AssessmentRepository assessmentRepository;

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
            if (loginDto.getStudentId() == null){
                throw new UnauthorizedException("학생이 아닙니다");
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

    /**
     * 성적 부여 API
     */
    @PostMapping("/{courseId}/{studentId}")
    public ResponseEntity<?> assignGrade(@PathVariable Long courseId, @PathVariable Long studentId,
                                         @SessionAttribute(required = false) LoginDto loginDto,
                                         @RequestBody GradeEditDto editDto){
        try {
            if (loginDto == null) {
                throw new UnauthorizedException("로그인이 되지 않았습니다");
            } else if (loginDto.getProfessorId() == null) {
                throw new UnauthorizedException("교수님이 아닙니다");
            }
            if(editDto == null){
                throw new AssignGradeNotFoundException("부여할 성적이 없습니다");
            }
            gradeService.assignGrade(editDto);
            return ResponseEntity.ok(editDto);
        } catch (UnauthorizedException e) {
            throw e;
        } catch(AssignGradeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

}

   
}
