package tn.TheInformants.controller;


import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.TheInformants.entities.user;
import tn.TheInformants.Enums.Role;

import tn.TheInformants.services.serviceuser;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController  implements Initializable  {
    serviceuser serviceuser = new serviceuser();
    @FXML
    private Label wornglogin;
    @FXML
    private Button log_in;
    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField tf_pswd;

    static user user1 = new user();
    static String mailST;
    @FXML
    private Label forget;

    @Deprecated
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
    @FXML
    void log_in(MouseEvent event) throws SQLException  {


            String email = tf_email.getText();
            String password = tf_pswd.getText();

            // Vérification de l'existence de l'utilisateur
        if (email.isEmpty()){
            wornglogin.setText("emailis empty");
        }else if (password.isEmpty()){
            wornglogin.setText("password  empty");
        }


            if (serviceuser.checkUserExistence(email, password)) {
                if (serviceuser.checkUserAccessByEmail(email)==0){

                wornglogin.setText("Utilisateur trouvé !");
                     user1=serviceuser.rechercherUserParEmail(email);
                try {
                    System.out.println(user1);
                   if(user1.getRole().equals(Role.ADMIN) ){
                       log_in.getScene().getWindow().hide();

                       Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/AI.fxml"));

                       // Create a scene with transparent fill
                       Stage stage =new Stage();
                       Scene scene =new Scene(root);
                       scene.getStylesheets().add(getClass().getResource("/gui/Rresources/AI.css").toExternalForm());
                       stage.setScene(scene);
                       scene.setFill(null);

                       stage.initStyle(StageStyle.TRANSPARENT);
                       stage.show();
                  }
                   else if (user1.getRole().equals(Role.PROF)){
                       log_in.getScene().getWindow().hide();

                       Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/UI.fxml"));

                       // Create a scene with transparent fill
                       Stage stage =new Stage();
                       Scene scene =new Scene(root);
                       scene.getStylesheets().add(getClass().getResource("/gui/Rresources/UI.css").toExternalForm());
                       stage.setScene(scene);
                       scene.setFill(null);

                       stage.initStyle(StageStyle.TRANSPARENT);
                       stage.show();
                   }else {
                       log_in.getScene().getWindow().hide();

                       Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/UI.fxml"));

                       // Create a scene with transparent fill
                       Stage stage =new Stage();
                       Scene scene =new Scene(root);
                       scene.getStylesheets().add(getClass().getResource("/gui/Rresources/UI.css").toExternalForm());
                       stage.setScene(scene);
                       scene.setFill(null);

                       stage.initStyle(StageStyle.TRANSPARENT);
                       stage.show();}



                } catch (IOException e) {
                    e.printStackTrace();
                    // Gérer l'erreur si le chargement de la page échoue
                }

            } else { wornglogin.setText("compte desactiver!");}}
                else{

                wornglogin.setText("Utilisateur non trouvé !");
            }
        }
    @FXML
    void tf_singup(MouseEvent event) {

        try {
            // Charger le fichier FXML de la page d'inscription
            Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/sinup.fxml"));

            // Créer une nouvelle scène avec le contenu chargé à partir du fichier FXML
            Scene scene = new Scene(root, 1366, 768);

            // Obtenir la fenêtre actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène sur la fenêtre
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'erreur si le chargement de la page échoue
        }


    }


    @FXML
    public void forgetpass(Event event) {
        try {
            mailST=tf_email.getText();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/mail.fxml"));

        // Créer une nouvelle scène avec le contenu chargé à partir du fichier FXML
        Scene scene = new Scene(root);

        // Obtenir la fenêtre actuelle à partir de l'événement
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Définir la nouvelle scène sur la fenêtre
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }}}











