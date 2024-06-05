package Team.project.service.CourseRegistration;

import Team.project.entity.Course;
import Team.project.entity.CourseRegistration;

public interface CourseRegistrationService {

    CourseRegistration registerCourse(Long hakbun, Long courseId) throws Exception;
}
