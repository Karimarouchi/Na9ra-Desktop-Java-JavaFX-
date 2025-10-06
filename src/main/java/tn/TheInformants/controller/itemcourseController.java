package tn.TheInformants.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import tn.TheInformants.entities.cours;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class itemcourseController
{
    @FXML
    private ImageView img;

    @FXML
    private Label level;

    @FXML
    private Label name;
    private cours cours;

    @FXML
    private Button CoursedetailsBtn;
    @FXML
    private HBox qrCodeBtnUI;

    public void setData(cours cours){
        this.cours=cours;
        System.out.println(cours);
        name.setText(cours.getTitre());
        level.setText(cours.getNiveau());

        Image image = new Image(
                getClass().getResource("/uploads/" + cours.getImagePath()).toExternalForm());
        img.setImage(image);

        System.out.println(cours.getId());

        CoursedetailsBtn.setOnMouseClicked(event -> {
            cours.setIdCourse(cours.getId()) ;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/userCommentsList.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();

                AnchorPane midlast = (AnchorPane ) ((Node) event.getSource()).getScene().lookup("#midlast");
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        qrCodeBtnUI.setOnMouseClicked(event -> {

            String text = "Course Link: " + cours.getLink();
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



    }





}