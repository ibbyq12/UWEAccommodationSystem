package assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import assignment.Accommodation.AccommodationType;

public class App extends Application {

    private List<Hall> halls;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initialize halls list
        halls = new ArrayList<>();
        // Add halls to the list...
        Hall hall1 = new Hall("Brecon", "123456789");
        Hall hall2 = new Hall("Cotswold", "987654321");
        Hall hall3 = new Hall("Mendip", "456123789");
        Hall hall4 = new Hall("Quantock", "654789123");

        // Adding more Accommodations to Brecon Hall
        hall1.addAccommodation(new Accommodation(AccommodationType.STANDARD, 100.0f, "Description"));
        hall1.addAccommodation(new Accommodation(AccommodationType.SUPERIOR, 150.0f, "Description"));
        halls.add(hall1); // Add hall1 to the list

        // Setting Hall Manager for Brecon Hall
        HallManager manager1 = new HallManager("Manager Name", 1, "123456789");
        hall1.setHallManager(manager1);

        // Adding more Accommodations to Cotswold Hall
        hall2.addAccommodation(new Accommodation(AccommodationType.STANDARD, 100.0f, "Description"));
        hall2.addAccommodation(new Accommodation(AccommodationType.SUPERIOR, 150.0f, "Description"));
        halls.add(hall2); // Add hall2 to the list

        // Setting Hall Manager for Brecon Hall
        HallManager manager2 = new HallManager("Manager Name", 2, "123456789");
        hall1.setHallManager(manager2);

        // Adding more Accommodations to Mendip Hall
        hall3.addAccommodation(new Accommodation(AccommodationType.STANDARD, 100.0f, "Description"));
        hall3.addAccommodation(new Accommodation(AccommodationType.SUPERIOR, 150.0f, "Description"));
        halls.add(hall3);

        // Setting Hall Manager for Brecon Hall
        HallManager manager3 = new HallManager("Manager Name", 3, "123456789");
        hall2.setHallManager(manager3);

        // Adding more Accommodations to Quantock Hall
        hall4.addAccommodation(new Accommodation(AccommodationType.STANDARD, 100.0f, "Description"));
        hall4.addAccommodation(new Accommodation(AccommodationType.SUPERIOR, 150.0f, "Description"));
        halls.add(hall4);

        // Setting Hall Manager for Brecon Hall
        HallManager manager4 = new HallManager("Manager Name", 4, "123456789");
        hall4.setHallManager(manager4);

        // Load FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();

        // Pass the halls list to the controller
        GUIController controller = loader.getController();
        controller.setHalls(halls);

        // Set up the stage
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Accommodation App");
        primaryStage.show();
    }
}
