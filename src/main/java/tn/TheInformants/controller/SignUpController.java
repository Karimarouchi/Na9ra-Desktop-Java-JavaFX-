package tn.TheInformants.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.TheInformants.entities.user;
import tn.TheInformants.Enums.Role;
import tn.TheInformants.services.SendMail;
import tn.TheInformants.services.serviceuser;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.EventObject;

public class SignUpController {

    serviceuser serviceuser = new serviceuser();

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_prenom;

    @FXML
    private DatePicker tf_datenes;

    @FXML
    private TextField tf_mail;

    @FXML
    private ChoiceBox<String> cb_role;

    @FXML
    private Button fx_login;

    @FXML
    private Button fxsinup;

    @FXML
    private Button fxedit;
    @FXML
    private Label fx_erro;
    @FXML
    private Label fx_pswd_eroor;
    @FXML
    private Label fx_date_eroor1;
    @FXML
    private ImageView image_user;
    File selectedFile;
    public String url_image;
    private String path;
    @FXML
    private PasswordField tf_pass;
    boolean verification = false;
    @FXML
    private Label error;
    int verfi=-1;

    @FXML
    void initialize() {
        cb_role.getItems().add(Role.PROF.toString());
        cb_role.getItems().add(Role.ETUDIANT.toString());
        fxedit.setVisible(false);
        fxedit.setManaged(false);
        fxsinup.setVisible(true);
        fxsinup.setManaged(true);

        image_user.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        image_user.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        image_user.setImage(new Image("file:" + file.getAbsolutePath()));
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
        image_user.setImage(new Image("file:C:\\Users\\user\\Desktop\\drag-drop.gif"));
        ;

    }

    @FXML
    void loginclik(MouseEvent event) {
        try {
            // Charger le fichier FXML de la page d'inscription
            Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/login.fxml"));

            // Créer une nouvelle scène avec le contenu chargé à partir du fichier FXML
            Scene scene = new Scene(root, 1366, 768);

            // Obtenir la fenêtre actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène sur la fenêtre
            stage.setScene(scene);
            stage.show();
            serviceuser serviceuser = new serviceuser();

        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'erreur si le chargement de la page échoue
        }
    }

    @FXML
    void sign_up(MouseEvent event) throws SQLException, IOException, InterruptedException {
        LocalDate selectedDate = tf_datenes.getValue();
        LocalDate currentDate = LocalDate.now();

        String email = tf_mail.getText();


        if (serviceuser.isValidEmail(email)) {


            if (!serviceuser.checkemail(tf_mail.getText())) {

                if(serviceuser.isValidPassword(tf_pass.getText())) {
                    if (!selectedDate.isAfter(currentDate)) {


                        user user1 = new user(tf_nom.getText(), tf_prenom.getText(), tf_datenes.getValue(), tf_mail.getText(), tn.TheInformants.Utils.passwordencryptor.encrypt(tf_pass.getText()), null, url_image,0);

                        if (cb_role.getValue() == "PROF")
                            user1.setRole(Role.PROF);
                        else if (cb_role.getValue() == "ETUDIANT")
                            user1.setRole(Role.ETUDIANT);
                        send_code();
                        if (verfi==1){serviceuser.Ajouteruser(user1);}
                        serviceuser serviceuser = new serviceuser();
                        Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/login.fxml"));
                        Scene scene = new Scene(root, 1366, 768);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                    }else {fx_date_eroor1.setText("!!!date non valide!!!");
                        fx_erro.setVisible(false);
                        fx_pswd_eroor.setVisible(false);}
                }  else {
                    fx_erro.setVisible(false);
                    fx_pswd_eroor.setText("!!!Invalid pasword!!!");}
            } else {
                fx_erro.setText("!!! Email alredy exists . !!!!");
            }
        } else {
            fx_erro.setText("!!! Invalid email format . !!!!");
        }


    }

    @FXML
    public void image_add(Event event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Please choose the image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            // Load the selected image into the image view
            Image image1 = new Image(selectedFile.toURI().toString());

            //url_image = file.toURI().toString();
            System.out.println(selectedFile.toURI().toString());
            image_user.setImage(image1);

            // Create a new file in the destination directory
            File destinationFile = new File("C:\\Users\\user\\Desktop\\karim FINAL\\karim FINAL\\karim\\public\\uploads\\files\\" + selectedFile.getName());
            url_image = selectedFile.getName();

            try {
                // Copy the selected file to the destination file
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println(e);
            }

        }
    }
    void send_code() throws InterruptedException, IOException {
        verfi=0;
        String resultat = "";
        user user3;
        serviceuser su = new serviceuser();
        SendMail sm = new SendMail();
        String code_random = "";
        String email = tf_mail.getText();
        user3=su.rechercherUserParEmail(email);
        if (email == null || email.isEmpty()) {
            System.out.println("Please complete all fields !");
            return; // ou toute autre action appropriée
        } else {
            if (email.isEmpty()) {
                System.out.println("Please complete all fields!");
            } else {//email and id sonts correct
                code_random = MailController.code_random() ;
                sm.envoyerMail(email, "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
                resultat = MailController.affichage_box_code(code_random);
                if ("true".equals(resultat)) {
                    MailController.information_Box("Correct Code", "Your Code is Correct ");
                    verfi=1;
                    this.verification = true;}
                else  {
                    MailController.alert_Box("Incorrect code", "You have achieved all your attempts, Try Again Later");
                    verfi=2;
                    this.verification = false;
                }

            }
        }
    }}








