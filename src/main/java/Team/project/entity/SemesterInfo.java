package Team.project.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class SemesterInfo {

    private int year;
    private int semester;
}
