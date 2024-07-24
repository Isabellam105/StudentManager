package com.example.studentmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> majorColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField majorField;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));

        studentTable.setItems(studentList);

        addButton.setOnAction(event -> addStudent());
        updateButton.setOnAction(event -> updateStudent());
        deleteButton.setOnAction(event -> deleteStudent());
    }

    private void addStudent() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String email = emailField.getText();
        String major = majorField.getText();

        Student newStudent = new Student(id, name, email, major);
        studentList.add(newStudent);
        clearFields();
    }

    private void updateStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            selectedStudent.setId(Integer.parseInt(idField.getText()));
            selectedStudent.setName(nameField.getText());
            selectedStudent.setEmail(emailField.getText());
            selectedStudent.setMajor(majorField.getText());
            studentTable.refresh();
            clearFields();
        }
    }

    private void deleteStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentList.remove(selectedStudent);
            clearFields();
        }
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        majorField.clear();
    }
}
