package Team.project.api.login;

import Team.project.dto.login.LoginDto;
import Team.project.exception.LoginCustomException;
import Team.project.service.login.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(ApiLoginController.class)
class ApiLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("학생 로그인 성공 테스트")
    void loginSuccess() throws Exception{
        // given
        LoginDto loginDto = new LoginDto();
        loginDto.setId("20000101");
        loginDto.setPassword("password123");

        LoginDto returnedDto = new LoginDto();
        returnedDto.setId("20000101");
        returnedDto.setPassword("password123");
        returnedDto.setStudentId(1L);

        // when
        Mockito.when(loginService.loginStudent(any(LoginDto.class), any(MockHttpServletRequest.class)))
                .thenReturn(returnedDto);

        // then
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(returnedDto)));

    }

    @Test
    @DisplayName("학생 로그인 실패 테스트 - 없는 ID")
    public void badLogin() throws Exception {
        // given
        LoginDto loginDto = new LoginDto();
        loginDto.setId("20000000");
        loginDto.setPassword("password123");

        // when
        Mockito.when(loginService.loginStudent(any(LoginDto.class), any(MockHttpServletRequest.class)))
                        .thenThrow(new LoginCustomException("없는 ID 입니다. 다시 확인해주세요"));

        // then
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("없는 ID 입니다. 다시 확인해주세요"));
    }

    @Test
    @DisplayName("학생 로그인 실패 테스트 - 비밀번호 불일치")
    public void badLogin2() throws Exception {
        // given
        LoginDto loginDto = new LoginDto();
        loginDto.setId("20000101");
        loginDto.setPassword("wrongpassword");

        Mockito.when(loginService.loginStudent(any(LoginDto.class), any(MockHttpServletRequest.class)))
                .thenThrow(new LoginCustomException("비밀번호가 일치하지 않습니다."));

        // when & then
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("비밀번호가 일치하지 않습니다."));
    }


}