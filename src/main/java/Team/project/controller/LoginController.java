package Team.project.controller;

import Team.project.dto.login.LoginDto;
import Team.project.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginDto", new LoginDto());

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto, BindingResult result, HttpServletRequest request){

        if(result.hasErrors()){
            return "login/loginForm";
        }

        // 교수, 학생 체크, 로그인 처리
        if(loginDto.getId().startsWith("2")){ // 학생
            loginService.loginStudent(loginDto,  request);

        }else { // 교수
            loginService.loginProfessor(loginDto, request);
        }

        return "redirect:/";
    }


}
