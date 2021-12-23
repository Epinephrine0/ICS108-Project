package project;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application {

	// All the parameters needed for the class

	public static Scene myScene;
	public static Stage StaticprimaryStage;
	public static Alert alert;

	@Override
	public void start(Stage primaryStage) {

		this.StaticprimaryStage = primaryStage; // To have access to the stage everywhere

		// Initializing the starting screen (the Main Pane)

		myScene = new Scene(new MainPane());
		primaryStage.getIcons().add(new Image("file:icon.png"));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Project");
		primaryStage.setScene(myScene);
		primaryStage.setOnCloseRequest(e -> {
			if (MainPane.unsavedChanges) {

				// Alert popup when there is an unsaved changes

				Alert alert = new Alert(AlertType.WARNING, "Header", ButtonType.YES, ButtonType.NO);

				alert.setHeaderText("You did not click save...");
				alert.setTitle("Warning unsaved changes detected");

				alert.setContentText("Any unsaved changes will be lost, are you sure you want to exit?");
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
					System.exit(0);
				}

				else {
					e.consume(); // DO NOTHING
				}

			}
		});
		primaryStage.show();

	}

	public static void main(String[] args) {
		try {
			CommonClass.loadBinaryData(); // Needed to load all the data
			Application.launch(args);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, t.getClass().getSimpleName() + ": " + t.getMessage());
			throw t; // don't suppress Throwable
		}
	}
}
