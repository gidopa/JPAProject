package Team.project.service.info;

import Team.project.dto.info.InfoDto;
import Team.project.dto.login.LoginDto;
import Team.project.entity.Professor;
import Team.project.entity.Student;
import Team.project.exception.InfoCustomException;
import Team.project.exception.SessionNotFoundException;
import Team.project.repository.login.ProLoginRepository;
import Team.project.repository.login.StudentLoginRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class InfoServiceImplTest {

    @Autowired
    private StudentLoginRepository studentLoginRepository;

    @Autowired
    private ProLoginRepository proLoginRepository;

    @Autowired
    private InfoService infoService;

    @Autowired
    private MockHttpSession mockHttpSession;

    private LoginDto loginDto;
    private InfoDto infoDto;

    @Test
    void findStudent() {
        // given
        Long id = 1L;
        loginDto = new LoginDto();
        loginDto.setId("20000101");
        loginDto.setPassword("password123");
        loginDto.setStudentId(id);

        mockHttpSession.setAttribute("loginDto", loginDto);
        MockHttpSession session = new MockHttpSession();

        // when
        InfoDto student = infoService.findStudent(id, mockHttpSession);

        // then
        assertThrows(SessionNotFoundException.class,()->{ // 세션 없을 경우 에러
           infoService.findStudent(id,session);
        });

        assertThrows(InfoCustomException.class,()->{ // 세션 id, url id 다른 경우 에러
            infoService.findStudent(2L, mockHttpSession);
        });

        assertThrows(InfoCustomException.class,()->{ // 없는 아이디 넣을 경우 에러
            infoService.findStudent(9L, mockHttpSession);
        });

        assertEquals(Long.valueOf(loginDto.getId()), student.getHakbun());
        assertEquals(loginDto.getPassword(), student.getPassword());
        assertEquals(id, student.getId());
        assertEquals("John Doe", student.getName());
    }

    @Test
    public void findProfessor() {
        // given
        Long id = 1L;
        loginDto = new LoginDto();
        loginDto.setId("33330101");
        loginDto.setPassword("password123");
        loginDto.setProfessorId(id);

        mockHttpSession.setAttribute("loginDto", loginDto);
        MockHttpSession session = new MockHttpSession();

        // when
        InfoDto professor = infoService.findProfessor(id, mockHttpSession);

        // then
        assertThrows(SessionNotFoundException.class,()->{ // 세션 없을 경우 에러
            infoService.findProfessor(id,session);
        });

        assertThrows(InfoCustomException.class,()->{ // 세션 id, url id 다른 경우 에러
            infoService.findProfessor(2L, mockHttpSession);
        });

        assertThrows(InfoCustomException.class,()->{ // 없는 아이디 넣을 경우 에러
            infoService.findProfessor(9L, mockHttpSession);
        });

        assertEquals(Long.valueOf(loginDto.getId()), professor.getHakbun());
        assertEquals(loginDto.getPassword(), professor.getPassword());
        assertEquals(id, professor.getId());
        assertEquals("pro", professor.getName());
    }

    @Test
    void studentUpdate() {
        // given
        Long id = 1L;
        infoDto = new InfoDto();
        infoDto.setId(1L);
        infoDto.setHakbun(20000101L);
        infoDto.setName("Won");
        infoDto.setPassword("12345");
        infoDto.setMajor("경영학과");
        infoDto.setCity("부산");
        infoDto.setStreet("사하");
        infoDto.setYears(2024);
        infoDto.setSemester(2);
        infoDto.setStatus("ENROLLED");

        // when
        infoService.studentUpdate(id, infoDto);
        Student student = studentLoginRepository.findByHakbun(id).orElse(null);

        // then
        assertThrows(InfoCustomException.class,()->{ // url id, infodto id 다른 경우
            infoService.studentUpdate(3L, infoDto);
        });

        assertThrows(InfoCustomException.class,()->{ // 없는 회원일 경우
            infoService.studentUpdate(9L, infoDto);
        });

        if(student == null){
            return;
        }else{
            assertEquals(student.getHakbun(), infoDto.getHakbun());
            assertEquals(student.getName(), infoDto.getName());
            assertEquals(student.getPassword(), infoDto.getPassword());
            assertEquals(student.getMajor().getName(), infoDto.getMajor());
            assertEquals(student.getAddress().getCity(), infoDto.getCity());
        }
    }

    @Test
    public void professorUpdate() {
        // given
        Long id = 1L;
        infoDto = new InfoDto();
        infoDto.setId(1L);
        infoDto.setHakbun(33330101L);
        infoDto.setName("Won");
        infoDto.setPassword("12345");
        infoDto.setCity("부산");
        infoDto.setStreet("사하");

        // when
        infoService.professorUpdate(id, infoDto);
        Professor professor = proLoginRepository.findById(id).orElse(null);

        // then
        assertThrows(InfoCustomException.class,()->{ // url id, infodto id 다른 경우
            infoService.professorUpdate(2L, infoDto);
        });

        assertThrows(InfoCustomException.class,()->{ // 없는 회원일 경우
            infoService.professorUpdate(9L, infoDto);
        });

        if(professor == null){
            return;
        }else{
            assertEquals(professor.getLoginId(), infoDto.getHakbun());
            assertEquals(professor.getName(), infoDto.getName());
            assertEquals(professor.getPassword(), infoDto.getPassword());
            assertEquals(professor.getAddress().getCity(), infoDto.getCity());
            assertEquals(professor.getAddress().getStreet(), infoDto.getStreet());
        }
    }
}