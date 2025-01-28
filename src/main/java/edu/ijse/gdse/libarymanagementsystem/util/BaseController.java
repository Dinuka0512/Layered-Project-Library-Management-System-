package edu.ijse.gdse.libarymanagementsystem.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class BaseController {

    private Timeline inactivityTimer;
    private static final int INACTIVITY_TIMEOUT = 20;

    public void initAutoLogout(Scene scene) {
        // Create a Timeline to monitor inactivity
        inactivityTimer = new Timeline(new KeyFrame(Duration.seconds(INACTIVITY_TIMEOUT), event -> {
            // Code to log out the user
            System.out.println("Account logout for Security system..");
            // Forcibly log out the user or navigate to the login screen
            Platform.runLater(() -> {
                navigateToLoginScreen(scene);
            });
        }));
        inactivityTimer.setCycleCount(Timeline.INDEFINITE);

        // Start the timer
        inactivityTimer.play();

        // Reset the timer on user interaction
        EventHandler<Event> userEventHandler = event -> {
            inactivityTimer.stop();
            inactivityTimer.play();
        };

        // Listen for all types of user input
        scene.addEventFilter(MouseEvent.MOUSE_MOVED, userEventHandler);
        scene.addEventFilter(MouseEvent.DRAG_DETECTED, userEventHandler);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, userEventHandler);
    }

    private void navigateToLoginScreen(Scene scene) {
        try {
            AnchorPane loginRoot = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            scene.setRoot(loginRoot);
            Stage stage = (Stage) scene.getWindow();
            stage.setTitle("Log in");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}