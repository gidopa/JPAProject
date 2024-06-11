package Team.project.controller;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.info.InfoDto;
import Team.project.dto.studentRecord.StudentCreditDto;
import Team.project.dto.studentRecord.StudentHistoryDto;
import Team.project.exception.InfoCustomException;
import Team.project.exception.SessionNotFoundException;
import Team.project.exception.StudentRecordCustomException;
import Team.project.service.grade.GradeService;
import Team.project.service.info.InfoService;
import Team.project.service.studentRecord.StudentRecordService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class StudentRecordController {

    private final StudentRecordService studentRecordService;
    private final GradeService gradeService;
    private final InfoService infoService;

    /* 학생 학적표 조회(학생 정보, 학생 상태 변경 내역, 성적 조회) */
    @GetMapping("/web/student/record/{id}")
    public String studentRecord(@PathVariable Long id, Model model, HttpSession session){
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
        
        model.addAttribute("studentHistoryDto", studentHistoryDtos);
        model.addAttribute("gradeDto", gradeDtos);
        model.addAttribute("infoDto", infoDto);
        model.addAttribute("studentCreditDto",studentCreditDto);

        log.info("학생 학적표 조회 성공 - uri.id: {}", id);

        return  "studentRecord/studentRecord";
    }

    @ExceptionHandler(StudentRecordCustomException.class)
    public String handlerException(StudentRecordCustomException ex, Model model){

        model.addAttribute("errorMessage", ex.getMessage());

        return "common/error";
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public String handlerException(SessionNotFoundException ex, Model model){

        model.addAttribute("errorMessage", ex.getMessage());

        return "common/error";
    }

    @ExceptionHandler(InfoCustomException.class)
    public String handlerException(InfoCustomException ex, Model model){

        model.addAttribute("errorMessage", ex.getMessage());

        return "common/error";
    }

}
