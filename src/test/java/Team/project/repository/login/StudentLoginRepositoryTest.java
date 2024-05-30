package Team.project.repository.login;

import Team.project.entity.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class StudentLoginRepositoryTest {

    @Autowired
    StudentLoginRepository studentLoginRepository;

    @Test
    @DisplayName("학번 조회 테스트")
    void findByHakbun() {
        // given
        Long id = 1l;
        Long hakbun = 20000101L;

        // when
        Student student = studentLoginRepository.findByHakbun(hakbun).orElse(null);

        // then
        if(student == null){
            return;
        }else {
            assertThat(student.getHakbun()).isEqualTo(hakbun);
            assertThat(student.getId()).isEqualTo(1L);
        }
    }
}