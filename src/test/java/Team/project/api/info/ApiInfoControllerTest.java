package Team.project.api.info;

import Team.project.dto.info.InfoDto;
import Team.project.exception.InfoCustomException;
import Team.project.service.info.InfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ApiInfoController.class)
class ApiInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfoService infoService;

    @Autowired
    private ObjectMapper objectMapper;

    private InfoDto infoDto;

    @Test
    @DisplayName("200 테스트")
    void suceessUpdate() throws Exception{
        // given
        infoDto = new InfoDto();
        Long id = 1L;
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
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("400 테스트")
    public void badUpdate() throws Exception{
        // given
        infoDto = new InfoDto();
        Long id = 1L;
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
                .andExpect(content().string("member id와 url id 가 일치하지 않습니다."));

    }




}