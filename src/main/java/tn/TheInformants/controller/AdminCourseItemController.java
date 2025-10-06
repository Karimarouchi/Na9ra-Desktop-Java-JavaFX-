package tn.TheInformants.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import tn.TheInformants.entities.cours;
import tn.TheInformants.services.ServiceCours;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class AdminCourseItemController {

    @FXML
    private HBox deleteCourse;

    @FXML
    private Label descriptionCourse;

    @FXML
    private HBox editCourse;

    @FXML
    private ImageView img;

    @FXML
    private Label levelCourse;

    @FXML
    private Text titleCourse;

    @FXML
    private HBox qrCodeBtn;

    @FXML
    private HBox shareFbBtn;


    public void setCourseData(cours cours) {
        // Instancier le service de cours
        ServiceCours coursService = new ServiceCours();

      //  Image image = new Image(
      //          getClass().getResource("/uploads/" + cours.getImagePath()).toExternalForm());
      //  img.setImage(image);

        titleCourse.setText(cours.getTitre());
        descriptionCourse.setText(cours.getDescription());
        levelCourse.setText(cours.getNiveau());

        // deleteCategory btn click
        deleteCourse.setId(String.valueOf(cours.getId()));

        deleteCourse.setOnMouseClicked(event -> {
            System.out.println("ID du cours à supprimer : " + cours.getId());

           // int courseIdToDelete = Integer.parseInt(IdTF.getText());
            cours coursToDelete = new cours();
            coursToDelete.setId(cours.getId());

            // Appeler la méthode de suppression dans le service
            try {
                coursService.supprimer(coursToDelete);

                // Afficher un message de succès ou effectuer d'autres actions nécessaires après la suppression réussie
                System.out.println("Le cours a été supprimé avec succès.");

                // Rafraîchir le contenu du GridPane après la suppression réussie
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Admin/CoursUI.fxml"));
                try {
                    Parent root = loader.load();
                    // Accéder à la pane content_area depuis le controller de

                    AnchorPane contentArea = (AnchorPane ) ((Node) event.getSource()).getScene().lookup("#content_area");

                    // Vider la pane et afficher le contenu de ProductsList.fxml
                    contentArea.getChildren().clear();
                    contentArea.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //refreshCourseList();
            } catch (SQLException e) {
                // Afficher un message d'erreur en cas d'échec de la suppression
                System.out.println("Erreur lors de la suppression du cours : " + e.getMessage());
                e.printStackTrace();
            }
        });
        // END deleteCourse btn click

        //**********edit course btn click
        editCourse.setOnMouseClicked(event -> {
            System.out.println("ID du cours à modifier : " + cours.getId());

            try {
                coursService.getById(cours.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Accéder aux inputs du formulaire du cours depuis ce controller
            TextField titleTF = (TextField) ((Node) event.getSource()).getScene().lookup("#titleTF");
            titleTF.setText(cours.getTitre());

            TextField DescriptionTF = (TextField) ((Node) event.getSource()).getScene().lookup("#DescriptionTF");
            DescriptionTF.setText(cours.getDescription());

            ComboBox<String> niveauComboBox = (ComboBox) ((Node) event.getSource()).getScene().lookup("#niveauComboBox");
            niveauComboBox.setValue(cours.getNiveau());

            ImageView  imgb = (ImageView ) ((Node) event.getSource()).getScene().lookup("#imgb");

            Image image = new Image(
                    getClass().getClassLoader().getResource("/uploads/" + cours.getImagePath()).toExternalForm());
            imgb.setImage(image);

        cours.setIdCourse(cours.getId());

        });
        // END editCourse btn click

        //**********share fb btn click
        shareFbBtn.setOnMouseClicked(event -> {
            String appId = "1093298458485409";
            String appSecret = "561fb810c001c9113e0a1d8459f3d0e1";
            String accessTokenString = "EAAPiWWJAYqEBOZBcqZCUW6vNmhZA6fHmncFIk1D59IQBKnIK1PJMdgQADMJT5gFSMOAWeAgXoZApxV9Hg4xr0LkhMabcFe1QLrZB8rNHskaKAmdn4Jq6DZCWfXvnnyhIzZBktw0g4WrWl6f0n2ZCL4s2JgHYZCp5WY3I6N96j6pQJhgRXoPp3voFgFxsGWnGviYwyRqZBGtzxiI8FNh3nYBgBYZCAQZD";

            Facebook facebook = new FacebookFactory().getInstance();
            facebook.setOAuthAppId(appId, appSecret);
            facebook.setOAuthAccessToken(new AccessToken(accessTokenString, null));

            String msg = "New Course is available now "
                    + "\n*** Title: "
                    + cours.getTitre()
                    + "\n*** Description: "
                    + cours.getDescription()
                    + "\n***Date: "
                    + cours.getNiveau() ;

            try {
                facebook.postStatusMessage(msg);
                System.out.println("Post shared successfully.");
            } catch (FacebookException e) {
                throw new RuntimeException(e);
            }

        });
        // END shareFb btn click

        //**********share fb btn click
        qrCodeBtn.setOnMouseClicked(event -> {

            String text = "Course ID: " + cours.getId()
                    + "\nCourse Title: " + cours.getTitre()
                    + "\nCourse Description: " + cours.getDescription()
                    + "\nCourse level: " + cours.getNiveau();
            // Créer un objet QRCodeWriter pour générer le QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // Générer la matrice de bits du QR code à partir du texte saisi
            BitMatrix bitMatrix;
            try {
                bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
                // Convertir la matrice de bits en image BufferedImage
                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                // Enregistrer l'image en format PNG
                // File outputFile = new File("qrcode.png");
                // ImageIO.write(bufferedImage, "png", outputFile);
                // Afficher l'image dans l'interface utilisateur

                ImageView qrCodeImg = (ImageView) ((Node) event.getSource()).getScene().lookup("#qrCodeImg");
                qrCodeImg.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

                HBox qrCodeImgModal = (HBox) ((Node) event.getSource()).getScene().lookup("#qrCodeImgModal");
                qrCodeImgModal.setVisible(true);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });
        // END qrCode btn click


    }
}
