package Team.project.controller;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.grade.GradeEditDto;
import Team.project.dto.grade.QGradeEditDto;
import Team.project.dto.login.LoginDto;
import Team.project.entity.Course;
import Team.project.entity.Grade;
import Team.project.entity.Student;
import Team.project.repository.Assessment.AssessmentRepository;
import Team.project.service.Assessment.AssessmentService;
import Team.project.service.Student.StudentService;
import Team.project.service.grade.GradeService;
import Team.project.service.professor.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final AssessmentService assessmentService;


    // 학생이 학번으로 로그인 한 후 자신의 성적 조회
    @GetMapping("/web/grades/students")
    public String allGrades(Model model, @SessionAttribute LoginDto loginDto) {
        String id = loginDto.getId();
        long hakbun = Long.parseLong(id);
        Optional<Student> findStudent = studentService.findByHakbun(hakbun);
        Student student = findStudent.orElseThrow(() -> new IllegalArgumentException("학번으로 학생을 조회할 수 없음"));
        Long studentId = student.getId();
        List<GradeDto> allGrade = gradeService.findAllGradeByStudentId(studentId);
        model.addAttribute("allGrade", allGrade);
        log.info("여기임");
        return "grade/myList";
    }

    // 교수가 자기 강의를 수강하는 학생들의 성적 조회
    @ResponseBody
    @GetMapping("/web/grades/courses/{courseId}")
    public ResponseEntity<List<GradeDto>> gradeListByCourse(@PathVariable Long courseId, Model model){
        List<GradeDto> gradeList = gradeService.findAllGradeByCourseId(courseId);
        log.info("왔냐");
        return ResponseEntity.ok(gradeList);
    }

    // 성적 수정 form 요청
    // findDto는 수정할 학생의 성적 Dto임
    @GetMapping("/web/grades/{courseId}/students/{studentId}")
    public String editGradeForm(@PathVariable Long courseId, @PathVariable Long studentId, Model model){
        GradeEditDto findDto = assessmentService.findCourseGradeByCourseId(courseId, studentId);
        model.addAttribute("findDto",findDto);
        return "grade/editForm";
    }

    // 성적 부여 / 수정
    @PostMapping("/web/grades/{courseId}/students/{studentId}")
    public String gradeListByProfessor(@Valid @ModelAttribute("findDto") GradeEditDto editDto, BindingResult bindingResult,
                                       @PathVariable Long courseId, Model model, @SessionAttribute LoginDto loginDto){
        if(bindingResult.hasErrors()){
            return "grade/editForm";
        }
        gradeService.assignGrade(editDto);
        Long professorId = loginDto.getProfessorId();
        return "redirect:/web/grades/professors/"+professorId;
    }





}
