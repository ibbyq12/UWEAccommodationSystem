package assignment;

public class HallManager {

    private final String fullName;
    private final int hallManagerID;
    private final String managerContactNumber;
    private Hall hallobject;

    public HallManager(String fullName, int hallManagerID, String managerContactNumber) {
        this.fullName = fullName;
        this.hallManagerID = hallManagerID;
        this.managerContactNumber = managerContactNumber;
    }

    // Getters for the manager properties

    public String getFullName() {
        return fullName;
    }

    public int gethallManagerID() {
        return hallManagerID;
    }

    public String getmanagerContactNumber() {
        return managerContactNumber;
    }

    // Method to get the associated hall
    public void getassociatedhall() {
        System.out.println(hallobject.toString());
    }

    // toString method to represent HallManager object as a string
    @Override
    public String toString() {
        return "Manager's Full Name: " + getFullName() + "\nHall manager's ID: " + gethallManagerID() +
                "\n Manager's Contact number: " + getmanagerContactNumber() + "\nThe Manager's Hall: ";
    }
}
