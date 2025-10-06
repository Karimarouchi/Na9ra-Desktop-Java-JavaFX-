package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.TheInformants.entities.Book;
import tn.TheInformants.entities.Panier;
import tn.TheInformants.services.ServicePanier;

import java.sql.SQLException;

public class itembookController
    {
        @FXML
        private Parent root;
        @FXML
        private ImageView itemimg;
        @FXML
        private Label itemname;

        @FXML
        private Label itemprice;

private Book book;
        public void setData(Book book){
            this.book=book;
            System.out.println(book);
            itemname.setText(book.getNom_liv());
            itemprice.setText(book.getPrix_liv()+" TND");
            Image image = new Image(book.getImagePath());
            itemimg.setImage(image);
        }

public void  buybtn_clicked(ActionEvent event) throws SQLException {
    System.out.println("cliiick");
    ServicePanier servicePanier = ServicePanier.getInstance();
    int id_liv =book.getId_liv();
    double prix_liv=book.getPrix_liv();
    String nom_liv=book.getNom_liv();
    String image_liv=book.getImagePath();
    String pdf_liv=book.getPdfPath();


    Panier panier = new Panier(id_liv,prix_liv,nom_liv,image_liv,pdf_liv);
    try {
        servicePanier.ajouter(panier);
        showPopup("Book added to collection successfully", AlertType.INFORMATION);

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    // Add questions to the MySQL table and associate them with the quiz
}
        private void showPopup(String message, AlertType alertType) {
            Alert alert = new Alert(alertType);
            alert.setTitle("Collection Update");
            alert.setHeaderText(null);
            alert.setContentText(message);



            alert.showAndWait();
        }
}
