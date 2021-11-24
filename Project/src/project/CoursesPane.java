package project;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CoursesPane extends Pane {
	private HBox hbox;
	private Button back;
	private Button previous;
	private Button next;
	private Button search;
	private ListView coursesList;
	private ListView studentsList;
	private Label studentsRegisteredLabel;

	public CoursesPane() {
		this.hbox = new HBox(10);
		this.back = new Button("Back");
		this.previous = new Button("< Previous");
		this.next = new Button("Next >");
		this.search = new Button("Search");

		this.hbox.getChildren().addAll(back, previous, next, search);
		this.hbox.setLayoutX(440);
		this.hbox.setLayoutY(520);

		this.getChildren().add(hbox);

		this.coursesList = new ListView();
		this.studentsList = new ListView();

		for (int i = 0; i < CommonClass.courseList.size(); i++) {
			coursesList.getItems().add(CommonClass.courseList.get(i).getCourseID());
		}

		this.coursesList.setLayoutX(15);
		this.coursesList.setLayoutY(15);
		this.studentsList.setLayoutX(900);
		this.studentsList.setLayoutY(40);

		this.studentsRegisteredLabel = new Label("");
		this.studentsRegisteredLabel.setLayoutX(900);
		this.studentsRegisteredLabel.setLayoutY(15);

		this.getChildren().addAll(coursesList, studentsList, studentsRegisteredLabel);

		this.back.setOnAction(e -> {

			GUI.myScene.setRoot(new MainPane());
			GUI.StaticprimaryStage.setTitle("Project");
			GUI.StaticprimaryStage.setHeight(640);
			GUI.StaticprimaryStage.setWidth(850);
		});

		this.coursesList.setOnMouseClicked(e -> {
			courseListMethod();

		});
		this.next.setOnAction(e -> {
			if (this.coursesList.getSelectionModel().getSelectedIndex() == CommonClass.courseList.size() - 1) {
				this.coursesList.getSelectionModel().selectFirst();
				this.coursesList.scrollTo(0);

			}

			else {
				this.coursesList.getSelectionModel()
						.select(this.coursesList.getSelectionModel().getSelectedIndex() + 1);
				this.coursesList.scrollTo(this.coursesList.getSelectionModel().getSelectedIndex());

			}

			this.courseListMethod();

		});

		this.previous.setOnAction(e -> {
			if (this.coursesList.getSelectionModel().getSelectedIndex() == 0 || this.coursesList.getSelectionModel().getSelectedIndex() == -1) {
				this.coursesList.getSelectionModel().selectLast();
				this.coursesList.scrollTo(this.coursesList.getSelectionModel().getSelectedIndex());

			} else {
				this.coursesList.getSelectionModel()
						.select(this.coursesList.getSelectionModel().getSelectedIndex() - 1);
				this.coursesList.scrollTo(this.coursesList.getSelectionModel().getSelectedIndex());

			}

			this.courseListMethod();

		});

	}

	private void courseListMethod() {
		if (this.coursesList.getSelectionModel().getSelectedIndex() != -1) {
			studentsRegisteredLabel.setText("There are "
					+ CommonClass.courseList.get(coursesList.getSelectionModel().getSelectedIndex()).getCourseSeats()
					+ " students registered in " + coursesList.getSelectionModel().getSelectedItem());

			studentsList.getItems().clear();

			for (int i = 0; i < CommonClass.studentList.size(); i++) {
				for (int j = 0; j < CommonClass.studentList.get(i).getCourses().size(); j++) {

					if (CommonClass.studentList.get(i).getCourses().get(j).getCourseID()
							.equals(coursesList.getSelectionModel().getSelectedItem())) {
						studentsList.getItems().add(CommonClass.studentList.get(i).getStudID());
					}

				}
			}

		}
	}

}
