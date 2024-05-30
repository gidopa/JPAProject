package Team.project;

import Team.project.entity.*;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitDataService initDataService;

    @PostConstruct
    public void init(){
        initDataService.init();
    }

    @Component
    static class InitDataService{

        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init(){
            Major computer = new Major("computer", new ArrayList<>());
            Professor p1 = new Professor("p1", computer);
            Professor p2 = new Professor("p2", computer);
            p1.changeMajor(computer);
            p2.changeMajor(computer);
            Course java = new Course("JAVA", p1, 0.3, 0.3, 0.4, 3, Category.MAJOR);
            Course AI = new Course("AI", p1, 0.3, 0.3, 0.4, 3, Category.MAJOR);


            em.persist(computer);
            em.persist(p1);
            em.persist(java);
            em.persist(p2);
            em.persist(AI);
            List<Enroll> enrolls = new ArrayList<>();

            for (int i = 1; i < 20; i++) {
                Student student = new Student("student"+i, computer, Long.parseLong(String.valueOf(20240000 + i)));
                Enroll enroll = new Enroll(student, java, null);
                Assessment assessment = new Assessment(i + 80, i + 80, i + 80, enroll);
                double totalScore = assessment.calculateTotalScore();
                enrolls.add(enroll);
                assessment.changeAssessment(totalScore, enroll);
                em.persist(student);
                em.persist(enroll);
                em.persist(assessment);
            }

            List<Enroll> enrolls2 = new ArrayList<>();
            for (int j = 30; j < 50; j++) {
                Student student = new Student("student"+j, computer, Long.parseLong(String.valueOf(20240000 + j)));
                Enroll enroll = new Enroll(student, AI, null);
                Assessment assessment = new Assessment(j + 80, j + 80, j + 80, enroll);
                double totalScore = assessment.calculateTotalScore();
                enrolls2.add(enroll);
                assessment.changeAssessment(totalScore, enroll);
                em.persist(student);
                em.persist(enroll);
                em.persist(assessment);
            }
            Enroll.assignGrade(enrolls2);
            Enroll.assignGrade(enrolls);





        }
    }
}
