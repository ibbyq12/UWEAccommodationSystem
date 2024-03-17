package assignment;

public enum CleaningStatus {
    Clean("Clean"),
    Dirty("Dirty"),
    Offline("Offline");

    private String Description;

    CleaningStatus(String Description) {
        this.Description = Description;
    }

    public String getDescription() {
        return Description;
    }

}
