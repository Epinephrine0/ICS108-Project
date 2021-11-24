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

		
		
/*		// The courses screen
		Pane coursesPane = new Pane();
		Scene coursesScene = new Scene(coursesPane, 1200, 500);

		HBox courses_hbox = new HBox(10);
		Button back = new Button("Back");
		Button previous = new Button("< Previous");
		Button next = new Button("Next >");
		Button search = new Button("Search");
		courses_hbox.getChildren().addAll(back, previous, next, search);
		courses_hbox.setLayoutX(450);
		courses_hbox.setLayoutY(450);
		coursesPane.getChildren().add(courses_hbox);

		ListView coursesList = new ListView();
		ListView studentsList = new ListView();

		for (int i = 0; i < CommonClass.courseList.size(); i++) {
			coursesList.getItems().add(CommonClass.courseList.get(i).getCourseID());
		}

		coursesPane.getChildren().add(coursesList);
		coursesPane.getChildren().add(studentsList);
		
		coursesList.setLayoutX(15);
		coursesList.setLayoutY(15);
		studentsList.setLayoutX(900);
		studentsList.setLayoutY(40);

		Label studentsRegisteredLabel = new Label("");
		studentsRegisteredLabel.setLayoutX(900);
		studentsRegisteredLabel.setLayoutY(15);

		coursesPane.getChildren().add(studentsRegisteredLabel);

		coursesList.setOnMouseClicked(e -> {
			studentsRegisteredLabel.setText("There are "
					+ CommonClass.courseList.get(coursesList.getSelectionModel().getSelectedIndex()).getCourseSeats()
					+ " students registered in " + coursesList.getSelectionModel().getSelectedItem());
			
			studentsList.getItems().clear();
			
			for (int i = 0; i < CommonClass.studentList.size(); i++) {
				for (int j = 0; j < CommonClass.studentList.get(i).getCourses().size(); j++) {

					if (CommonClass.studentList.get(i).getCourses().get(j).getCourseID().equals(coursesList.getSelectionModel().getSelectedItem())) {
						studentsList.getItems().add(CommonClass.studentList.get(i).getStudID());
					}

				}
			}
		});

		viewCourse.setOnAction(e -> { // The View course button in the starting screen
			primaryStage.setTitle("Courses");
			primaryStage.setScene(coursesScene);
			primaryStage.show();
		});

		back.setOnAction(e -> { // The back button in the courses screen
			primaryStage.setTitle("Project");
			primaryStage.setScene(Startscene);
			primaryStage.show();
		});
*/
	}

	public static void main(String[] args) {
		CommonClass.loadBinaryData();
		 Application.launch(args);
	}
}
