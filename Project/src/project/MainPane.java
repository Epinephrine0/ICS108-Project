package project;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class MainPane extends Pane {
	private Label registrationLabel;
	private HBox hbox;
	private Button viewCourse;
	private Button viewStudentDetails;
	private Button save;
	
	public MainPane() {
		this.registrationLabel = new Label("Registration System");
		this.registrationLabel.setFont(Font.font("arial", 40));
		this.registrationLabel.setLayoutX(240);
		this.registrationLabel.setLayoutY(200);
		
		this.hbox = new HBox (10);
		
		this.viewCourse = new Button("View Course");
		this.viewStudentDetails = new Button("View Student Details");
		this.save = new Button("Save");
		
		this.hbox.getChildren().addAll(viewCourse, viewStudentDetails, save);
		this.hbox.setLayoutX(280);
		this.hbox.setLayoutY(550);
		
		this.getChildren().add(registrationLabel);
		this.getChildren().add(hbox);
		
		this.viewCourse.setOnAction(e->{
			GUI.myScene.setRoot(new CoursesPane());
			GUI.StaticprimaryStage.setTitle("Courses");
			GUI.StaticprimaryStage.setHeight(600);
			GUI.StaticprimaryStage.setWidth(1200);
		});
		
		
		
	}

	
	
	
}
