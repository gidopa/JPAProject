package Team.project.api.studentRecord;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.info.InfoDto;
import Team.project.dto.login.LoginDto;
import Team.project.dto.studentRecord.StudentCreditDto;
import Team.project.dto.studentRecord.StudentHistoryDto;
import Team.project.dto.studentRecord.StudentRecordResponse;
import Team.project.entity.GradeType;
import Team.project.exception.SessionNotFoundException;
import Team.project.exception.StudentRecordCustomException;
import Team.project.service.grade.GradeService;
import Team.project.service.info.InfoService;
import Team.project.service.studentRecord.StudentRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(ApiStudentRecordController.class)
public class ApiStudentRecordControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean StudentRecordService studentRecordService;
    @MockBean GradeService gradeService;
    @MockBean InfoService infoService;

    @Test
    @DisplayName("학생 학적 조회 성공 테스트")
    public void studentRecordSuccess() throws Exception{
        // given
        MockHttpSession session = new MockHttpSession();
        LoginDto loginDto = new LoginDto();
        loginDto.setId("20000101");
        loginDto.setStudentId(1L);
        session.setAttribute("loginDto", loginDto);

        Long studentId = 1L;

        List<StudentHistoryDto> studentHistoryDtos = Arrays.asList(
                new StudentHistoryDto(7L, 1L, 20000101L, "John Doe", "ENROLLED", "ONLEAVE", LocalDate.of(2023,2,1)),
                new StudentHistoryDto(1L, 1L, 20000101L, "John Doe", "ENROLLED", "ENROLLED", LocalDate.of(2023,1,1))
        );

        List<GradeDto> gradeDtos = Arrays.asList(
                new GradeDto(1L, 1L, "John Doe", "JAVA", 90.0, 90.0, 90.0, GradeType.APLUS),
                new GradeDto(1L, 2L, "John Doe", "Python", 98.0, 97.0, 90.0, GradeType.APLUS)
        );

        StudentCreditDto studentCreditDto = new StudentCreditDto(1L, 1L, 4.5f);
        InfoDto infoDto = new InfoDto(1L, 20000101L, "John Doe", "password123", "컴퓨터공학", "Cityville", "123 Main St", 2024, 2, null);

        StudentRecordResponse response = new StudentRecordResponse(studentHistoryDtos, gradeDtos, studentCreditDto, infoDto);

        given(studentRecordService.studentRecord(studentId, session)).willReturn(studentHistoryDtos);
        given(gradeService.findAllGradeByStudentId(studentId)).willReturn(gradeDtos);
        given(studentRecordService.selectCredit(studentId, gradeDtos)).willReturn(studentCreditDto);
        given(infoService.findStudent(studentId, session)).willReturn(infoDto);

        // when & then
        mockMvc.perform(get("/student/record/{id}", studentId)
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    @DisplayName("학적 조회 예외 테스트1")
    public void studentRecordFailed1() throws Exception{
        // given
        Long id = 1L;
        MockHttpSession session = new MockHttpSession();

        given(studentRecordService.studentRecord(id, session)).willThrow(new SessionNotFoundException("로그인이 되지 않았습니다. 로그인 하세요"));

        // when & then
        mockMvc.perform(get("/student/record/{id}", id)
                        .session(session))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("로그인이 되지 않았습니다. 로그인 하세요"))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("학적 조회 예외 테스트1")
    public void studentRecordFailed2() throws Exception{
        // given
        Long id = 2L;

        MockHttpSession session = new MockHttpSession();
        LoginDto loginDto = new LoginDto();
        loginDto.setId("20000101");
        loginDto.setStudentId(1L);
        session.setAttribute("loginDto", loginDto);

        given(studentRecordService.studentRecord(id, session)).willThrow(new StudentRecordCustomException("허용되지 않은 url 입니다."));

        // when & then
        mockMvc.perform(get("/student/record/{id}", id).session(session))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("허용되지 않은 url 입니다."))
                .andExpect(jsonPath("$.status").value(400));

    }
}
