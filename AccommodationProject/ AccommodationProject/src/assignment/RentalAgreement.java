package assignment;

import assignment.Accommodation.AccommodationType;

public class RentalAgreement {
    private Student student;
    private final int leaseNumber;
    private AccommodationType Roomobject;

    public RentalAgreement(int leaseNumber, Student student) {
        this.leaseNumber = leaseNumber;
        this.student = student;

    }

    public Student getStudent() {
        return student;
    }

    public int getLeaseNumber() {
        return leaseNumber;
    }

    public void getRoomType() {
        System.out.println(Roomobject.getRoomType());
    }

    @Override
    public String toString() {
        return "Rental Agreement" + "\nstudent: " + student + " lease Number: " + leaseNumber + "RoomType: "
                + Roomobject.getRoomType();
    }

}
