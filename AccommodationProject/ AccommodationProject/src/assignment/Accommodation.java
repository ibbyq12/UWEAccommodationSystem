package assignment;

/**
 * Represents an accommodation in a hall.
 */
public class Accommodation {
    private int accommNo; // Accommodation number
    private final AccommodationType accommodationType; // Type of accommodation
    private final float price; // Price of accommodation
    private final String description; // Description of accommodation
    private RentalAgreement rentalAgreement; // Rental agreement associated with accommodation
    private CleaningStatus cleaningStatus; // Cleaning status of accommodation

    /**
     * Constructs an Accommodation object.
     */
    public Accommodation(AccommodationType accommodationType, float price, String description) {
        this.accommodationType = accommodationType;
        this.price = price;
        this.description = description;
        this.rentalAgreement = null;
        this.cleaningStatus = CleaningStatus.Clean;
    }

    // Getter and setter methods...
    public CleaningStatus getCleaningStatus() {
        return cleaningStatus;
    }

    public void setCleaningStatus(CleaningStatus cleaningStatus) {
        this.cleaningStatus = cleaningStatus;
    }

    public int getAccommNo() {
        return accommNo;
    }

    public AccommodationType getType() {
        return accommodationType;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setRentalAgreement(RentalAgreement rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    public void setAccommNo(int accommNo) {
        this.accommNo = accommNo;
    }

    public void removeRentalAgreement() {
        this.rentalAgreement = null;
    }

    public RentalAgreement getRentalAgreement() {
        return rentalAgreement;
    }

    /**
     * Represents the type of accommodation (e.g., Standard Room or Superior Room).
     */
    enum AccommodationType {
        STANDARD("Standard Room"),
        SUPERIOR("Superior Room");

        private String roomType;

        AccommodationType(String roomType) {
            this.roomType = roomType;
        }

        public String getRoomType() {
            return roomType;
        }
    }

    /**
     * Constructs an AccommodationType enum with a specified room type.
     */
    enum AccomodationAvailabilty {
        Available("Available"),
        NotAvailable("Not Available");

        private String roomAvailability;

        AccomodationAvailabilty(String roomAvailability) {
            this.roomAvailability = roomAvailability;
        }

        public String getRoomAvailability() {
            return roomAvailability;
        }
    }

    /**
     * Represents the availability of accommodation (e.g., Available or Not
     * Available).
     */
    public AccomodationAvailabilty getAvailability() {
        // Logic to determine availability based on rental agreement and cleaning status
        if (rentalAgreement != null || cleaningStatus == CleaningStatus.Offline) {
            return AccomodationAvailabilty.NotAvailable;
        } else {
            return AccomodationAvailabilty.Available;
        }
    }

    /**
     * Represents the occupancy status of accommodation (e.g., Occupied or
     * Unoccupied).
     */

    enum AccomodationOccupancy {
        OCCUPIED("Occupied"),
        NOTOCCUPIED("Unoccupied");

        private final String occupancy;

        AccomodationOccupancy(String occupancy) {
            this.occupancy = occupancy;
        }

        public String getRoomOccupancy() {
            return occupancy;
        }
    }

    public AccomodationOccupancy getOccupancy() {
        return rentalAgreement != null ? AccomodationOccupancy.OCCUPIED : AccomodationOccupancy.NOTOCCUPIED;
    }

    @Override
    public String toString() {
        return "Accommodation" + "\nAccommodation Number: " + accommNo + "\n Type: " + accommodationType + "\nprice: "
                + price
                + "\ndescription: "
                + description + "\nRenta lAgreement: " + rentalAgreement + "Cleaning Status: " + cleaningStatus;
    }

}
