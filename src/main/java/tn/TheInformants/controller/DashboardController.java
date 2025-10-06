package tn.TheInformants.controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Button logout_btn;
    @FXML
    private Label role_user;
    @FXML
    private Label nom_user;
    @FXML
    private ImageView imge_profil;


    String fullurl = "C:\\Users\\user\\Desktop\\karim FINAL\\karim FINAL\\karim\\public\\uploads\\files\\" + LoginController.user1.getImage();
    @FXML
    private Button modifer;
    @FXML
    private Label welcom;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            imge_profil.setImage(new Image(new FileInputStream(fullurl)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e);
        }

        System.out.println("je suis"+LoginController.user1);
        role_user.setText(LoginController.user1.getRole().toString());
        nom_user.setText(LoginController.user1.getNom());
        welcom.setText("Welcome back "+LoginController.user1.getNom()+"!");

    }
    @FXML
    public void logout() throws IOException {
       logout_btn.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/login.fxml"));
        Stage stage =new Stage();
        Scene scene =new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/gui/Rresources/AI.css").toExternalForm());
        stage.setScene(scene);
        scene.setFill(null);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    public void mofifer_user(Event event) throws IOException {
    }

    @FXML
    public void modifer_button(Event event) throws IOException {{
        Parent root = FXMLLoader.load(getClass().getResource("/gui/User/modifer_user.fxml"));
        Scene scene = new Scene(root, 1366, 768);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();}
    }
}
