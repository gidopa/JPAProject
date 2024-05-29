package Team.project.service.Student;

import Team.project.entity.Student;
import Team.project.repository.Student.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> findById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public Optional<Student> findByHakbun(Long hakbun) {
        return studentRepository.findByHakbun(hakbun);
    }
}
