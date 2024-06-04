package Team.project.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Comparator;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"totalScore"})
public class Assessment {

    @Id
    @GeneratedValue
    @Column(name = "assessment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enroll_id")
    private Enroll enroll;


    private Double midTermScore; // 중간고사 점수
    private Double finalTermScore; // 기말고사 점수
    private Double reportScore; // 레포트 점수
    private Double totalScore ;

    public void setEnroll(Enroll enroll) {
        this.enroll = enroll;
    }

    public void changeAssessment(Double totalScore, Enroll enroll){
        this.totalScore = totalScore;
        enroll.changeAssessment(this);
    }

    public Assessment(double midTermScore, double finalTermScore, double reportScore, Enroll enroll) {
        this.midTermScore = midTermScore;
        this.finalTermScore = finalTermScore;
        this.reportScore = reportScore;
        this.enroll = enroll;
    }

    public double calculateTotalScore() {
        double midTermWeight = enroll.getCourse().getMidTermWeight();
        double finalTermWeight = enroll.getCourse().getFinalTermWeight();
        double reportWeight = enroll.getCourse().getReportWeight();
        double totalScore = (midTermScore * midTermWeight) +
                (finalTermScore * finalTermWeight) +
                (reportScore * reportWeight);
        this.totalScore = totalScore;
        return totalScore;
    }


}