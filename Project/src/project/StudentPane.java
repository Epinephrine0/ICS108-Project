package project;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StudentPane extends Pane {
	// All the parameters needed for the class

	private Label studentIDLabel;
	private Label registeredCoursesLabel;
	private Label notregisteredCoursesLabel;
	private Label search_Failed_Message;
	private TextField studentID_TextField;
	private ListView coursesList;
	private ComboBox avalibleCourses;
	private Button back;
	private Button previous;
	private Button next;
	private Button search;
	private Button register;
	private Button drop;
	private HBox hbox;
	private VBox vbox;
	private int indexOfStudent;
	private boolean is_Not_Found;
	private ArrayList studentCurrentCourses;
	private String wantedCourse;
	private String unWantedCourse;

	public StudentPane() {
		// Setting the title and dimensions

		GUI.StaticprimaryStage.setTitle("Students");
		GUI.StaticprimaryStage.setHeight(600);
		GUI.StaticprimaryStage.setWidth(700);

		// Setting the labels
		this.studentIDLabel = new Label("Student ID");
		this.registeredCoursesLabel = new Label("Registered Courses");
		this.notregisteredCoursesLabel = new Label("Not Registered Courses");
		
		this.search_Failed_Message = new Label("Search failed, there is no student with this ID");
		this.search_Failed_Message.setStyle("-fx-font-weight: bold");
		this.search_Failed_Message.setLayoutX(250);
		this.search_Failed_Message.setLayoutY(20);
		this.search_Failed_Message.setVisible(false);

		this.studentIDLabel.setLayoutX(100);
		this.studentIDLabel.setLayoutY(50);

		this.registeredCoursesLabel.setLayoutX(100);
		this.registeredCoursesLabel.setLayoutY(250);

		this.notregisteredCoursesLabel.setLayoutX(100);
		this.notregisteredCoursesLabel.setLayoutY(447);

		// Setting the HBox and putting the buttons in it

		this.hbox = new HBox(10);
		this.back = new Button("Back");
		this.previous = new Button("< Previous");
		this.next = new Button("Next >");
		this.register = new Button("Register");
		this.drop = new Button("Drop");
		this.search = new Button("Search");

		this.hbox.getChildren().addAll(back, previous, next, register, drop, search);
		this.hbox.setLayoutX(165);
		this.hbox.setLayoutY(520);

		// Setting the ID TextField and the courses list and the available courses
		// and putting them in a VBox
		this.studentID_TextField = new TextField("");
		this.coursesList = new ListView();
		this.coursesList.setMaxHeight(350);
		this.avalibleCourses = new ComboBox();
		this.avalibleCourses.setMinWidth(300);
		this.vbox = new VBox(10);

		vbox.getChildren().addAll(studentID_TextField, coursesList, avalibleCourses);
		vbox.setLayoutX(250);
		vbox.setLayoutY(50);

		this.getChildren().addAll(studentIDLabel, registeredCoursesLabel, notregisteredCoursesLabel, hbox, vbox,search_Failed_Message);

		// Showing the student ID and Courses
		indexOfStudent = 0;
		this.studentID_TextField.setText(CommonClass.studentList.get(0).getStudID()); // Initialize with the first
		getStudentCourses(); // student
		getAvailableCourses();

		// Setting the back button
		this.back.setOnAction(e -> {
			GUI.myScene.setRoot(new MainPane());
			GUI.StaticprimaryStage.setTitle("Project");
			GUI.StaticprimaryStage.setHeight(640);
			GUI.StaticprimaryStage.setWidth(850);
		});

		// When the user click on "Next" we just select the (selected index + 1), if the
		// selected is the last index, we select index 0

		this.next.setOnAction(e -> {

			if (indexOfStudent == CommonClass.studentList.size() - 1) {
				indexOfStudent = 0;
			} else {
				indexOfStudent += 1;
			}
			getStudentCourses();
		});

		// when the user select "Previous" we just select the (selected index - 1), if
		// the
		// selected index is the first index, we select the last index
		this.previous.setOnAction(e -> {

			if (indexOfStudent == 0) {
				indexOfStudent = CommonClass.studentList.size() - 1;
			} else {
				indexOfStudent -= 1;
			}
			getStudentCourses();
		});

		// The id text field calls the search function

		this.studentID_TextField.setOnAction(e -> {
			searchForStudent();
		});
		
		this.search.setOnAction(e -> {
			searchForStudent();
		});

		// The register button calls the register function

		this.register.setOnAction(e -> {
			registerCourse();
		});

		// The drop button calls the drop method

		this.drop.setOnAction(e -> {
			removeCourse();

		});

	}

	//This method will search for a student in the students list and will show his courses
	private void getStudentCourses() {

		this.coursesList.getItems().clear();
		this.studentID_TextField.setText(CommonClass.studentList.get(indexOfStudent).getStudID());
		for (int i = 0; i < CommonClass.studentList.get(indexOfStudent).getCourses().size(); i++) {

			this.coursesList.getItems()
					.add(CommonClass.studentList.get(indexOfStudent).getCourses().get(i).getCourseID());
			getAvailableCourses();
		}

	}
	
	//This method will search for a student in the students list and will show available to register courses

	private void getAvailableCourses() {

		this.avalibleCourses.getItems().clear();
		for (int i = 0; i < CommonClass.courseList.size(); i++) {
			if (CommonClass.courseList.get(i).getAvailableSeats() > 0) {
				if (!this.coursesList.getItems().contains(CommonClass.courseList.get(i).getCourseID())) {
					this.avalibleCourses.getItems().add(CommonClass.courseList.get(i).getCourseID());
				}
			}

		}

	}
	
	
	//This method will take the student id from the student id text field and will
	//search for him and will call the two methods above
	//if the student is not found it will show the error message and will clear the lists
	

	private void searchForStudent() {
		is_Not_Found = true;

		for (int i = 0; i < CommonClass.studentList.size(); i++) {
			if (CommonClass.studentList.get(i).getStudID().equals(this.studentID_TextField.getText())) {
				indexOfStudent = i;
				getStudentCourses();

				this.search_Failed_Message.setVisible(false);
				is_Not_Found = false;
			}

		}
		
		if (is_Not_Found) {
			this.search_Failed_Message.setVisible(true);
			this.coursesList.getItems().clear();
			this.avalibleCourses.getItems().clear();

		}
	}

	//This method will search for the wanted course and use his index to add the object to the student courses array list
	//it will also call the (register) method using the course object
	private void registerCourse() {
		if (this.avalibleCourses.getSelectionModel().getSelectedItem() != null) {

			wantedCourse = (String) this.avalibleCourses.getSelectionModel().getSelectedItem();

			for (int i = 0; i < CommonClass.studentList.size(); i++) {
				if (CommonClass.studentList.get(i).getStudID().equals(this.studentID_TextField.getText())) {

					for (int j = 0; j < CommonClass.courseList.size(); j++) {

						if (CommonClass.courseList.get(j).getCourseID().equals(wantedCourse)) {
							studentCurrentCourses = CommonClass.studentList.get(i).getCourses();
							studentCurrentCourses.add(CommonClass.courseList.get(j));
							CommonClass.courseList.get(j).register();
							getStudentCourses();
							MainPane.unsavedChanges = true;

						}

					}

				}

			}

		}

	}
	//This method will search for the unwanted course and will remove it from the student courses array list
	//it will also call the (drop) method using the course object

	private void removeCourse() {
		if (this.coursesList.getSelectionModel().getSelectedItem() != null) {
			unWantedCourse = (String) this.coursesList.getSelectionModel().getSelectedItem();

			for (int i = 0; i < CommonClass.studentList.size(); i++) {
				if (CommonClass.studentList.get(i).getStudID().equals(this.studentID_TextField.getText())) {

					for (int j = 0; j < CommonClass.courseList.size(); j++) {

						if (CommonClass.courseList.get(j).getCourseID().equals(unWantedCourse)) {
							studentCurrentCourses = CommonClass.studentList.get(i).getCourses();
							studentCurrentCourses.remove(CommonClass.courseList.get(j));
							CommonClass.courseList.get(j).drop();
							getStudentCourses();
							MainPane.unsavedChanges = true;

						}

					}

				}

			}

		}
	}

}
