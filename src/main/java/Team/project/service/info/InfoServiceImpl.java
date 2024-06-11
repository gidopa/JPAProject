package Team.project.service.info;

import Team.project.dto.info.InfoDto;
import Team.project.dto.login.LoginDto;
import Team.project.entity.Professor;
import Team.project.entity.Student;
import Team.project.exception.InfoCustomException;
import Team.project.exception.SessionNotFoundException;
import Team.project.repository.login.ProLoginRepository;
import Team.project.repository.login.StudentLoginRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InfoServiceImpl implements InfoService{

    private final StudentLoginRepository studentLoginRepository;
    private final ProLoginRepository proLoginRepository;

    /* 학생 정보 수정 뷰 */
    @Override
    public InfoDto findStudent(Long id, HttpSession session) {

        LoginDto loginDto = (LoginDto) session.getAttribute("loginDto");
        if(loginDto == null){
            log.warn("미로그인 상태 - 세션 없음 : {}", loginDto);
            throw new SessionNotFoundException("로그인이 되지 않았습니다. 로그인 하세요");
        }

        if(id != loginDto.getStudentId()){
            log.warn("Uri, 세션 id 불일치 - Uri.id: {}, LoginDto.id: {}", id, loginDto.getStudentId());
            throw new InfoCustomException("허용되지 않은 url 입니다.");
        }

        Student student = studentLoginRepository.findStudentById(id).orElse(null);
        if(student == null){
            log.warn("없는 회원 조회 - Uri.id: {}", id);
            throw new InfoCustomException("없는 회원입니다.");
        }

        InfoDto infoDto = new InfoDto(student.getId(), student.getHakbun(), student.getName(), student.getPassword(), student.getMajor().getName(), student.getAddress().getCity(),
                student.getAddress().getStreet(), student.getSemesterInfo().getYears(), student.getSemesterInfo().getSemester());


        return infoDto;
    }

    /* 교수 정보 수정 뷰 */
    @Override
    public InfoDto findProfessor(Long id, HttpSession session) {

        LoginDto loginDto = (LoginDto) session.getAttribute("loginDto");
        if(loginDto == null){
            log.warn("미로그인 상태 - 세션 없음 : {}", loginDto);
            throw new SessionNotFoundException("로그인이 되지 않았습니다. 로그인 하세요");
        }

        if(id != loginDto.getProfessorId()){
            log.warn("Uri, 세션 id 불일치 - Uri.id: {}, LoginDto.id: {}", id, loginDto.getProfessorId());
            throw new InfoCustomException("허용되지 않은 url 입니다.");
        }

        Professor professor = proLoginRepository.findById(id).orElse(null);
        if(professor == null){
            log.warn("없는 회원 조회 - Uri.id: {}", id);
            throw new InfoCustomException("없는 회원입니다.");
        }

        InfoDto infoDto = new InfoDto(professor.getId(), professor.getLoginId(), professor.getName(), professor.getPassword(), professor.getMajor().getName(), professor.getAddress().getCity(), professor.getAddress().getStreet());

        return infoDto;
    }

    /* 학생 정보 수정 */
    @Override
    @Transactional
    public InfoDto studentUpdate(Long id, InfoDto infoDto) {

        if(id != infoDto.getId()){
            log.warn("Uri / infoDto.id 불일치 - Uri.id: {}, infoDto.id: {}", id, infoDto.getId());
            throw new InfoCustomException("member id와 url id 가 일치하지 않습니다.");
        }

        Student student = studentLoginRepository.findByHakbunAndId(infoDto.getHakbun(), infoDto.getId()).orElse(null);

        if(student == null){
            log.warn("학생 학번, pk 조회 실패 - infoDto.hakbun: {}, infoDto.id: {}", infoDto.getHakbun(), infoDto.getId());
            throw new InfoCustomException("없는 회원입니다.");
        }
        student.updateStudent(infoDto.getName(), infoDto.getPassword(), infoDto.getCity(), infoDto.getStreet());

        infoDto.setName(student.getName());
        infoDto.setPassword(student.getPassword());
        infoDto.setCity(student.getAddress().getCity());
        infoDto.setStreet(student.getAddress().getStreet());

        return infoDto;
    }

    /* 교수 정보 수정 */
    @Override
    @Transactional
    public InfoDto professorUpdate(Long id, InfoDto infoDto) {

        if(id != infoDto.getId()){
            log.warn("Uri / infoDto.id 불일치 - Uri.id: {}, infoDto.id: {}", id, infoDto.getId());
            throw new InfoCustomException("member id와 url id 가 일치하지 않습니다.");
        }

        Professor professor = proLoginRepository.findByLoginIdAndId(infoDto.getHakbun(), infoDto.getId()).orElse(null);

        if(professor == null){
            log.warn("교수 로그인 ID, pk 조회 실패 - infoDto.hakbun: {}, infoDto.id: {}", infoDto.getHakbun(), infoDto.getId());
            throw new InfoCustomException("없는 회원입니다.");
        }

        professor.updateProfessor(infoDto.getName(), infoDto.getPassword(), infoDto.getCity(), infoDto.getStreet());

        infoDto.setName(professor.getName());
        infoDto.setPassword(professor.getPassword());
        infoDto.setCity(professor.getAddress().getCity());
        infoDto.setStreet(professor.getAddress().getStreet());

        return infoDto;
    }
}
