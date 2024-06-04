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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ApiLoginController.class)
class ApiLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Autowired
    private ObjectMapper objectMapper;

    private LoginDto loginDto;

    @Test
    @DisplayName("200 테스트")
    void loginSuccess() throws Exception{
        // given
        loginDto = new LoginDto();
        loginDto.setId("20000101");
        loginDto.setPassword("password123");

        // when
        Mockito.when(loginService.loginStudent(any(LoginDto.class), any(MockHttpServletRequest.class)))
                .thenReturn(loginDto);

        // then
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("400 테스트")
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
                .andExpect(content().string("없는 ID 입니다. 다시 확인해주세요"));
    }


}