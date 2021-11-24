package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
	
	public static Scene myScene;
	public static Stage StaticprimaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		// The starting screen	
		this.StaticprimaryStage = primaryStage;
		primaryStage.setHeight(640);
		primaryStage.setWidth(850);
		
		myScene = new Scene(new MainPane());
		
		
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
