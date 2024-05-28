package Team.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Credit { // 평균 학점

    @Id
    @GeneratedValue
    @Column(name = "credit_id")
    private String id;

    @OneToOne(mappedBy = "credit")
    private Student student;

    private float credit; // 평균 학점
}
