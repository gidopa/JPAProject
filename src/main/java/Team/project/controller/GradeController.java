package Team.project.controller;

import Team.project.dto.grade.GradeDto;
import Team.project.entity.GradeType;
import Team.project.entity.Student;
import Team.project.service.Student.StudentService;
import Team.project.service.grade.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;

    // 학생이 학번으로 로그인 한 후 자신의 성적 조회
    @GetMapping("/grades/students")
    public String allGrades(Model model, @SessionAttribute(value = "hakbun", required = false)Long hakbun) {
        hakbun= (long) 20240001;
        Optional<Student> findStudent = studentService.findByHakbun(hakbun);
        Student student = findStudent.orElseThrow(() -> new IllegalArgumentException("학번으로 학생을 조회할 수 없음"));
        Long studentId = student.getId();
        List<GradeDto> allGrade = gradeService.findAllGradeByStudentId(studentId);
        model.addAttribute("allGrade", allGrade);
        return "grade/List";
    }
    // 교수가 자기 강의를 수강하는 학생들의 성적 조회
    @GetMapping("/grades/{courseId}")
    public String gradeListByCourse(@PathVariable Long courseId, Model model){
        List<GradeDto> gradeList = gradeService.findAllGradeByCourseId(courseId);
        model.addAttribute("allGrade", gradeList);
        return "grade/List";
    }


}
