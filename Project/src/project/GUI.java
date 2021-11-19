package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application{

	@Override
	public void start(Stage primaryStage)  {
		Pane startScreen = new Pane();
		Scene scene = new Scene(startScreen, 400, 400);
		primaryStage.setTitle("Project"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
	}

	public static void main(String[] args) {
		CommonClass.loadBinaryData();
		Application.launch(args);
	}
}
