package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.TheInformants.Utils.MyDataBase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    @FXML
    private Button addClaim;

    @FXML
    private Button addCourse_Btn;

    @FXML
    private Button homeBtn;

    @FXML
    private Label homeTotalClaims;

    @FXML
    private Label homeTotalCourses;

    @FXML
    private AnchorPane home_form;

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
    public void homeTotalCourses() {
        System.out.println("Méthode homeTotalCourses() appelée.");

        String sql = "SELECT COUNT(id) AS course_count FROM cours";
        Connection connect = MyDataBase.getInstance().getConnection();
        int countData = 0;
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                countData = result.getInt("course_count");
                // Supposons que homeTotalCourses représente le label où vous souhaitez afficher le nombre total de cours
                homeTotalCourses.setText(String.valueOf(countData));
            } else {
                System.out.println("Aucun résultat trouvé pour le nombre total de cours.");
            }
            result.close();
            prepare.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homeTotalClaims() {
        System.out.println("Méthode homeTotalCourses() appelée.");

        String sql = "SELECT COUNT(id_Discussion) AS discussion_count FROM discussion";
        Connection connect = MyDataBase.getInstance().getConnection();
        int countData = 0;
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                countData = result.getInt("discussion_count");
                // Supposons que homeTotalClaims représente le label où vous souhaitez afficher le nombre total de cours
                homeTotalClaims.setText(String.valueOf(countData));
            } else {
                System.out.println("Aucun résultat trouvé pour le nombre total de cours.");
            }
            result.close();
            prepare.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeTotalCourses();
        homeTotalClaims();

    }

    public void Select(ActionEvent actionEvent) {
    }

    public void AddBtn(ActionEvent actionEvent) {
    }

    public void UpdateBtn(ActionEvent actionEvent) {
    }

    public void DeleteBtn(ActionEvent actionEvent) {
    }

    public void ClearBtn(ActionEvent actionEvent) {
    }

    public void homeBtn(ActionEvent actionEvent) {
    }
}
