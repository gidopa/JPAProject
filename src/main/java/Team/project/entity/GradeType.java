package Team.project.entity;

public enum GradeType  {
    APLUS("A+"),
    AZERO("A"),
    BPLUS("B+"),
    BZERO("B"),
    CPLUS("C+"),
    CZERO("C"),
    DPLUS("D+"),
    DZERO("D"),
    F("F"),
    INPROGRESS("INPROGRESS");

    private final String displayName;

    GradeType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
