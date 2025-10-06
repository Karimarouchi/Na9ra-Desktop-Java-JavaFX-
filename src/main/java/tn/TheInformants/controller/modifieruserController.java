package tn.TheInformants.controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.TheInformants.entities.user;
import tn.TheInformants.services.serviceuser;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class modifieruserController  implements Initializable {

    @FXML
    private Label fx_pswd_eroor;
    @FXML
    private TextField mail_user;
    @FXML
    private ImageView fx_image_supprimer_user;
    @FXML
    private DatePicker date_user;
    @FXML
    private TextField nom_user;
    @FXML
    private TextField prenom_user;
    @FXML
    private Label fx_date_eroor1;
    @FXML
    private Label fx_erro;
    @FXML
    private Button fxedit_user;
    serviceuser serviceuser=new serviceuser();
    String mot= tn.TheInformants.Utils.passwordencryptor.decrypt(LoginController.user1.getPswd());
    @FXML
    private PasswordField mdp_user;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        nom_user.setText(LoginController.user1.getNom());
        prenom_user.setText(LoginController.user1.getPrenom());
        date_user.setValue(LoginController.user1.getDatenes());
        mail_user.setText(LoginController.user1.getMail());
        mdp_user.setText(mot);
    }
   @FXML
    public void suprimer(Event event) throws SQLException , IOException {
        serviceuser serviceusear = new serviceuser();
        serviceusear.Supprimeruser(LoginController.user1.getUser_id());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Admin/DashboardUI.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void retour_user(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sharedInterface/ui.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void modifier(ActionEvent actionEvent){
        String mot= tn.TheInformants.Utils.passwordencryptor.encrypt(mdp_user.getText());
        user user = new user(nom_user.getText(), prenom_user.getText(), date_user.getValue(), mail_user.getText(),tn.TheInformants.Utils.passwordencryptor.encrypt(mdp_user.getText()) , LoginController.user1.getRole(), LoginController.user1.getImage(), LoginController.user1.getactif());
        serviceuser.Modiferuser(user, LoginController.user1.getUser_id());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sharedInterface/UI.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        LoginController.user1= user;
        
    }
}
