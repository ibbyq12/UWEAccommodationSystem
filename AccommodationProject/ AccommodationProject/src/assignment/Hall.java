package assignment;

import java.util.ArrayList;

public class Hall {
    final private String hallName;
    final private String hallTeleNumber;
    private ArrayList<Accommodation> accommodations;
    private HallManager hallManager;
    private int availableRooms;
    private int totalRooms;
    private int roomsRequireClean;
    private int offlineRoom;
    private int nextAccommodationNumber;

    public Hall(String hallName, String hallTeleNumber) {
        this.hallName = hallName;
        this.hallTeleNumber = hallTeleNumber;
        this.accommodations = new ArrayList<>();
        this.availableRooms = 0;
        this.totalRooms = 0;
        this.roomsRequireClean = 0; // Initialize roomsRequireClean to 0
        this.offlineRoom = 0;
        this.nextAccommodationNumber = 1;
    }

    // Getters for the hall properties
    public int getAvailableRooms() {
        return availableRooms;
    }

    public int getTotalRooms() {
        return accommodations.size();
    }

    public int getRoomsRequireClean() {
        return roomsRequireClean;
    }

    public int getOfflineRoom() {
        return offlineRoom;
    }

    public HallManager getHallManager() {
        return hallManager;
    }

    // Method to add an accommodation
    public void addAccommodation(Accommodation accommodation) {
        // Generate a unique accommodation number
        accommodation.setAccommNo(nextAccommodationNumber++);
        this.accommodations.add(accommodation);

        switch (accommodation.getAvailability()) {
            case Available:
                availableRooms++;
                break;
            case NotAvailable:
                if (accommodation.getCleaningStatus() == CleaningStatus.Offline) {
                    offlineRoom++;
                } else {
                    roomsRequireClean++;
                }
        }
    }

    public void setCleaningStatus(Accommodation accommodation, CleaningStatus cleaningStatus) {
        CleaningStatus previousStatus = accommodation.getCleaningStatus();

        // Set the new cleaning status
        accommodation.setCleaningStatus(cleaningStatus);

        // Adjust counts based on the status change
        switch (previousStatus) {
            case Clean:
                if (cleaningStatus == CleaningStatus.Dirty) {
                    roomsRequireClean++;
                } else if (cleaningStatus == CleaningStatus.Offline) {
                    offlineRoom++;
                    availableRooms--;
                }
                break;
            case Dirty:
                if (cleaningStatus == CleaningStatus.Clean) {
                    roomsRequireClean--;
                } else if (cleaningStatus == CleaningStatus.Offline) {
                    roomsRequireClean--;
                    offlineRoom++;
                    availableRooms--;
                }
                break;
            case Offline:
                if (cleaningStatus == CleaningStatus.Clean) {
                    offlineRoom--;
                    availableRooms++;
                } else if (cleaningStatus == CleaningStatus.Dirty) {
                    roomsRequireClean++;
                    availableRooms++;
                }
                break;
        }
    }

    // Getter for the accommodations list
    public ArrayList<Accommodation> getAccommodations() {
        return this.accommodations;
    }

    public void increaseAvailableRooms(int amount) {
        availableRooms += amount;
    }

    // Getter for the hall name
    public String getHallName() {
        return this.hallName;
    }

    // Getter for the telephone number
    public String getHallTelephoneNumber() {
        return this.hallTeleNumber;
    }

    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }

    @Override
    public String toString() {
        return "Hall: " + "\nHall Name: " + hallName + "\nHall Telephone Number: " + hallTeleNumber
                + "\nAccommodations: " + accommodations + "\nHall Manager: " + hallManager
                + "\nAvailable Rooms=" + availableRooms + "\nTotal Rooms: " + totalRooms +
                "\nRooms Require Cleaning: " + roomsRequireClean + "\nOffline Rooms: " + offlineRoom;
    }
}
