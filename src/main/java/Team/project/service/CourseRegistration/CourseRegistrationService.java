package Team.project.service.CourseRegistration;

import Team.project.entity.Enroll;

public interface CourseRegistrationService {

    Enroll registerCourse(Long hakbun, Long courseId) throws Exception;
}
