package assignment;

public class Student {
    private int studentID;
    private String firstName;
    private String mobileNumber;
    private String lastName;

    public Student(int studentID, String firstName, String lastName, String mobileNumber) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.mobileNumber = mobileNumber;
        this.lastName = lastName;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return "Student" + "\nstudent ID: " + studentID + "\nfirst Name:" + firstName + "\nlast Name: " + lastName
                + "\nmobile Number: "
                + mobileNumber;
    }

}
