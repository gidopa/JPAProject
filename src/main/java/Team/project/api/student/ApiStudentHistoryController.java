package Team.project.api.student;

import Team.project.dto.login.LoginDto;
import Team.project.dto.student.StudentHistoryDto;
import Team.project.entity.Student;
import Team.project.exception.NoStateChangeException;
import Team.project.exception.UnauthorizedException;
import Team.project.service.Student.StudentService;
import Team.project.service.StudentHistory.StudentHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiStudentHistoryController {

    private final StudentHistoryService studentHistoryService;
    private final StudentService studentService;

    @PostMapping("/enrollments/students/{studentId}")
    public ResponseEntity<?> getHistory(@RequestBody StudentHistoryDto studentHistoryDto, @SessionAttribute(required = false) LoginDto loginDto) {
        Long studentId = loginDto.getStudentId();
        Optional<Student> loginStudent = studentService.findById(studentId);
        if(loginStudent.isPresent()){
            Student student = loginStudent.get();
                if (loginDto == null) {
                    throw new UnauthorizedException("로그인이 되지 않았습니다");
                } else if (loginDto.getStudentId() == null) {
                    throw new UnauthorizedException("학생이 아닙니다");
                } else if (student.getLastStatus().equals(studentHistoryDto.getNewStatus())){
                    throw new NoStateChangeException("변경하려는 상태가 기존의 상태와 동일합니다");
                }
                StudentHistoryDto historyDto = studentHistoryService.updateEnrollments(studentHistoryDto);
                return ResponseEntity.ok(historyDto);
        }else{
            throw new IllegalArgumentException("Student Not Found");
        }


    }
}
