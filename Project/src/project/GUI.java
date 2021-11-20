package project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application{

	@Override
	public void start(Stage primaryStage)  {
		Pane startScreen = new Pane();
		Label registrationLabel = new Label("Registration System");
		
		registrationLabel.setFont(Font.font ("arial", 40));
		registrationLabel.setLayoutX(240);
		registrationLabel.setLayoutY(200);

		HBox hbox = new HBox(10);
		Button viewCourse = new Button("View Course");
		Button viewStudentDetails = new Button("View Student Details");
		Button save = new Button("Save");
		hbox.getChildren().addAll(viewCourse,viewStudentDetails,save);
		hbox.setLayoutX(280);
		hbox.setLayoutY(550);
		


		
		startScreen.getChildren().add(registrationLabel);
		startScreen.getChildren().add(hbox);
		
		
		
		Scene scene = new Scene(startScreen, 850, 600);
		primaryStage.setTitle("Project"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
		
	}

	public static void main(String[] args) {
		//CommonClass.loadBinaryData();
		Application.launch(args);
	}
}
