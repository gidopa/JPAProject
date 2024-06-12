package Team.project.api.info;

import Team.project.dto.info.InfoDto;
import Team.project.dto.login.LoginDto;
import Team.project.exception.InfoCustomException;
import Team.project.service.info.InfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(ApiInfoController.class)
class ApiInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfoService infoService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("학생 정보 반환 - 200 테스트")
    public void successInfo() throws Exception{
        // given
        MockHttpSession session = new MockHttpSession();
        LoginDto loginDto = new LoginDto();
        loginDto.setId("20000101");
        loginDto.setStudentId(1L);
        session.setAttribute("loginDto", loginDto);

        InfoDto infoDto = new InfoDto(1L, 20000101L, "John Doe", "password123", "컴퓨터공학", "Cityville", "123 Main St", 2024, 2);

        Mockito.when(infoService.findStudent(Mockito.anyLong(), Mockito.any(HttpSession.class)))
                .thenReturn(infoDto);


        // when & then
        mockMvc.perform(get("/student/info/1")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.hakbun").value(20000101L))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.major").value("컴퓨터공학"));
    }

    @Test
    @DisplayName("학생 정보 반환 - 400 테스트")
    public void badInfo() throws Exception{
        // given
        Long id = 1L;

        // when
        Mockito.when(infoService.findStudent(Mockito.anyLong(), Mockito.any(HttpSession.class)))
                .thenThrow(new InfoCustomException("허용되지 않은 url 입니다."));

        // then
        mockMvc.perform(get("/student/info/"+id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("허용되지 않은 url 입니다."))
                .andExpect(jsonPath("$.status").value(400));
    }



    @Test
    @DisplayName("학생 정보 수정 200 테스트")
    void successUpdate() throws Exception{
        // given
        Long id = 1L;

        InfoDto infoDto = new InfoDto();
        infoDto.setId(id);
        infoDto.setHakbun(20000101L);
        infoDto.setName("Come");
        infoDto.setPassword("a b c");
        infoDto.setCity("부산");
        infoDto.setStreet("사하구");

        // when
        Mockito.when(infoService.studentUpdate(Mockito.anyLong(), Mockito.any(InfoDto.class)))
                .thenReturn(infoDto);

        // then
        mockMvc.perform(patch("/student/info/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(infoDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Come"))
                .andExpect(jsonPath("$.password").value("a b c"));
    }

    @Test
    @DisplayName("학생 정보 수정 - 400 테스트")
    public void badUpdate() throws Exception{
        // given
        Long id = 1L;

        InfoDto infoDto = new InfoDto();
        infoDto.setId(2L);
        infoDto.setHakbun(20000101L);
        infoDto.setName("Come");
        infoDto.setPassword("a b c");
        infoDto.setCity("부산");
        infoDto.setStreet("사하구");

        // when
        Mockito.when(infoService.studentUpdate(Mockito.anyLong(), Mockito.any(InfoDto.class)))
                .thenThrow(new InfoCustomException("member id와 url id 가 일치하지 않습니다."));

        // then
        mockMvc.perform(patch("/student/info/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(infoDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("member id와 url id 가 일치하지 않습니다."))
                .andExpect(jsonPath("$.status").value(400));

    }


}