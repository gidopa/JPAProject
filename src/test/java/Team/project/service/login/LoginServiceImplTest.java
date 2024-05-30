package Team.project.service.login;

import Team.project.dto.login.LoginDto;
import Team.project.exception.LoginCustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class LoginServiceImplTest {

    @Autowired
    LoginService loginService;

    @Test
    @DisplayName("학생 로그인")
    void loginStudent() {
        // given
        HttpServletRequest request = new MockHttpServletRequest();
        LoginDto loginDto = new LoginDto();
        loginDto.setId("20000101");
        loginDto.setPassword(""); // 틀린 비밀번호

        LoginDto loginDto1 = new LoginDto();
        loginDto1.setId("20000101");
        loginDto1.setPassword("password123"); // 맞는 비밀번호

        // when
        loginService.loginStudent(loginDto1, request);
        HttpSession session = request.getSession();
        LoginDto result = (LoginDto) session.getAttribute("loginDto");

        // then
        assertThrows(LoginCustomException.class,()->{ // 틀린 비밀번호 에러 발생 확인
            loginService.loginStudent(loginDto, request);
        });

        Assertions.assertThat(result.getId()).isEqualTo(loginDto1.getId());
        Assertions.assertThat(result.getPassword()).isEqualTo(loginDto1.getPassword());
    }

    @Test
    @DisplayName("교수 로그인")
    void loginProfessor() {
        // given
        HttpServletRequest request = new MockHttpServletRequest();
        LoginDto loginDto = new LoginDto();
        loginDto.setId("33330101");
        loginDto.setPassword(""); // 틀린 비밀번호

        LoginDto loginDto1 = new LoginDto();
        loginDto1.setId("33330101");
        loginDto1.setPassword("password123"); // 맞는 비밀번호

        // when
        loginService.loginProfessor(loginDto1, request);
        HttpSession session = request.getSession();
        LoginDto result = (LoginDto) session.getAttribute("loginDto");

        // then
        assertThrows(LoginCustomException.class,()->{ // 틀린 비밀번호 에러 발생 확인
            loginService.loginStudent(loginDto, request);
        });

        Assertions.assertThat(result.getId()).isEqualTo(loginDto1.getId());
        Assertions.assertThat(result.getPassword()).isEqualTo(loginDto1.getPassword());
    }
}