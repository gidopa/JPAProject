package Team.project.api.login;

import Team.project.dto.login.LoginDto;
import Team.project.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiLoginController {

    private final LoginService loginService;

    /* 로그인 처리 */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto,
                                HttpServletRequest request) {
        LoginDto result;

        if(loginDto.getId().startsWith("2")){
            result = loginService.loginStudent(loginDto, request);
        }else{
            result = loginService.loginProfessor(loginDto, request);
        }

        return ResponseEntity.ok(result);
    }

}
