package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CoursesPane extends Pane {
	private HBox hbox;
	private Button back;
	private Button previous;
	private Button next;
	private Button search;
	private ListView coursesList;
	private ListView studentsList;
	private Label studentsRegisteredLabel;
	private VBox vbox1;
	private VBox vbox2;
	private Label id;
	private Label name;
	private Label days;
	private Label location;
	private Label time;
	private Label status;
	private Label search_Label;
	private Label search_Failed_Message;
	private TextField id_TextField;
	private TextField name_TextField;
	private TextField days_TextField;
	private TextField location_TextField;
	private TextField time_TextField;
	private TextField search_Input;
	private ComboBox statusComboBox;
	private boolean is_Not_Found;

	public CoursesPane() {
		this.hbox = new HBox(10);
		this.back = new Button("Back");
		this.previous = new Button("< Previous");
		this.next = new Button("Next >");
		this.search = new Button("Search");

		this.hbox.getChildren().addAll(back, previous, next, search);
		this.hbox.setLayoutX(440);
		this.hbox.setLayoutY(520);

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

		this.vbox1 = new VBox(20);
		this.id = new Label("ID");
		this.name = new Label("Name");
		this.days = new Label("Days");
		this.location = new Label("Location");
		this.time = new Label("Time");
		this.status = new Label("Status");

		this.vbox1.getChildren().addAll(id, name, days, location, time, status);
		this.vbox1.setLayoutX(350);
		this.vbox1.setLayoutY(100);

		this.vbox2 = new VBox(12);
		this.id_TextField = new TextField();
		this.id_TextField.setMinWidth(400);
		this.name_TextField = new TextField();
		this.name_TextField.setMinWidth(400);
		this.days_TextField = new TextField();
		this.days_TextField.setMinWidth(400);
		this.location_TextField = new TextField();
		this.location_TextField.setMinWidth(400);
		this.time_TextField = new TextField();
		this.time_TextField.setMinWidth(400);

		ObservableList<String> options = FXCollections.observableArrayList("Open", "Closed");
		this.statusComboBox = new ComboBox(options);
		this.statusComboBox.setDisable(true);
		
		this.search_Label = new Label("Please enter the course ID:");
		this.search_Input = new TextField();

		this.search_Label.setLayoutX(350);
		this.search_Label.setLayoutY(355);
		this.search_Input.setLayoutX(420);
		this.search_Input.setLayoutY(382);
		this.search_Input.setMinWidth(400);

		this.vbox2.getChildren().addAll(id_TextField, name_TextField, days_TextField, location_TextField,
				time_TextField, statusComboBox);

		this.vbox2.setLayoutX(420);
		this.vbox2.setLayoutY(98);

		this.search_Failed_Message = new Label("Error, this course ID does not exist!");
		this.search_Failed_Message.setLayoutX(465);
		this.search_Failed_Message.setLayoutY(450);
		this.search_Failed_Message.setStyle("-fx-font-weight: bold");
		this.getChildren().addAll(coursesList, studentsList, studentsRegisteredLabel, hbox, vbox1, vbox2,
				search_Failed_Message);

		this.search_Failed_Message.setVisible(false);

		this.back.setOnAction(e -> {

			GUI.myScene.setRoot(new MainPane());
			GUI.StaticprimaryStage.setTitle("Project");
			GUI.StaticprimaryStage.setHeight(640);
			GUI.StaticprimaryStage.setWidth(850);
		});

		this.coursesList.setOnMouseClicked(e -> {
			courseInformationMethod();

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

			this.courseInformationMethod();

		});

		this.previous.setOnAction(e -> {
			if (this.coursesList.getSelectionModel().getSelectedIndex() == 0
					|| this.coursesList.getSelectionModel().getSelectedIndex() == -1) {
				this.coursesList.getSelectionModel().selectLast();
				this.coursesList.scrollTo(this.coursesList.getSelectionModel().getSelectedIndex());

			} else {
				this.coursesList.getSelectionModel()
						.select(this.coursesList.getSelectionModel().getSelectedIndex() - 1);
				this.coursesList.scrollTo(this.coursesList.getSelectionModel().getSelectedIndex());

			}

			this.courseInformationMethod();

		});

		this.search.setOnAction(e -> {

			if (search_Input.getText().equals("")) {
				if (search_Label.getScene() == null && search_Input.getScene() == null) {
					this.getChildren().addAll(search_Label, search_Input);
				}
			}

			else {
				searchForCourse();
			}

		});

		this.search_Input.setOnAction(e -> {
			searchForCourse();
		});

	}

	private void courseInformationMethod() {
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

			this.id_TextField.setText(
					CommonClass.courseList.get(coursesList.getSelectionModel().getSelectedIndex()).getCourseID());
			this.name_TextField.setText(
					CommonClass.courseList.get(coursesList.getSelectionModel().getSelectedIndex()).getCourseName());
			this.days_TextField.setText(
					CommonClass.courseList.get(coursesList.getSelectionModel().getSelectedIndex()).getCourseDays());
			this.location_TextField.setText(
					CommonClass.courseList.get(coursesList.getSelectionModel().getSelectedIndex()).getCourseLocation());
			this.time_TextField.setText(
					CommonClass.courseList.get(coursesList.getSelectionModel().getSelectedIndex()).getCourseTime());

			if (CommonClass.courseList.get(coursesList.getSelectionModel().getSelectedIndex())
					.getAvailableSeats() > 0) {
				this.statusComboBox.getSelectionModel().select(0);
			} else {
				this.statusComboBox.getSelectionModel().select(1);

			}

		}
	}

	private void searchForCourse() {
		is_Not_Found = true;

		for (int i = 0; i < CommonClass.courseList.size(); i++) {
			if (CommonClass.courseList.get(i).getCourseID().equals(this.search_Input.getText().toUpperCase())) {
				this.coursesList.getSelectionModel().select(i);
				this.coursesList.scrollTo(i);
				courseInformationMethod();
				this.search_Failed_Message.setVisible(false);
				is_Not_Found = false;
			}

		}
		if (is_Not_Found) {
			this.search_Failed_Message.setVisible(true);
		}
	}

}
