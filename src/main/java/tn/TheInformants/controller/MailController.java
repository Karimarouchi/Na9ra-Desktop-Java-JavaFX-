package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.TheInformants.entities.user;
import tn.TheInformants.services.SendMail;
import tn.TheInformants.services.serviceuser;
import tn.TheInformants.Utils.passwordencryptor;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MailController {
    boolean verification = false;
    static String emailaddress;
    @FXML
    private Button send_code;
    @FXML
    private Label error;
    @FXML
    private Button RETOUR;
    user user3=new user();
    @FXML
    private PasswordField fx_mod;
    @FXML
    private PasswordField fx_remod;

    public static String code_random() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();

    }
    public  static void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
    public static boolean alert_Box_verif_code(String title, String message) throws InterruptedException {

        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.WARNING;

        Alert alert = new Alert(Type, "");
        alert.setTitle(title);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;

    }
    public static String affichage_box_code(String code_random) throws InterruptedException {
        int i = 0;
        boolean test = false;

        while (i <= 2 && !test) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Code Verification");
            dialog.setContentText("A verification code is sent to your email");
            String code_saisie;

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {

                code_saisie = result.get();

                if (code_saisie.equals(code_random)) {
                    return "true";

                } else if (!code_saisie.equals(code_random) && i < 2) {
                    if (i == 0) {
                        test = true;
                        test = alert_Box_verif_code("Incorrect code", "You have two attempts left");

                    } else if (i == 1) {
                        test = true;

                        test = alert_Box_verif_code("Incorrect code", "You only have one attempt left");
                    }

                    i++;

                } else {

                    return "cancel";
                }

            } else {
                return "close";
            }
        }
        return "cancel";

    }

    public static void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    @FXML
    void  send_code(ActionEvent event) throws InterruptedException, IOException {
        serviceuser su = new serviceuser();
        SendMail sm = new SendMail();
        String code_random = "";
        String email = LoginController.mailST;
        emailaddress = LoginController.mailST;
        user3=su.rechercherUserParEmail(emailaddress);
        if (email == null || email.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs!");
            return; // ou toute autre action appropriée
        } else {
            String resultat = "";
            if (email.isEmpty()) {
                System.out.println("Veuillez remplir tous les champs!");
            } else if (!su.isValidEmail(email)) {
                System.out.println("Verifier Verifier Vos Données");
            } else { //email and id sonts correct
                code_random = code_random();
                sm.envoyerMail(email, "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
                resultat = affichage_box_code(code_random);
                if ("true".equals(resultat)) {
                    information_Box("Code Correct", "Votre Code est Correct Veuillez modifier votre mot de passe");
                    this.verification = true;
                    String modepasse=fx_mod.getText();
                    String remod=fx_remod.getText();
                    if (Objects.equals(modepasse, remod)){
                        remod=passwordencryptor.encrypt(modepasse);
                        user3.setPswd(remod);
                        su.Modiferuser(user3,user3.getUser_id());
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/login.fxml"));
                            Scene scene = new Scene(root, 1366, 768);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            // Gérer l'erreur si le chargement de la page échoue
                        }
                    }else {
                        error.setText("password error");
                    }


                } else if (!"close".equals(resultat)) {
                    alert_Box("Code Incorrect", "Vous avez atteint toutes vos tentaives,Ressayez Plus Tard");

                    this.verification = false;
                }

            }
        }
    }

    @FXML
    public void RETOUR(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sharedInterface/LOGIN.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}


