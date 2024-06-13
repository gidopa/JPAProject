package Team.project.repository.StudentHistory;

import Team.project.dto.student.StudentHistoryDto;
import Team.project.entity.Student;
import Team.project.entity.StudentHistory;
import Team.project.service.Student.StudentService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static Team.project.entity.QStudentHistory.*;

@Repository
public class StudentHistoryRepositoryImpl implements StudentHistoryRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final StudentService studentService;

    public StudentHistoryRepositoryImpl(EntityManager em, StudentService studentService) {

        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
        this.studentService = studentService;
    }

    @Override
    @Transactional
    public StudentHistoryDto updateEnrollments(StudentHistoryDto studentHistoryDto) {
        Long studentId = studentHistoryDto.getStudentId();
        Optional<Student> findStudent = studentService.findById(studentId);
        findStudent.ifPresentOrElse(
                student -> {
                    // 새로운 StudentHistory 엔티티를 생성
                    StudentHistory studentHistoryDB = new StudentHistory();
                    em.persist(studentHistoryDB); // 새로운 StudentHistory 엔티티를 저장
                    studentHistoryDB.setStudent(student); // 세션에 이미 있는 Student 엔티티를 설정
                    studentHistoryDB.setNewStatus(studentHistoryDto.getNewStatus());
                    studentHistoryDB.setUpdatedDate(studentHistoryDto.getUpdatedDate());
                    studentHistoryDB.setOldStatus(studentHistoryDto.getOldStatus()); // 기존 상태 설정
                    studentHistoryDB.setReason(studentHistoryDto.getReason());
                    studentHistoryDB.setStartDate(studentHistoryDto.getStartDate());
                },
                () -> {
                    throw new IllegalArgumentException("Student Not Found");
                }
        );
        return studentHistoryDto;

    }
}
