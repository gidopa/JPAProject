package Team.project.controller;

import Team.project.dto.login.LoginDto;
import Team.project.dto.student.StudentHistoryDto;
import Team.project.entity.Student;
import Team.project.entity.StudentHistory;
import Team.project.entity.StudentStatus;
import Team.project.service.Student.StudentService;
import Team.project.service.StudentHistory.StudentHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentHistoryController {

    private final StudentHistoryService studentHistoryService;
    private final StudentService studentService;

    @GetMapping("/web/student/enrollments")
    public String enrollForm(Model model, @SessionAttribute LoginDto loginDto){
        Long studentId = loginDto.getStudentId();
        Optional<Student> findStudent = studentService.findById(studentId);
        if(findStudent.isPresent()){
            Student student = findStudent.get();
            model.addAttribute("student", student);
        }else{
            throw new IllegalArgumentException("Student Not Found");
        }
        model.addAttribute("studentHistory", new StudentHistory());
        model.addAttribute("studentHistoryDto", new StudentHistoryDto());
        return "/student/enrollForm";
    }

    @PostMapping("/web/students/enrollments")
    public String studentsEnrollment(@Valid @ModelAttribute StudentHistoryDto studentHistoryDto, BindingResult bindingResult,
                                     Model model){
        log.info("modelAttribute : {}", studentHistoryDto.toString());
        if(bindingResult.hasErrors()){
            model.addAttribute("studentHistoryDto", studentHistoryDto);
            Optional<Student> findStudent = studentService.findById(studentHistoryDto.getStudentId());
            if(findStudent.isPresent()){
                Student student = findStudent.get();
                model.addAttribute("student", student);
            }else{
                throw new IllegalArgumentException("Student Not Found");
            }
            return "/student/enrollForm";
        }

        studentHistoryService.updateEnrollments(studentHistoryDto);

        return "redirect:/";
    }

}
