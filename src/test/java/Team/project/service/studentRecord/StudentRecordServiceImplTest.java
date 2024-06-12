package Team.project.service.studentRecord;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.login.LoginDto;
import Team.project.dto.studentRecord.StudentCreditDto;
import Team.project.dto.studentRecord.StudentHistoryDto;
import Team.project.entity.GradeType;
import Team.project.exception.SessionNotFoundException;
import Team.project.exception.StudentRecordCustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class StudentRecordServiceImplTest {

    @Autowired StudentRecordService studentRecordService;
    @Autowired MockHttpSession mockHttpSession;


    @Test
    @DisplayName("학생 휴복학 조회")
    void studentRecord() throws Exception{
        // given
        Long id = 1L;
        LoginDto loginDto = new LoginDto();
        loginDto.setId("20000101");
        loginDto.setPassword("password123");
        loginDto.setStudentId(id);

        StudentHistoryDto dto = new StudentHistoryDto(7L, 1L, 20000101L, "John Doe", "ENROLLED", "ONLEAVE", LocalDate.of(2023,2,1));

        mockHttpSession.setAttribute("loginDto", loginDto);
        MockHttpSession session = new MockHttpSession();

        // when
        List<StudentHistoryDto> result = studentRecordService.studentRecord(id, mockHttpSession);

        // then
        Assertions.assertThrows(SessionNotFoundException.class, ()->{ // 세션 없을 경우 에러
            studentRecordService.studentRecord(id, session);
        });

        Assertions.assertThrows(StudentRecordCustomException.class, ()->{ // uri, 세션 다른 경우 에러
            studentRecordService.studentRecord(2L, mockHttpSession);
        });

        Assertions.assertEquals(dto.getStudent_history_id(), result.get(0).getStudent_history_id());
        Assertions.assertEquals(dto.getStudent_id(), result.get(0).getStudent_id());
        Assertions.assertEquals(dto.getHakbun(), result.get(0).getHakbun());
        Assertions.assertEquals(dto.getName(), result.get(0).getName());
        Assertions.assertEquals(dto.getOld_status(), result.get(0).getOld_status());
        Assertions.assertEquals(dto.getNew_status(), result.get(0).getNew_status());
        Assertions.assertEquals(dto.getUpdated_date(), result.get(0).getUpdated_date());

    }

    @Test
    @DisplayName("학생 학점 조회")
    void selectCredit() throws Exception{
        // given
        Long id = 1L;
        Float credit = 4.5f;
        GradeDto dto = new GradeDto(1L, 1L, "John Doe", "JAVA", 90.0, 90.0, 90.0, GradeType.APLUS);
        List<GradeDto> gradeDtos = new ArrayList<>();
        gradeDtos.add(dto);
        // when

        StudentCreditDto result = studentRecordService.selectCredit(id, gradeDtos);


        // then
        Assertions.assertThrows(StudentRecordCustomException.class,()->{ // 없는 회원 조회
            studentRecordService.selectCredit(200L, gradeDtos);
        });

        Assertions.assertEquals(result.getCredit(), credit);
    }

}