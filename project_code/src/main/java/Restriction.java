public class Restriction {
    private int restrictionId;
    private String restrictionName;

    public Restriction(int restrictionId, String restrictionName) {
        this.restrictionId = restrictionId;
        this.restrictionName = restrictionName;
    }

    public int getRestrictionId() {
        return restrictionId;
    }

    public String getRestrictionName() {
        return restrictionName;
    }
}
