package Team.project.repository.CourseRegistration;

import Team.project.entity.Course;
import Team.project.entity.Enroll;
import Team.project.entity.Student;

public interface CourseRegistrationRepositoryCustom {
    public Enroll registerCourse(Long hakbun, Long courseId);

    public boolean existsByStudentAndCourse(Student student, Course course);
}
