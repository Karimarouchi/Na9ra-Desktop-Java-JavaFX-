package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.TheInformants.entities.Panier;

import java.io.IOException;

public class collectionitemController {
    @FXML
    private Label booknamecoll;

    @FXML
    private ImageView imageliv;
    private Panier panier;

    public void setData(Panier panier){
        this.panier=panier;
        System.out.println(panier);
        booknamecoll.setText(panier.getNom_liv());
        Image image = new Image(panier.getImagePath());
        imageliv.setImage(image);
    }
    public void download(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/User/payment.fxml"));

            // Load the payment.fxml file and get the root node
            Parent anchorPane = fxmlLoader.load();

            // Access the controller after loading the fxml
            PaymentController paymentController = fxmlLoader.getController();
            System.out.println(panier);
            // Set the panier in the PaymentController
            paymentController.setPanier(panier);

            // Create a new scene
            Scene updateScene = new Scene(anchorPane);

            // Get the stage from the ActionEvent
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            // Create a new stage for the payment.fxml scene
            Stage paymentStage = new Stage();
            paymentStage.setTitle("Pay");
            paymentStage.setScene(updateScene);

            // Set the owner stage to the current stage (collectionitem.fxml)
            paymentStage.initOwner(currentStage);

            // Show the payment.fxml scene without closing the collectionitem.fxml scene
            paymentStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }




}
