package Team.project.controller;

import Team.project.dto.login.LoginDto;
import Team.project.exception.LoginCustomException;
import Team.project.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }



    @ExceptionHandler(LoginCustomException.class)
    public String LoginException(LoginCustomException ex, RedirectAttributes redirect){
        redirect.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/login";
    }
}
