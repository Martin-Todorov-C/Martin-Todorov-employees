package com.example.martintodorovemployees.app;

import com.example.martintodorovemployees.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Launch point for the JavaFX UI.
 *
 * Loads our layout from FXML, wires up the controller,
 * then shows the primary window.
 */
public class EmployeePairApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the design from XML file
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/martintodorovemployees/hello-view.fxml")
        );
        BorderPane root = loader.load();

        // Give the controller access to the Stage so it can open file choosers, etc.
        MainController controller = loader.getController();
        controller.setStage(stage);

        // Build and show the scene
        Scene scene = new Scene(root, 700, 500);
        stage.setTitle("Employee Pair Finder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Starting JavaFX app
        launch();
    }
}