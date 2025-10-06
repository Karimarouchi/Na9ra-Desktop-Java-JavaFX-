package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import tn.TheInformants.entities.Feedback;
import tn.TheInformants.services.Servicesondage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class RateItem implements Initializable {
    @FXML
    private Label date_rate;

    @FXML
    private Label descrp_rate;
    @FXML
    private Label id_rate;
    @FXML
    private Button mod_rate;

    @FXML
    private Button supp_rate;

    @FXML
    private Label user_id;
    private Events2Controller events2Controller;
    private  Feedback feedback;
    public void setDATA(Feedback feedback, Events2Controller eventsController){
        user_id.setText(String.valueOf(feedback.getUSER_ID()));
        descrp_rate.setText(feedback.getREPONSE());
        date_rate.setText(String.valueOf(feedback.getDATE()));
id_rate.setText(String.valueOf(feedback.getID()));


        this.events2Controller=eventsController;
        this.feedback=feedback;

    }public void supp_rate_clicked() throws SQLException {
        // Get the ID from the text field
        int id = 0;

            id = Integer.parseInt(id_rate.getText());

        // Display a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the event with ID " + id + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If the user clicks OK, perform the deletion
            Servicesondage sondage = new Servicesondage();
            sondage.supprimer(id);
        } else {
            // If the user cancels the operation or closes the dialog, do nothing
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mod_rate.setOnMouseClicked(e -> {
                    events2Controller.setUpEditPagerate(feedback);
                }

        );

    }
}
