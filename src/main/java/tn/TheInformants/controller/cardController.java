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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tn.TheInformants.entities.user;
import tn.TheInformants.Enums.Role;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class cardController implements Initializable {

    @FXML
    private Rectangle fx_rectangle;

    @FXML
    private Label fx_name;

    @FXML
    private Label fx_prenom;

    @FXML
    private Label fx_role;

    @FXML
    private ImageView fx_image;

    @FXML
    private Button fx_modif;

    @FXML
    private Button fx_supprimer;

    @FXML
    private ImageView fx_image_modif;

    private MyListener myListener;
    private user user;
    //static user r = new user();
    private int id;
    @FXML
    private ImageView fxactif;
    private int actif;
    static user user1 = new user();
    String t;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fxactif.setVisible(true);
    }

    public void setData(int user_id, String nom, String prenom, LocalDate datenes, String mail, String pswd, Role role, String image, int actif, MyListener myListener) throws FileNotFoundException {
        this.id = user_id;
        fx_name.setText(nom);
        fx_prenom.setText(prenom);
        System.out.println("jeimage"+image);
        if(image.equals("null")){t="user.png";}else{t=image;}
        fx_role.setText(role.toString());
        String fullurl = "C:\\Users\\user\\Desktop\\karim FINAL\\karim FINAL\\karim\\public\\uploads\\files\\" + t;
        System.out.println("full url " + fullurl);
        String furl;
        if (actif == 1) {
            furl = "@../../../../../../../../xampp/htdocs/user_image/desactiver.png";
        } else {
            furl = "@../../../../../../../../xampp/htdocs/user_image/verifier.png";
        }

        fx_modif.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Admin/modifierUI.fxml"));
                Parent modifyFormContent = loader.load();
                this.user1 = new user(user_id, nom, prenom, datenes, mail, pswd, role,t, actif);
                System.out.println(user1);
                modifierController modifyFormController = loader.getController();
                System.out.println(modifyFormController);
                modifyFormController.setDate(user1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        fxactif.setImage(new Image(new FileInputStream(furl)));

        try {
            fx_image.setImage(new Image(new FileInputStream(fullurl)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e);
        }
    }

    @FXML
    public void modifier_user(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Admin/modifierUI.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public interface MyListener {
        void onClick(user user);
    }
}

