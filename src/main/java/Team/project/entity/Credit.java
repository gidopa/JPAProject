package Team.project.entity;

import Team.project.dto.grade.GradeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Credit { // 평균 학점

    @Id
    @GeneratedValue
    @Column(name = "credit_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private float credit; // 평균 학점

    public void addCredit(List<GradeDto> gradeDtos){
        float sum = 0;

        for (GradeDto gradeDto : gradeDtos) {
            if(gradeDto.getGradeType().name().equals("APLUS")){
                sum += 4.5f;
            }else if(gradeDto.getGradeType().name().equals("AZERO")){
                sum += 4.0f;
            }else if(gradeDto.getGradeType().name().equals("BPLUS")){
                sum += 3.5f;
            }else if(gradeDto.getGradeType().name().equals("BZERO")){
                sum += 3.0f;
            }else if(gradeDto.getGradeType().name().equals("CPLUS")){
                sum += 2.5f;
            }else if(gradeDto.getGradeType().name().equals("CZERO")){
                sum += 2.0f;
            }else if(gradeDto.getGradeType().name().equals("DPLUS")){
                sum += 1.5f;
            }else if(gradeDto.getGradeType().name().equals("DZERO")){
                sum += 1.0f;
            }else if(gradeDto.getGradeType().name().equals("F")){
                sum += 0;
            }
        }
        this.credit = sum / gradeDtos.size();
    }
}
