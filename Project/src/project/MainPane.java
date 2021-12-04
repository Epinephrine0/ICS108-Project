package project;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class MainPane extends Pane {
	
	//All the parameters needed for the class 
	private Label registrationLabel;
	private HBox hbox;
	private Button viewCourse;
	private Button viewStudentDetails;
	private Button save;
	private Button exit;
	
	public MainPane() {
		
		//Setting the dimensions
		GUI.StaticprimaryStage.setHeight(640);
		GUI.StaticprimaryStage.setWidth(850);
		
		//Setting the Registration label
		this.registrationLabel = new Label("Registration System");
		this.registrationLabel.setFont(Font.font("arial", 40));
		this.registrationLabel.setLayoutX(240);
		this.registrationLabel.setLayoutY(200);
		
		//Setting the HBox and putting the buttons in it
		
		this.hbox = new HBox (10);
		
		this.viewCourse = new Button("View Course");
		this.viewStudentDetails = new Button("View Student Details");
		this.save = new Button("Save");
		this.exit = new Button("Exit");
		
		this.hbox.getChildren().addAll(viewCourse, viewStudentDetails, save, exit);
		this.hbox.setLayoutX(250);
		this.hbox.setLayoutY(550);
		
		this.getChildren().add(registrationLabel);
		this.getChildren().add(hbox);
		
		
		
		//Making the "Exit" button function
		
		this.exit.setOnAction(e->{
			System.exit(0);
		});
		
		
		//Making the "View Course" button function
		this.viewCourse.setOnAction(e->{
			GUI.myScene.setRoot(new CoursesPane());  
			
			
		});
		
		
		
	}

	
	
	
}
