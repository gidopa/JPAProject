package Team.project.controller;

import Team.project.dto.Logind.LoginDto;
import Team.project.dto.grade.GradeDto;
import Team.project.entity.Course;
import Team.project.entity.Student;
import Team.project.service.Student.StudentService;
import Team.project.service.course.CourseService;
import Team.project.service.course.CourseServiceImpl;
import Team.project.service.grade.GradeService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.From;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final CourseService courseService;

    // 학생이 학번으로 로그인 한 후 자신의 성적 조회
    @GetMapping("/grades/students")
    public String allGrades(Model model, @SessionAttribute(value = "hakbun", required = false)Long hakbun) {
        hakbun= (long) 20240001;
        Optional<Student> findStudent = studentService.findByHakbun(hakbun);
        Student student = findStudent.orElseThrow(() -> new IllegalArgumentException("학번으로 학생을 조회할 수 없음"));
        Long studentId = student.getId();
        List<GradeDto> allGrade = gradeService.findAllGradeByStudentId(studentId);
        model.addAttribute("allGrade", allGrade);
        return "grade/myList";
    }

    // 교수가 자기 강의를 수강하는 학생들의 성적 조회
    @GetMapping("/grades/{courseId}")
    public String gradeListByCourse(@PathVariable Long courseId, Model model, @SessionAttribute(required = false) LoginDto dto)throws IllegalArgumentException{
        dto = new LoginDto();
        dto.setProfessorId((long)1);
        List<Course> lists = courseService.findAllByProfessorId(dto.getProfessorId());
        model.addAttribute("list", lists);
        // 자신의 강의 중 어떤 강의에 대한 조회를 할건지 위해 course 조회
        List<GradeDto> gradeList = gradeService.findAllGradeByCourseId(courseId);
        model.addAttribute("allGrade", gradeList);
        return "grade/AllListProf";
    }




}
