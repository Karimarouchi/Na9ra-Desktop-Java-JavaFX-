package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import tn.TheInformants.entities.EVENTS;
import tn.TheInformants.Enums.Status;
import tn.TheInformants.Enums.Typee;
import tn.TheInformants.services.ServiceEvents;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EventsController implements Initializable {

    @FXML
    private ImageView imageviewi,imageviewi1;
    @FXML
    private ImageView PictureChooser;
    private AnchorPane event_interface;

    @FXML
    private TextField Input_EVENTS_nom;

    @FXML
    private TextArea Input_EVENTS_desc;

    @FXML
    private TextField Input_EVENTS_date;

    @FXML
    private ComboBox<String> Input_EVENTS_type;
    @FXML
    private ComboBox<String>  Input_EVENTS_status;

    @FXML
    private TextField Input_EVENTS_id2;

    String imagepath,imagepath1;
    public void addevent_btn_clicked(ActionEvent actionEvent) throws SQLException {
        ServiceEvents serviceEvents = new ServiceEvents();
        String NOM;
        try {
             NOM = Input_EVENTS_nom.getText();
        }catch (IllegalArgumentException e) {
            showAlert("set name", "Please enter a  name ");
            return;}
        String descrp;
        try {
             descrp = Input_EVENTS_desc.getText();
        }catch (IllegalArgumentException e) {
            showAlert("set description", "Please enter a  description ");
            return;}


        Date DATE = null;
        try {
            DATE = Date.valueOf(Input_EVENTS_date.getText());
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Date", "Please enter a valid date (YYYY-MM-DD) format");
            return;
        }
        Typee TYPE = null;
        try {
            TYPE = Typee.valueOf(Input_EVENTS_type.getValue());
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Type", "Please enter a valid event type");
            return;
        }
        Status STATUS = null;
        try {
            STATUS = Status.valueOf(Input_EVENTS_status.getValue());
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Status", "Please enter a valid event status");
            return;
        }
        int id_User;
        try {
            id_User = Integer.parseInt("8");
        } catch (NumberFormatException e) {
            showAlert("Invalid User ID", "Please enter a valid user ID (numeric value)");
            return;
        }
        String image_url = "/gui/Rresources/" +imagepath;



        EVENTS events = new EVENTS(NOM, descrp, DATE, TYPE, STATUS, id_User,image_url);

        if (serviceEvents.verifUser(events.getUserId()) == 0) {
            showAlert("User Not Found", "Sorry! There is no USER with the ID = " + events.getUserId() +
                    ". Please verify the User ID.");
        } else {
            serviceEvents.ajouter(events);
            showAlert("Event Added", "Event added successfully");
        }
        Input_EVENTS_type.getItems().clear();
        Input_EVENTS_status.getItems().clear();

    }
    @FXML
    private void handlePictureBtn() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");

        // Set the initial directory
        fileChooser.setInitialDirectory(new File("src/main/resources/gui/Rresources"));


        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("*", "*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Set the selected image to the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            PictureChooser.setImage(image);
            imageviewi.setVisible(false);
            // Get just the name of the file without the full path
            String fileName = selectedFile.getName();
            System.out.println("File Name: " + fileName);
            imagepath = fileName;
            // Use fileName as needed, such as storing it in your database
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

        Input_EVENTS_type.getItems().clear();
        Input_EVENTS_status.getItems().clear();
        Input_EVENTS_type.getItems().add("FORMATION");
        Input_EVENTS_type.getItems().add("ATELIER");
        Input_EVENTS_type.getItems().add("HACKATHON");
        Input_EVENTS_status.getItems().add("ACTIF");
        Input_EVENTS_status.getItems().add("INACTIF");
    }

    public void changer_interface(ActionEvent actionEvent) {
    }
}