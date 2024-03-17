package assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GUIController {

    @FXML
    private ChoiceBox<Hall> hallChoiceBox;

    @FXML
    private TextField hallTelephoneNumber;

    @FXML
    private TextField managerName;

    @FXML
    private TextField totalRoomsField;

    @FXML
    private TextField availableRoomsField;

    @FXML
    private TextField offlineRoomsField;

    @FXML
    private TextField requireCleaningRoomsField;

    @FXML
    private TableView<Accommodation> accommodationTableView;

    @FXML
    private TableColumn<Accommodation, Integer> accommNoColumn;

    @FXML
    private TableColumn<Accommodation, Accommodation.AccommodationType> accommTypeColumn;

    @FXML
    private TableColumn<Accommodation, Float> priceColumn;

    @FXML
    private TableColumn<Accommodation, Accommodation.AccomodationAvailabilty> availabilityColumn;

    @FXML
    private TableColumn<Accommodation, CleaningStatus> cleaningStatusColumn;

    @FXML
    private TextField accommNumberInfo;

    @FXML
    private TextField accommTypeInfo;

    @FXML
    private TextField accommPriceInfo;

    @FXML
    private TextField accommOccupancyInfo;

    @FXML
    private ChoiceBox<CleaningStatus> cleaningStatusChoice;

    @FXML
    private TextField accommDescription;

    private List<Hall> halls;

    @FXML
    private TextField leaseNumber;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField mobileNumber;

    @FXML
    private TextField studentNumber;

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
        updateChoiceBox();
    }

    private void updateChoiceBox() {
        if (halls != null) {
            hallChoiceBox.getItems().addAll(halls);
            hallChoiceBox.setConverter(new HallStringConverter());
        }
    }

    @FXML
    private void initialize() {
        hallChoiceBox.setOnAction(event -> {
            Hall selectedHall = hallChoiceBox.getValue();
            if (selectedHall != null) {
                hallTelephoneNumber.setText(selectedHall.getHallTelephoneNumber());
                HallManager manager = selectedHall.getHallManager();
                if (manager != null) {
                    managerName.setText(manager.getFullName());
                } else {
                    managerName.setText("No manager assigned");
                }
                updateAccommodationFields(selectedHall);

                Accommodation selectedAccommodation = accommodationTableView.getSelectionModel().getSelectedItem();
                if (selectedAccommodation != null) {
                    if (selectedAccommodation.getRentalAgreement() != null) {
                        updateRentalAgreementFields(selectedAccommodation.getRentalAgreement());
                    } else {
                        clearRentalAgreementFields();
                    }
                } else {
                    clearRentalAgreementFields();
                }
            } else {
                // Clear all fields if no hall is selected
                clearFields();
                clearRentalAgreementFields();
            }
        });

        // Set cell value factories for the Accommodation TableView columns
        accommNoColumn.setCellValueFactory(new PropertyValueFactory<>("accommNo"));
        accommTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
        cleaningStatusColumn.setCellValueFactory(new PropertyValueFactory<>("cleaningStatus"));

        // Handle row selection in the TableView
        accommodationTableView.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        leaseNumber.setText(String.valueOf(newSelection.getAccommNo()));
                        updateAccommodationInformation(newSelection);
                    }
                });

        cleaningStatusChoice.getItems().addAll(CleaningStatus.values());
        cleaningStatusChoice.setValue(CleaningStatus.Clean); // Set default value

        // Set the event handler for when a new item is selected in the ChoiceBox
        cleaningStatusChoice.setOnAction(event -> {
            setCleaningStatusForAccommodation();
        });

    }

    private void updateRentalAgreementFields(RentalAgreement rentalAgreement) {
        firstName.setText(rentalAgreement.getStudent().getFirstName());
        lastName.setText(rentalAgreement.getStudent().getLastName());
        studentNumber.setText(String.valueOf(rentalAgreement.getStudent().getStudentID()));
        mobileNumber.setText(rentalAgreement.getStudent().getMobileNumber());
    }

    private void clearRentalAgreementFields() {
        firstName.clear();
        lastName.clear();
        studentNumber.clear();
        mobileNumber.clear();
    }

    // Method to update the Accommodation Information section
    private void updateAccommodationInformation(Accommodation accommodation) {
        // Set the Accommodation Number
        accommNumberInfo.setText(String.valueOf(accommodation.getAccommNo()));
        // Set the Accommodation Type
        accommTypeInfo.setText(accommodation.getType().getRoomType());
        // Set the Price
        accommPriceInfo.setText(String.valueOf(accommodation.getPrice()));
        // Set the Occupancy
        accommOccupancyInfo.setText(accommodation.getOccupancy().getRoomOccupancy());
        accommDescription.setText(accommodation.getDescription());
        // Update or clear rental agreement fields
        if (accommodation.getRentalAgreement() != null) {
            updateRentalAgreementFields(accommodation.getRentalAgreement());
        } else {
            clearRentalAgreementFields();
        }
    }

    private void updateAccommodationFields(Hall selectedHall) {
        int totalRooms = selectedHall.getTotalRooms();
        int availableRooms = selectedHall.getAvailableRooms();
        int offlineRooms = selectedHall.getOfflineRoom();
        int requireCleaningRooms = selectedHall.getRoomsRequireClean();
        totalRoomsField.setText(String.valueOf(totalRooms));
        availableRoomsField.setText(String.valueOf(availableRooms));
        offlineRoomsField.setText(String.valueOf(offlineRooms));
        requireCleaningRoomsField.setText(String.valueOf(requireCleaningRooms));
        // Load accommodation data into the TableView
        accommodationTableView.getItems().clear();
        accommodationTableView.getItems().addAll(selectedHall.getAccommodations());
    }

    private void clearFields() {
        hallTelephoneNumber.setText("");
        managerName.setText("");
        totalRoomsField.setText("0");
        availableRoomsField.setText("0");
        offlineRoomsField.setText("0");
        requireCleaningRoomsField.setText("0");
        accommodationTableView.getItems().clear();
    }

    private void setCleaningStatusForAccommodation() {
        Accommodation selectedAccommodation = accommodationTableView.getSelectionModel().getSelectedItem();
        CleaningStatus selectedCleaningStatus = cleaningStatusChoice.getValue();
        if (selectedAccommodation != null && selectedCleaningStatus != null) {
            Hall selectedHall = hallChoiceBox.getValue();
            selectedHall.setCleaningStatus(selectedAccommodation, selectedCleaningStatus);
            updateAccommodationFields(selectedHall);
        }
    }

    @FXML
    private void createRentalAgreement(ActionEvent event) {
        Accommodation selectedAccommodation = accommodationTableView.getSelectionModel().getSelectedItem();
        Hall selectedHall = hallChoiceBox.getValue();
        if (selectedAccommodation != null) {
            if (selectedAccommodation.getRentalAgreement() != null) {
                // Display error if rental agreement already exists
                System.err.println("Rental agreement already exists for this accommodation.");
                showErrorAlert("Rental agreement already exists for this accommodation.");
            } else {
                // Check if the accommodation is clean and available
                if (selectedAccommodation.getCleaningStatus() == CleaningStatus.Clean) {
                    try {
                        int leaseNum = Integer.parseInt(leaseNumber.getText());
                        int studentID = Integer.parseInt(studentNumber.getText());
                        String first = firstName.getText();
                        String last = lastName.getText();
                        String mobile = mobileNumber.getText();

                        Student student = new Student(studentID, first, last, mobile);
                        RentalAgreement rentalAgreement = new RentalAgreement(leaseNum, student);
                        selectedAccommodation.setRentalAgreement(rentalAgreement);
                        selectedHall.increaseAvailableRooms(-1);

                        // Update GUI fields and availability
                        updateAccommodationFields(selectedHall);
                    } catch (NumberFormatException e) {
                        // Handle invalid input
                        System.err.println("Invalid input format for lease number or student ID.");
                        showErrorAlert("Invalid input format for lease number or student ID.");
                    }
                } else {
                    // Accommodation cannot be rented
                    System.err.println("Accommodation cannot be rented. Make sure it is clean.");
                    showErrorAlert("Accommodation cannot be rented. Make sure it is clean and available.");
                }
            }
        } else {
            // No accommodation selected
            System.err.println("Please select an accommodation.");
            showErrorAlert("Please select an accommodation.");
        }
    }

    @FXML
    private void deleteRentalAgreement(ActionEvent event) {
        Accommodation selectedAccommodation = accommodationTableView.getSelectionModel().getSelectedItem();
        Hall selectedHall = hallChoiceBox.getValue();
        if (selectedAccommodation != null) {
            if (selectedAccommodation.getRentalAgreement() != null) {
                // Delete the rental agreement
                selectedAccommodation.removeRentalAgreement();

                selectedHall.increaseAvailableRooms(1);

                // Update GUI fields and availability
                updateAccommodationFields(selectedHall);
            } else {
                // Display error if no rental agreement exists
                System.err.println("No rental agreement exists for this accommodation.");
                showErrorAlert("No rental agreement exists for this accommodation.");
            }
        } else {
            // No accommodation selected
            System.err.println("Please select an accommodation.");
            showErrorAlert("Please select an accommodation.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static class HallStringConverter extends StringConverter<Hall> {
        @Override
        public String toString(Hall hall) {
            return hall != null ? hall.getHallName() : "";
        }

        @Override
        public Hall fromString(String string) {
            // Not needed for this example
            return null;
        }
    }
}
