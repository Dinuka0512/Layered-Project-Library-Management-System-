package edu.ijse.gdse.libarymanagementsystem;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load and display the loading view
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoardingSceenView.fxml"))));
        stage.show();

        // Create a background task to load the main scene
        Task<Scene> loadingTask = new Task<>() {
            @Override
            protected Scene call() throws Exception {
                // Load the main layout from FXML
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Login.fxml"));
                return new Scene(fxmlLoader.load());
            }
        };

        // What to do when loading is successful
        loadingTask.setOnSucceeded(event -> {
            Scene sceneValue = loadingTask.getValue();

            stage.setTitle("Log in");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Logo.png")));
//            stage.setMaximized(true);
            stage.resizableProperty().setValue(Boolean.FALSE);

            // Switch to the main scene
            stage.setScene(sceneValue);
        });

        // What to do in case of loading failure (optional)
        loadingTask.setOnFailed(event -> {
            System.err.println("Failed to load the main layout."); // Print error message
        });

        // Start the task in a separate thread
        new Thread(loadingTask).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
