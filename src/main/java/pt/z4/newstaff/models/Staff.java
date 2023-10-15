package pt.z4.newstaff.models;

public class Staff {
    private final String name;
    private boolean isOnStaffMode;
    private boolean exists;

    public Staff(String name, boolean isOnStaffMode, boolean exists) {
        this.name = name;
        this.isOnStaffMode = isOnStaffMode;
        this.exists = exists;
    }

    public String getName() {
        return name;
    }

    public boolean isOnStaffMode() {
        return isOnStaffMode;
    }

    public boolean exists() {
        return exists;
    }
}
