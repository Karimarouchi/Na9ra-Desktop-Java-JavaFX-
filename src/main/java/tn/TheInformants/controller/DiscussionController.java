package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DiscussionController {
    @FXML
    private Label CourseIdLabel;

    @FXML
    private TextField CourseId_Label;

    @FXML
    private TextField DiscussionIdLabel;

    @FXML
    private Label IdLabel;

    @FXML
    private Label MessageLabel;

    @FXML
    private Label PostDate;

    @FXML
    private Label TitleLabel;

    @FXML
    private Button addClaim;

    @FXML
    private Button addCourse_Btn;

    @FXML
    private DatePicker date_Label;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField message_Label;

    @FXML
    private TextField title_Label;

    @FXML
    void AddBtn(ActionEvent event) {

    }

    @FXML
    void addClaimAdmin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/DiscussionUI.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Titre de votre fenêtre");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addCourseAdmin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/CoursUI.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Course");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
