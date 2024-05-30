package Team.project.api.login;

import Team.project.dto.login.LoginDto;
import Team.project.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiLoginController {

    private final LoginService loginService;

    @PostMapping("/api/login")
    public LoginDto login(@Valid @RequestBody LoginDto loginDto,
                                          HttpServletRequest request,
                                          BindingResult result) {

        loginService.loginStudent(loginDto, request);

        return loginDto;
    }
}
