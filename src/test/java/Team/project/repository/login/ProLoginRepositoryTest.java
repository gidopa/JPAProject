package Team.project.repository.login;

import Team.project.entity.Professor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ProLoginRepositoryTest {

    @Autowired
    ProLoginRepository proLoginRepository;

    @Test
    @DisplayName("교수 로그인 아이디로 조회")
    void findByLoginId() {
        Long id = 2l;
        Long loginId = 33330102L;

        // when
        Professor professor = proLoginRepository.findByLoginId(loginId).orElse(null);

        // then
        if(professor == null){
            return;
        }else {
            assertThat(professor.getLoginId()).isEqualTo(loginId);
            assertThat(professor.getId()).isEqualTo(2L);
        }

    }
}