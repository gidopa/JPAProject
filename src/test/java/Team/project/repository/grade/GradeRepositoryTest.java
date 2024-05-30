package Team.project.repository.grade;

import Team.project.dto.grade.GradeDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class GradeRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    GradeRepository gradeRepository;


}