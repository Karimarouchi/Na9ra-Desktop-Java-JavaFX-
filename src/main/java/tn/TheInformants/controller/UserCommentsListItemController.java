package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import tn.TheInformants.entities.discussion;
import tn.TheInformants.services.ServiceDiscussion;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserCommentsListItemController {

    @FXML
    private Label dateCmt;



    @FXML
    private HBox deleteCmt;

    @FXML
    private Label descriptionCmt;

    @FXML
    private HBox editCmt;

    @FXML
    private Text titleCmt;

    @FXML
    private Label usernameCmt;
    @FXML
    private HBox userBtns;



    public void setCommentData(discussion disc) {
        int idUser = LoginController.user1.getUser_id();
        System.out.println("idUser"+idUser);
        //recuperer le user connecter ***********************************
        if(idUser != disc.getUser_id()){
            userBtns.setVisible(false);

        }
        titleCmt.setText(disc.getTitreDiscussion());
        descriptionCmt.setText(disc.getMessage());

        Date datePost = disc.getDatePost();

        // Format the date as a string in MM-JJ-YYYY format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = dateFormat.format(datePost);

        // Set the text of your UI element
        dateCmt.setText(formattedDate);

        //houni trécuperi el user bel methode getById w taffichi nom wel prenom
        //usernameCmt.setText();

        ServiceDiscussion serviceDiscussion = new ServiceDiscussion();

        deleteCmt.setOnMouseClicked(event -> {
            System.out.println("ID du commentaire à supprimer : " + disc.getIdDiscussion());

            try {
                serviceDiscussion.supprimer(disc);

                System.out.println("Le commentaire a été supprimé avec succès.");

                // Rafraîchir le contenu du GridPane après la suppression réussie
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/User/userCommentsList.fxml"));
                try {
                    Parent root = loader.load();
                    // Accéder à la pane content_area depuis le controller de

                    AnchorPane midlast = (AnchorPane ) ((Node) event.getSource()).getScene().lookup("#midlast");

                    // Vider la pane et afficher le contenu de userCommentsList
                    midlast.getChildren().clear();
                    midlast.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //refreshCourseList();
            } catch (SQLException e) {
                // Afficher un message d'erreur en cas d'échec de la suppression
                System.out.println("Erreur lors de la suppression du comment : " + e.getMessage());
                e.printStackTrace();
            }
        });
        // END deleteComment btn click

        //**********edit comment btn click
        editCmt.setOnMouseClicked(event -> {
            System.out.println("ID du comment à modifier : " + disc.getIdDiscussion());

            try {
                serviceDiscussion.getById(disc.getIdDiscussion());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Accéder aux inputs du formulaire du cours depuis ce controller
            TextField titleCmtField = (TextField) ((Node) event.getSource()).getScene().lookup("#titleCmtField");
            titleCmtField.setText(disc.getTitreDiscussion());

            TextField commentCmtField = (TextField) ((Node) event.getSource()).getScene().lookup("#commentCmtField");
            commentCmtField.setText(disc.getMessage());

            discussion.setDiscussionID(disc.getIdDiscussion());



        });
    }
}
