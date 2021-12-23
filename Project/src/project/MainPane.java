package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class MainPane extends Pane {

	// All the parameters needed for the class
	private Label registrationLabel;
	private HBox hbox;
	private Button viewCourse;
	private Button viewStudentDetails;
	private Button save;
	private Button exit;
	private FileOutputStream fus;
	private ObjectOutputStream oos;
	public static boolean unsavedChanges;

	public MainPane() {

		// Setting the dimensions
		GUI.StaticprimaryStage.setHeight(640);
		GUI.StaticprimaryStage.setWidth(850);

		// Setting the Registration label
		this.registrationLabel = new Label("Registration System");
		this.registrationLabel.setFont(Font.font("arial", 40));
		this.registrationLabel.setLayoutX(240);
		this.registrationLabel.setLayoutY(200);

		// Setting the HBox and putting the buttons in it

		this.hbox = new HBox(10);

		this.viewCourse = new Button("View Course");
		this.viewStudentDetails = new Button("View Student Details");
		this.save = new Button("Save");
		this.exit = new Button("Exit");

		this.hbox.getChildren().addAll(viewCourse, viewStudentDetails, save, exit);
		this.hbox.setLayoutX(250);
		this.hbox.setLayoutY(550);

		this.getChildren().add(registrationLabel);
		this.getChildren().add(hbox);

		// Making the "Exit" button function

		this.exit.setOnAction(e -> {
			if (unsavedChanges) {
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

			} else {
				System.exit(0);

			}

		});

		// Making the "View Student Details" button function
		this.viewStudentDetails.setOnAction(e -> {
			GUI.myScene.setRoot(new StudentPane());

		});

		// Making the "View Course" button function

		this.viewCourse.setOnAction(e -> {
			GUI.myScene.setRoot(new CoursesPane());

		});

		// Making the "Save" button function

		this.save.setOnAction(e -> {

			try {

				fus = new FileOutputStream("res\\registration.dat");
				oos = new ObjectOutputStream(fus);

				oos.writeObject(CommonClass.courseList);
				oos.writeObject(CommonClass.studentList);

				oos.close();
				fus.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.print(e1.getMessage());
			}
			unsavedChanges = false;

		});

	}

}
