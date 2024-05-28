package Team.project.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class SemesterInfo {

    private int current_year;
    private int semester;
}
