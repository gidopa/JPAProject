package Team.project;

import Team.project.entity.GradeType;
import org.springframework.stereotype.Component;

@Component
public class EnumUtil {

    public String getDisplayName(GradeType gradeType) {
        return gradeType != null ? gradeType.getDisplayName() : "";
    }
}