package Team.project.service.login;

import Team.project.dto.login.LoginDto;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {

    // 학생 로그인
    void loginStudent(LoginDto loginDto, HttpServletRequest request);

    // 교수 로그인
    void loginProfessor(LoginDto loginDto, HttpServletRequest request);
}
