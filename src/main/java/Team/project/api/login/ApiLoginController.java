package Team.project.api.login;

import Team.project.dto.login.LoginDto;
import Team.project.exception.LoginCustomException;
import Team.project.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiLoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto,
                                HttpServletRequest request) {

        try{
            LoginDto result;
            if(loginDto.getId().startsWith("2")){
                result = loginService.loginStudent(loginDto, request);
            }else{
                result = loginService.loginProfessor(loginDto, request);
            }
            return ResponseEntity.ok(result);

        }catch (LoginCustomException e){
            return handlerLoginException(e);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러가 발생하였습니다.");
        }
    }

    @ExceptionHandler(LoginCustomException.class)
    public ResponseEntity<String> handlerLoginException(LoginCustomException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
