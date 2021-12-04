package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class CoursesPane extends Pane {

	//All the parameters needed for the class 

	
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
	private Label search_Failed_Message;
	private TextField id_TextField;
	private TextField name_TextField;
	private TextField days_TextField;
	private TextField location_TextField;
	private TextField time_TextField;
	private ComboBox statusComboBox;
	private boolean is_Not_Found;

	public CoursesPane() {

		//Setting the title and dimensions

		GUI.StaticprimaryStage.setTitle("Courses");
		GUI.StaticprimaryStage.setHeight(600);
		GUI.StaticprimaryStage.setWidth(1200);

		//Setting the HBox and putting the buttons in it

		this.hbox = new HBox(10);
		this.back = new Button("Back");
		this.previous = new Button("< Previous");
		this.next = new Button("Next >");
		this.search = new Button("Search");

		this.hbox.getChildren().addAll(back, previous, next, search);
		this.hbox.setLayoutX(440);
		this.hbox.setLayoutY(520);
		
		//Setting the courses and students list view

		this.coursesList = new ListView();
		this.studentsList = new ListView();

		for (int i = 0; i < CommonClass.courseList.size(); i++) {
			coursesList.getItems().add(CommonClass.courseList.get(i).getCourseID());       //This loop will add every course in the courses list view
		}
		
		//Courses and Students list view position

		this.coursesList.setLayoutX(15);
		this.coursesList.setLayoutY(15);
		this.studentsList.setLayoutX(900);
		this.studentsList.setLayoutY(40);
		
		
		//This label will appear above the students list view to tell how many students are registered in the selected course

		this.studentsRegisteredLabel = new Label("");
		this.studentsRegisteredLabel.setLayoutX(900);
		this.studentsRegisteredLabel.setLayoutY(15);
		
		
		//Setting the left VBox and putting all the labels in it

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
		
		
		//Setting the right VBox and putting all the Text Fields in it

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

		this.statusComboBox = new ComboBox();
		this.statusComboBox.setDisable(true);

		this.vbox2.getChildren().addAll(id_TextField, name_TextField, days_TextField, location_TextField,
				time_TextField, statusComboBox);

		this.vbox2.setLayoutX(420);
		this.vbox2.setLayoutY(98);
		
		//This label will not be visible by default, and will show when the search function fails to find the course

		this.search_Failed_Message = new Label("Error, this course ID does not exist!");
		this.search_Failed_Message.setLayoutX(465);
		this.search_Failed_Message.setLayoutY(400);
		this.search_Failed_Message.setStyle("-fx-font-weight: bold");
		this.getChildren().addAll(coursesList, studentsList, studentsRegisteredLabel, hbox, vbox1, vbox2,
				search_Failed_Message);

		this.search_Failed_Message.setVisible(false);
		
		
		//Making the "Back" button function

		this.back.setOnAction(e -> {

			GUI.myScene.setRoot(new MainPane());
			GUI.StaticprimaryStage.setTitle("Project");
			GUI.StaticprimaryStage.setHeight(640);
			GUI.StaticprimaryStage.setWidth(850);

		});
		
		//When the user click on any course this action will call the Course Information Method
		
		this.coursesList.setOnMouseClicked(e -> {
			courseInformationMethod();

		});
		
		//When the user click on "Next" we just select the (selected index + 1), if the selected is the last index, we select index 0
		//and we call the Course Information Method

		
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
		
		//When the user click on "Previous" we just select the (selected index - 1), if the selected is the first index, we select the last index
		//and we call the Course Information Method
		
		//Note that the -1 in the condition is to handle the case where the user does not select anything and just press "Previous", in this case
		//we select the last index

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
		
		
		
		//When the user click on "Search" this action will call the Search function
		
		this.search.setOnAction(e -> {

			if (!id_TextField.getText().equals("")) {

				searchForCourse();

			}

		});
		
		
		//When the user does not click on "Search", but presses "Enter", this action will call the search funtion

		this.id_TextField.setOnAction(e -> {

			searchForCourse();

		});

	}
	
	//This method will get the selected course information
	
	private void courseInformationMethod() {
		if (this.coursesList.getSelectionModel().getSelectedIndex() != -1) {
			studentsRegisteredLabel.setText("There are "
					+ CommonClass.courseList.get(coursesList.getSelectionModel().getSelectedIndex()).getCourseSeats() //Getting how many students are
					                                                                                                  //registered in the course
					+ " students registered in " + coursesList.getSelectionModel().getSelectedItem());

			studentsList.getItems().clear();                                                                 //Clear the list from the previous students

			for (int i = 0; i < CommonClass.studentList.size(); i++) {                                    //This loop will check search for students
				                                                                                          //who are registered in the course and will add them to the list
				for (int j = 0; j < CommonClass.studentList.get(i).getCourses().size(); j++) {

					if (CommonClass.studentList.get(i).getCourses().get(j).getCourseID()
							.equals(coursesList.getSelectionModel().getSelectedItem())) {
						
						studentsList.getItems().add(CommonClass.studentList.get(i).getStudID());
					}

				}
			}
			
			
			//Filling the course information in the Text Fields

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
				this.statusComboBox.setValue("Open");
			} else {
				this.statusComboBox.setValue("Closed");

			}

		}
	}
	
	//This method will search for a course in the courseList, taking the input from the user in the course id Text Field
	//If the course is not found, the Search Failed label will be visible
	//If the course is found it will select it in the courses list view and will call the Course Information Method
	
	private void searchForCourse() {
		is_Not_Found = true;

		for (int i = 0; i < CommonClass.courseList.size(); i++) {
			if (CommonClass.courseList.get(i).getCourseID().equals(this.id_TextField.getText().toUpperCase())) {
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
