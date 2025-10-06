package tn.TheInformants.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestUIController implements Initializable {

    @FXML
    private Button ajouter_Test;

    @FXML
    private Button modifier_Test;

    @FXML
    private Button supprimer_Test;
    @FXML
    private AnchorPane main_anchor;
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main_anchor.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/statisticsf.fxml"));

        try {
            Parent p = (Parent) fxmlLoader.load();
            main_anchor.setTopAnchor(p, 0.0);
            main_anchor.setRightAnchor(p, 0.0);
            main_anchor.setBottomAnchor(p, 0.0);
            main_anchor.setLeftAnchor(p, 0.0);
            main_anchor.getChildren().add(p);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    @FXML
    void ajouter_Test_clicked(ActionEvent event) {
        ajouter_Test.setStyle("-fx-border-color: #0070FF; -fx-text-fill:#0070FF;-fx-border-radius: 10px");
        modifier_Test.setStyle("-fx-border-color: #0070FF00; -fx-border-radius: 0px");
        supprimer_Test.setStyle("-fx-border-color: #0070FF00; -fx-border-radius: 0px");

        main_anchor.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/AddTest.fxml"));

        try {
            Parent p = (Parent) fxmlLoader.load();
            main_anchor.setTopAnchor(p, 0.0);
            main_anchor.setRightAnchor(p, 0.0);
            main_anchor.setBottomAnchor(p, 0.0);
            main_anchor.setLeftAnchor(p, 0.0);
            main_anchor.getChildren().add(p);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }


    @FXML
    void modifier_Test_clicked(ActionEvent event) {
        modifier_Test.setStyle("-fx-border-color: #0070FF;-fx-text-fill:#0070FF; -fx-border-radius: 10px");
        ajouter_Test.setStyle("-fx-border-color: #0070FF00; -fx-border-radius: 0px");
        supprimer_Test.setStyle("-fx-border-color: #0070FF00; -fx-border-radius: 0px");
        main_anchor.getChildren().clear(); // Clear existing content
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/UpdateTest.fxml"));

        try {
            Parent p = (Parent) fxmlLoader.load();
            main_anchor.setTopAnchor(p, 0.0);
            main_anchor.setRightAnchor(p, 0.0);
            main_anchor.setBottomAnchor(p, 0.0);
            main_anchor.setLeftAnchor(p, 0.0);

            main_anchor.getChildren().add(p);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @FXML
    void supprimer_Test_clicked(ActionEvent event) {
        supprimer_Test.setStyle("-fx-border-color: #0070FF;-fx-text-fill:#0070FF; -fx-border-radius: 10px");
        modifier_Test.setStyle("-fx-border-color: #0070FF00; -fx-border-radius: 0px");
        ajouter_Test.setStyle("-fx-border-color: #0070FF00; -fx-border-radius: 0px");
        main_anchor.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/DeleteForm.fxml"));

        try {
            Parent p = (Parent) fxmlLoader.load();
            main_anchor.setTopAnchor(p, 0.0);
            main_anchor.setRightAnchor(p, 0.0);
            main_anchor.setBottomAnchor(p, 0.0);
            main_anchor.setLeftAnchor(p, 0.0);

            main_anchor.getChildren().add(p);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }




    }




}
