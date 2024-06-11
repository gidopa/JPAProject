package Team.project.repository.studentRecord;

import Team.project.dto.studentRecord.QStudentHistoryDto;
import Team.project.dto.studentRecord.StudentHistoryDto;
import Team.project.entity.Student;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static Team.project.entity.QStudent.student;
import static Team.project.entity.QStudentHistory.studentHistory;

@Repository
public class StudentHistoryRepositoryImpl implements StudentHistoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StudentHistoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<StudentHistoryDto> findStudentHistories(Student entity) {

        return queryFactory
                .select(new QStudentHistoryDto(
                        studentHistory.id,
                        student.id,
                        student.hakbun,
                        student.name,
                        studentHistory.oldStatus.stringValue(),
                        studentHistory.newStatus.stringValue(),
                        studentHistory.updatedDate))
                .from(studentHistory)
                .join(studentHistory.student, student)
                .where(student.eq(entity))
                .orderBy(studentHistory.updatedDate.desc())
                .fetch();
    }
}
