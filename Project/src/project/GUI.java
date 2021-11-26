package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application {

	public static Scene myScene;
	public static Stage StaticprimaryStage;

	@Override
	public void start(Stage primaryStage) {
		// The starting screen
		this.StaticprimaryStage = primaryStage;
	

		myScene = new Scene(new MainPane());
		primaryStage.getIcons().add(new Image("file:icon.png"));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Project");
		primaryStage.setScene(myScene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		CommonClass.loadBinaryData();
		Application.launch(args);
	}
}
