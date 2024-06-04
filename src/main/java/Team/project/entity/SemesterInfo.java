package Team.project.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class SemesterInfo {

    private int years;
    private int semester;
}
