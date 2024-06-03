package Team.project.controller;

import Team.project.dto.login.LoginDto;
import Team.project.entity.Course;
import Team.project.entity.Grade;
import Team.project.service.professor.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final ProfessorService professorService;

    @GetMapping("/web/grades/professors/{professorId}")
    public String gradeListByProfessor(@PathVariable Long professorId, Model model){
        List<Course> courseList = professorService.findGradeListBy(professorId);
        model.addAttribute("courseList",courseList);
        return "grade/List";
    }
}
