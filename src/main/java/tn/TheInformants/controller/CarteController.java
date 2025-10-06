package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.TheInformants.entities.EVENTS;
import tn.TheInformants.services.ServiceEvents;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class CarteController implements Initializable {
    @FXML
    private Label dateit;
    @FXML
    private ImageView imageview_item;
    @FXML
    private TextArea descrpit;

    @FXML
    private Label idit;

    @FXML
    private Label nomit;

    @FXML
    private Label statusit;
    @FXML
    private Button modbtn;
    @FXML
    private Button rate_btn;
    @FXML
    private Label typeit;

    @FXML
    private Label userit;
    private Events2Controller events2Controller;
    private  EVENTS event;
    public void setDATA(EVENTS event, Events2Controller eventsController){
        idit.setText(String.valueOf(event.getId()));
        nomit.setText(event.getNom());
        typeit.setText(String.valueOf(event.getType()));
        userit.setText(String.valueOf(event.getUserId()));
        descrpit.setText(event.getDescription());
        dateit.setText(String.valueOf(event.getDate()));



        this.events2Controller=eventsController;
        statusit.setText(String.valueOf(event.getStatus()));
        imageview_item.setImage(  new Image(Objects.requireNonNull(getClass().getResourceAsStream(event.getImage_url())), 244, 168, false, false) );// Resizing));
        this.event=event;

}public void supp_btn_clicked() throws SQLException {
        // Get the ID from the text field
        int id = 0;
        try {
            id = Integer.parseInt(idit.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid ID", "Please enter a valid numeric ID.");
            return;
        }

        // Display a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the event with ID " + id + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If the user clicks OK, perform the deletion
            ServiceEvents events = new ServiceEvents();
            events.supprimer(id);
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
        modbtn.setOnMouseClicked(e -> {
            try {
                events2Controller.setUpEditPage(event);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        );
        rate_btn.setOnMouseClicked(e -> {
                    try {
                        events2Controller.setUpRatePage(event);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

        );
    }
}
