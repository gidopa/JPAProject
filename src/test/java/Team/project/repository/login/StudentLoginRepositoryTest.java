package Team.project.repository.login;

import Team.project.entity.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

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
        String password = "password123";

        // when
        Student student = studentLoginRepository.findByHakbun(hakbun).orElse(null);

        // then
        if(student == null){
            return;
        }else {
            System.out.println(student.getHakbun());
            assertThat(student.getHakbun()).isEqualTo(hakbun);
            assertThat(student.getId()).isEqualTo(1L);
            assertThat(student.getPassword()).isEqualTo(password);
        }
    }

    @Test
    @DisplayName("fetch join 조회")
    public void findStudentById() throws Exception{
        // given
        Long id = 1L;
        Long hakbun = 20000101L;
        String city  = "Cityville";
        int years = 2024;

        // when
        Student student = studentLoginRepository.findStudentById(id).orElse(null);

        // then
        if(student == null){
            return;
        }else{
            assertThat(student.getId()).isEqualTo(id);
            assertThat(student.getHakbun()).isEqualTo(hakbun);
            assertThat(student.getAddress().getCity()).isEqualTo(city);
            assertThat(student.getSemesterInfo().getYears()).isEqualTo(years);
        }
    }

    @Test
    @DisplayName("학번, id 조회")
    public void findByHakbunAndId() {
        // given
        Long id = 1L;
        Long hakbun = 20000101L;
        String city  = "Cityville";
        int years = 2024;

        // when
        Student student = studentLoginRepository.findByHakbunAndId(hakbun, id).orElse(null);

        // then
        if(student == null){
            fail("실패");
        }else {
            assertThat(student.getId()).isEqualTo(id);
            assertThat(student.getHakbun()).isEqualTo(hakbun);
            assertThat(student.getAddress().getCity()).isEqualTo(city);
            assertThat(student.getSemesterInfo().getYears()).isEqualTo(years);
        }
    }
}