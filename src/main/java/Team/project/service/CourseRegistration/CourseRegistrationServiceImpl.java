package Team.project.service.CourseRegistration;

import Team.project.entity.Course;
import Team.project.entity.Student;
import Team.project.entity.CourseRegistration;
import Team.project.repository.Course.CourseRepository;
import Team.project.repository.CourseRegistration.CourseRegistrationRepository;
import Team.project.repository.CourseRepository;
import Team.project.repository.Student.StudentRepository;
import Team.project.repository.StudentRepository;
import Team.project.repository.CourseRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public CourseRegistration registerCourse(Long hakbun, Long courseId) throws Exception {
        log.info("CourseRegistrationService. Hakbun: {}, CourseID: {}", hakbun, courseId);

        // 학생정보를 데이터베이스에서 조회
        Student student = studentRepository.findByHakbun(hakbun).orElseThrow(() -> {
            log.error("학생을 찾을 수 없음. Hakbun: {}", hakbun);
            return new NullPointerException();
        });
        // 강좌를 데이터베이스에서 조회
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("강좌를 찾을 수 없음. CourseID: {}", courseId);
                    return new Exception("강좌를 찾을 수 없습니다.");
                });

        // 이미 수강신청 되었는지 확인
        if (courseRegistrationRepository.existsByStudentAndCourse(student, course)) {
            log.warn("이미 수강신청된 강좌. Hakbun: {}, CourseID: {}", hakbun, courseId);
            throw new Exception("이미 수강신청된 강좌입니다.");
        }

        // 수강신청 엔티티 생성 및 저장
        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setStudent(student);
        courseRegistration.setCourse(course);
        log.info("Course registered successfully. Hakbun: {}, CourseID: {}", hakbun, courseId);
        return courseRegistrationRepository.save(courseRegistration);
    }
}
