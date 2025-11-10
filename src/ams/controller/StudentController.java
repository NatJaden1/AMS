package ams.controller;

import ams.model.Student;
import ams.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StudentController {

    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, Integer> colId;
    @FXML
    private TableColumn<Student, String> colName, colEmail, colCourse;

    @FXML
    private TextField nameField, emailField, courseField;
    @FXML
    private Button addBtn, updateBtn, deleteBtn;

    private ObservableList<Student> studentList;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        colCourse.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourse()));

        loadStudents();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> populateFields(newSel));
    }

    private void loadStudents() {
        studentList = FXCollections.observableArrayList(StudentService.getAllStudents());
        table.setItems(studentList);
    }

    private void populateFields(Student s) {
        if(s != null) {
            nameField.setText(s.getName());
            emailField.setText(s.getEmail());
            courseField.setText(s.getCourse());
        }
    }

    @FXML
    private void addStudent() {
        Student s = new Student(nameField.getText(), emailField.getText(), courseField.getText());
        StudentService.addStudent(s);
        loadStudents();
    }

    @FXML
    private void updateStudent() {
        Student s = table.getSelectionModel().getSelectedItem();
        if(s != null) {
            s.setName(nameField.getText());
            s.setEmail(emailField.getText());
            s.setCourse(courseField.getText());
            StudentService.updateStudent(s);
            loadStudents();
        }
    }

    @FXML
    private void deleteStudent() {
        Student s = table.getSelectionModel().getSelectedItem();
        if(s != null) {
            StudentService.deleteStudent(s.getId());
            loadStudents();
        }
    }
}
