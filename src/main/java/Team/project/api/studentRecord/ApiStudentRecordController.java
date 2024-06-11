package Team.project.api.studentRecord;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.info.InfoDto;
import Team.project.dto.studentRecord.StudentCreditDto;
import Team.project.dto.studentRecord.StudentHistoryDto;
import Team.project.dto.studentRecord.StudentRecordResponse;
import Team.project.service.grade.GradeService;
import Team.project.service.info.InfoService;
import Team.project.service.studentRecord.StudentRecordService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ApiStudentRecordController {

    private final StudentRecordService studentRecordService;
    private final GradeService gradeService;
    private final InfoService infoService;

    @GetMapping("/student/record/{id}")
    public ResponseEntity<?> studentRecord(@PathVariable Long id, HttpSession session){
        log.info("학생 학적표 조회 시도 중 - uri.id: {}", id);
        
        /* 학생 휴복학 상태 */
        log.info("학생 휴복학 조회 시도 중 - uri.id: {}", id);
        List<StudentHistoryDto> studentHistoryDtos = studentRecordService.studentRecord(id, session);
        log.info("학생 휴복학 상태 조회 성공 - student_history:{}", studentHistoryDtos);

        /* 학생 성적 조회 */
        log.info("학생 성적 조회 시도 중 - uri.id: {}", id);
        List<GradeDto> gradeDtos = gradeService.findAllGradeByStudentId(id);
        log.info("학생 성적 조회 성공 - uri.id: {}, gradeDto: {}", id, gradeDtos);

        /* 학생 학점 조회 */
        log.info("학생 학점 조회 시도 중 - uri.id: {}", id);
        StudentCreditDto studentCreditDto = studentRecordService.selectCredit(id, gradeDtos);
        log.info("학생 학점 조회 성공 - studentCredit");

        /* 학생 정보 조회 */
        log.info("학생 정보 조회 시도 중 - uri.id: {}", id);
        InfoDto infoDto = infoService.findStudent(id, session);
        log.info("학생 정보 조회 성공 - uri.id: {}, infoDto: {}", id, infoDto);

        log.info("학생 학적표 조회 성공 - uri.id: {}", id);
        
        return ResponseEntity.ok(new StudentRecordResponse(studentHistoryDtos, gradeDtos, studentCreditDto, infoDto));
    }
}
