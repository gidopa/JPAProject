package Team.project.repository.studentRecord;

import Team.project.dto.studentRecord.StudentHistoryDto;
import Team.project.entity.Student;
import Team.project.repository.login.StudentLoginRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class StudentHistoryRepositoryImplTest {

    @Autowired StudentHistoryRepository studentHistoryRepository;
    @Autowired StudentLoginRepository studentLoginRepository;

    @Test
    @DisplayName("학생 휴복학 상태 조회")
    public void findStudentHistories() throws Exception{
        // given
        Long id = 1L;
        StudentHistoryDto studentHistoryDto = new StudentHistoryDto(7L, 1L, 20000101L, "John Doe", "ENROLLED", "ONLEAVE", LocalDate.of(2023,2,1));
        
        // when
        Student student = studentLoginRepository.findById(id).orElse(null);
        List<StudentHistoryDto> studentHistories = studentHistoryRepository.findStudentHistories(student);

        // then
        assertThat(studentHistories.size()).isEqualTo(2);
        assertThat(studentHistories.get(0)).isEqualToComparingFieldByField(studentHistoryDto);
        StudentHistoryDto result = studentHistories.get(0);
        assertThat(result.getStudent_history_id()).isEqualTo(studentHistoryDto.getStudent_history_id());
        assertThat(result.getStudent_id()).isEqualTo(studentHistoryDto.getStudent_id());
        assertThat(result.getHakbun()).isEqualTo(studentHistoryDto.getHakbun());
        assertThat(result.getName()).isEqualTo(studentHistoryDto.getName());
        assertThat(result.getOld_status()).isEqualTo(studentHistoryDto.getOld_status());
        assertThat(result.getNew_status()).isEqualTo(studentHistoryDto.getNew_status());
        assertThat(result.getUpdated_date()).isEqualTo(studentHistoryDto.getUpdated_date());
    }
}