package Team.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @Column(unique = true)
    private Long hakbun;
    private String name;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Enroll> enrollments = new ArrayList<>();

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Credit credit;

    @Embedded
    private SemesterInfo semesterInfo;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentHistory> studentHistories = new ArrayList<>();

    public Student(String name, Major major, Long hakbun,  String password) {
        this.hakbun = hakbun;
        this.name = name;
        this.password = password;
        this.major = major;
    }

    public void updateStudent(String name, String password, String city, String street){
        this.name = name;
        this.password = password;
        this.address.updateAddress(city, street);
    }

    /* 마지막 저장된 상태 조회 */
    public StudentStatus getLastStatus(){
        return studentHistories.stream()
                .max(Comparator.comparing(StudentHistory::getId))
                .map(StudentHistory::getNewStatus)
                .orElse(StudentStatus.ENROLLED);
    }


}


