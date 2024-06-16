package Team.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"student", "oldStatus", "newStatus", "updatedDate", "startDate", "reason"})
public class StudentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "old_status")
    private StudentStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "new_status")
    private StudentStatus newStatus;

    private String reason;
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "updated_date")
    private LocalDate updatedDate;


    public StudentHistory(Student student, StudentStatus oldStatus, StudentStatus newStatus) {
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        setStudent(student); // 연관관계 설정
        this.updatedDate = LocalDate.now();
    }

    /* 연관관계 메서드 */
    public void setStudent(Student student) {
        if (this.student != null) {
            this.student.getStudentHistories().remove(this);
        }
        this.student = student;
        if (student != null && !student.getStudentHistories().contains(this)) {
            student.getStudentHistories().add(this);
        }
    }

}
