package Team.project.service.studentRecord;

import Team.project.dto.grade.GradeDto;
import Team.project.dto.login.LoginDto;
import Team.project.dto.studentRecord.StudentCreditDto;
import Team.project.dto.studentRecord.StudentHistoryDto;
import Team.project.entity.Student;
import Team.project.exception.SessionNotFoundException;
import Team.project.exception.StudentRecordCustomException;
import Team.project.repository.login.StudentLoginRepository;
import Team.project.repository.studentRecord.StudentRecordRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentRecordServiceImpl implements StudentRecordService {

    private final StudentRecordRepository studentRecordRepository;
    private final StudentLoginRepository studentLoginRepository;

    /* 학생 휴복학 조회 */
    @Override
    public List<StudentHistoryDto> studentRecord(Long id, HttpSession session) {

        LoginDto loginDto = (LoginDto) session.getAttribute("loginDto");
        if(loginDto == null){
            log.warn("미로그인 상태 - 세션 없음 : {}", loginDto);
            throw new SessionNotFoundException("로그인이 되지 않았습니다. 로그인 하세요");
        }

        if(id != loginDto.getStudentId()){
            log.warn("Uri, 세션 id 불일치 - Uri.id: {}, LoginDto.id: {}", id, loginDto.getStudentId());
            throw new StudentRecordCustomException("허용되지 않은 url 입니다.");
        }
        Student student = studentLoginRepository.findStudentById(id)
                .orElseThrow(() -> new StudentRecordCustomException("회원이 존재하지 않습니다."));

        return studentRecordRepository.findStudentHistories(student);
    }

    /* 학생 학점 조회 */
    @Override
    @Transactional
    public StudentCreditDto selectCredit(Long id, List<GradeDto> gradeDtos) {

        Student findStudent = studentLoginRepository.findStudentById(id)
                .orElseThrow(() -> new StudentRecordCustomException("조회된 학생이 없습니다."));

        findStudent.getCredit().addCredit(gradeDtos);

        log.info("학점 - {}", findStudent.getCredit().getCredit());

        return new StudentCreditDto(findStudent.getId(), findStudent.getCredit().getId(), findStudent.getCredit().getCredit());
    }
}
