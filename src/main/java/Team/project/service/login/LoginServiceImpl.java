package Team.project.service.login;

import Team.project.dto.login.LoginDto;
import Team.project.entity.Professor;
import Team.project.entity.Student;
import Team.project.exception.LoginCustomException;
import Team.project.repository.login.ProLoginRepository;
import Team.project.repository.login.StudentLoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{

    private final StudentLoginRepository studentLoginRepository;
    private final ProLoginRepository proLoginRepository;

    // 학생 로그인
    @Override
    public LoginDto loginStudent(LoginDto loginDto, HttpServletRequest request) {
        Long hakbun = Long.valueOf(loginDto.getId());

        Student student = studentLoginRepository.findByHakbun(hakbun).orElse(null);

        if(student == null){
            throw new LoginCustomException("없는 ID 입니다. 다시 확인해주세요");
        }

        if (!student.getPassword().equals(loginDto.getPassword())){
            throw new LoginCustomException("비밀번호가 일치하지 않습니다.");
        }
        loginDto.setStudentId(student.getId());

        HttpSession session = request.getSession();
        session.setAttribute("loginDto", loginDto);
        return loginDto;
    }

    // 교수 로그인
    @Override
    public LoginDto loginProfessor(LoginDto loginDto, HttpServletRequest request) {
        Long loginId = Long.valueOf(loginDto.getId());

        Professor professor = proLoginRepository.findByLoginId(loginId).orElse(null);

        if(professor == null){
            throw new LoginCustomException("없는 ID 입니다. 다시 확인해주세요");
        }

        if(!professor.getPassword().equals(loginDto.getPassword())){
            throw new LoginCustomException("비밀번호가 일치하지 않습니다.");
        }
        loginDto.setProfessorId(professor.getId());

        HttpSession session = request.getSession();
        session.setAttribute("loginDto", loginDto);

        return loginDto;
    }
}
