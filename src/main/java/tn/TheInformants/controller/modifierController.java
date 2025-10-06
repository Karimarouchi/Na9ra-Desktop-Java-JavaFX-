package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.TheInformants.entities.user;
import tn.TheInformants.Enums.Role;
import tn.TheInformants.Enums.actif;
import tn.TheInformants.services.serviceuser;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class modifierController implements Initializable {

    @FXML
    private Button fxedit;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private DatePicker date;

    @FXML
    private TextField mail;

    @FXML
    private TextField mdp;

    @FXML
    private Label fx_date_eroor1;

    @FXML
    private Label fx_erro;

    @FXML
    private Label fx_pswd_eroor;


    @FXML
    private ChoiceBox<actif> fx_actif;
    @FXML
    private ChoiceBox<Role> role;
    @FXML
    private ImageView fx_image_supprimer;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        fx_actif.getItems().add(actif.INACTIF);
        fx_actif.getItems().add(actif.ACTIF);
        role.getItems().add(Role.PROF);
        role.getItems().add(Role.ADMIN);
        role.getItems().add(Role.ETUDIANT);

        System.out.println("jejeee"+user2);
        String mot= tn.TheInformants.Utils.passwordencryptor.decrypt(cardController.user1.getPswd());
        nom.setText(cardController.user1.getNom());
        prenom.setText(cardController.user1.getPrenom());
        date.setValue(cardController.user1.getDatenes());
        mail.setText(cardController.user1.getMail());
        mdp.setText("********");

        if (cardController.user1.getactif() == 0)
            fx_actif.setValue(tn.TheInformants.Enums.actif.ACTIF);
        else
            fx_actif.setValue(tn.TheInformants.Enums.actif.INACTIF);

        role.setValue(cardController.user1.getRole());

    }

user user2= new user();
    public void setDate(user user1){
        System.out.println("modifer");
        System.out.println(user1);
        // Assurez-vous que role.getValue() est différent de null avant de vérifier son contenu
        if (role.getValue() != null) {
            if (role.getValue().equals("PROF")) {
                user1.setRole(Role.PROF);
            } else if (role.getValue().equals("ETUDIANT")) {
                user1.setRole(Role.ETUDIANT);
            }
        }
        user2=user1;
        System.out.println("je sus"+user2);
        nom.setText(user2.getNom());
    }





    @Deprecated
    public void image_add(Event event) {
    }

    @FXML
    public void retour(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sharedInterface/AI.fxml"));
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
    public void modifier(ActionEvent actionEvent) throws SQLException, IOException {
        serviceuser serviceuser = new serviceuser();

        int a;

        if (fx_actif.getValue().toString() == "ACTIF")
            a = 0;
        else
            a = 1;

        user user = new user(nom.getText(), prenom.getText(), date.getValue(), mail.getText(), cardController.user1.getPswd(), Role.valueOf(role.getValue().toString()), cardController.user1.getImage(), a);

        serviceuser.Modiferuser(user, cardController.user1.getUser_id());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sharedInterface/AI.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        LoginController.user1= user;
    }


    @FXML
    public void suprimer(Event event) throws SQLException, IOException {
        serviceuser serviceusear = new serviceuser();
        serviceusear.Supprimeruser(cardController.user1.getUser_id());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sharedInterface/AI.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
