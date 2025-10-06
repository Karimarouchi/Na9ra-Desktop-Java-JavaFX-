package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import tn.TheInformants.entities.EVENTS;
import tn.TheInformants.entities.Feedback;
import tn.TheInformants.Enums.Status;
import tn.TheInformants.Enums.Typee;
import tn.TheInformants.Na9ra.Na9ra;
import tn.TheInformants.services.ServiceEvents;
import tn.TheInformants.services.Servicesondage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Events2Controller implements Initializable {
    @FXML
    private TableColumn<EVENTS, Date> DATEe;
    @FXML
    private TextField Input_EVENTS_nom;
    @FXML
    private GridPane itemlist;
    @FXML
    private AnchorPane event_int,mod_int;
    String imagepath,imagepath1;


    @FXML
    private TextArea Input_EVENTS_desc;

    @FXML
    private TextField Input_EVENTS_date;

    @FXML
    private ComboBox<String> Input_EVENTS_type;
    @FXML
    private ComboBox<String> Input_EVENTS_status;
    @FXML
    private TextField Input_EVENTS_idd;
    private TextArea Input_EVENTS_image;

    private List<EVENTS> q;
    @FXML
    private TextArea comment_mod,comment_add;
    public int event_gen_id;
public void setUpEditPagerate(Feedback feedback){
    modifier_rate_int.setVisible(true);
    comment_mod.setText(feedback.getREPONSE());

}
public void mod_comment_clicked() throws SQLException {
    String comment=comment_mod.getText();
    Feedback feedback=new Feedback(LoginController.user1.getUser_id(),event_gen_id,comment);
    Servicesondage servicesondage=new Servicesondage();
    servicesondage.modifier(feedback);
    modifier_rate_int.setVisible(false);

}

    public void affichefenetre(ActionEvent actionEvent) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Na9ra.class.getResource("/gui/User/EvenementUI2.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene=new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        event_int.setVisible(true);
        mod_int.setVisible(false);
        show();
        Input_EVENTS_type.getItems().clear();
        Input_EVENTS_status.getItems().clear();
        Input_EVENTS_type.getItems().add("FORMATION");
        Input_EVENTS_type.getItems().add("ATELIER");
        Input_EVENTS_type.getItems().add("HACKATHON");
        Input_EVENTS_status.getItems().add("ACTIF");
        Input_EVENTS_status.getItems().add("INACTIF");

    }

    public void setUpEditPage(EVENTS e) throws SQLException {
modifier_rate_int.setVisible(false);
        ajouter_rate_int.setVisible(false);
        rate_int.setVisible(false);
        event_int.setVisible(false);
        mod_int.setVisible(true);
        Input_EVENTS_nom.setText(String.valueOf(e.getNom()));
        Input_EVENTS_desc.setText(String.valueOf(e.getDescription()));
        Input_EVENTS_date.setText(String.valueOf(e.getDate()));
        Input_EVENTS_type.setValue(String.valueOf(Typee.valueOf(String.valueOf(e.getType()))));
        Input_EVENTS_status.setValue(String.valueOf(Status.valueOf(String.valueOf(e.getStatus()))));
        System.out.println(e.getId());
        Input_EVENTS_idd.setText(String.valueOf(e.getId()));

    }
    @FXML
    private AnchorPane modifier_rate_int,ajouter_rate_int,rate_int;
    public void setUpRatePage(EVENTS e) throws SQLException {
        modifier_rate_int.setVisible(false);
        ajouter_rate_int.setVisible(false);
        rate_int.setVisible(true);
        event_int.setVisible(false);
        mod_int.setVisible(false);
        showrate(e.getId());
        event_gen_id=e.getId();

    }
    public void add_rate_btn_clicked(){
        ajouter_rate_int.setVisible(true);

    }
    private static final String NEUTRINO_API_ENDPOINT = "https://neutrinoapi.com/bad-word-filter";
    private static final String API_USER_ID = "ahmeeeed";
    private static final String API_KEY = "h0TYS2ew9NwC03UJnSFd9zTDCPCr3FZsJuJAG7qjvEs81k0y";
    public void ajouter_comment_clicked() throws SQLException {
        String comment=comment_add.getText();
        Feedback feedback=new Feedback(LoginController.user1.getUser_id(),event_gen_id,comment);
        Servicesondage servicesondage=new Servicesondage();
        if(!containsBadWords(comment)){
        servicesondage.ajouter(feedback);}
        else{
            showAlert("Alert", "Your comment contains inappropriate language.");

        }

        ajouter_rate_int.setVisible(false);
    }
    private boolean containsBadWords(String comment) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(NEUTRINO_API_ENDPOINT + "?content=" + comment);
            request.addHeader("User-ID", API_USER_ID);
            request.addHeader("API-Key", API_KEY);
            HttpResponse response = httpClient.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JSONObject jsonResponse = new JSONObject(result.toString());
            return jsonResponse.getBoolean("is-bad");
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Assume no bad words in case of an error
        }
    }
    @FXML
    private TextField event_rech;
    public void show()
    {
        itemlist.getChildren().clear();

        ServiceEvents serviceEvents =new ServiceEvents();
        try {
            q =  serviceEvents.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!Objects.equals(event_rech.getText(), "")) {
            q = q.stream()
                    .filter(events -> events.getNom().contains(event_rech.getText()))
                    .collect(Collectors.toList());

        }

        int col=0;
        int row=1;
        for (EVENTS qzz : q ) {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/carte.fxml"));
            try {
                AnchorPane p =fxmlLoader.load();
                CarteController cntrll = fxmlLoader.getController();
                cntrll.setDATA(qzz,this);
                if(col==3){
                    col=0;
                    row++;
                }
                itemlist.add(p,col++,row);
                itemlist.setMargin(p,new Insets(10));
                //  itemlist.getChildren().add(p);
            } catch (
                    IOException ex) {
                throw new RuntimeException(ex);
            } }
    }
    List<Feedback> q1;
    @FXML
    private VBox feedback_pane;
    public void showrate(int id)
    {
        Servicesondage servicesondage =new Servicesondage();
        try {
            q1 =  servicesondage.afficher(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        for (Feedback qzz1 : q1 ) {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/rateitem.fxml"));
            try {
                AnchorPane p =fxmlLoader.load();
                RateItem cntrlll = fxmlLoader.getController();
                cntrlll.setDATA(qzz1,this);
                feedback_pane.getChildren().add(p);

            } catch (
                    IOException ex) {
                throw new RuntimeException(ex);
            } }
    }
    public void modevent_btn_clicked() throws SQLException {
       int idd= Integer.parseInt(Input_EVENTS_idd.getText());
        ServiceEvents serviceEvents = new ServiceEvents();
        String NOM;
        try {
            NOM = Input_EVENTS_nom.getText();
        }catch (IllegalArgumentException e) {
            showAlert("set name", "Please enter a  name ");
            return;}
        String descrp;
        try {
            descrp = Input_EVENTS_desc.getText();
        }catch (IllegalArgumentException e) {
            showAlert("set description", "Please enter a  description ");
            return;}


        Date DATE = null;
        try {
            DATE = Date.valueOf(Input_EVENTS_date.getText());
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Date", "Please enter a valid date (YYYY-MM-DD) format");
            return;
        }
        Typee TYPE = null;
        try {
            TYPE = Typee.valueOf(Input_EVENTS_type.getValue());
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Type", "Please enter a valid event type");
            return;
        }
        Status STATUS = null;
        try {
            STATUS = Status.valueOf(Input_EVENTS_status.getValue());
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Status", "Please enter a valid event status");
            return;
        }

        String image_url;
        try {
             image_url = "/gui/Rresources/" +imagepath;
        } catch (NumberFormatException e) {
            showAlert("Invalid User ID", "Please enter a valid user ID (numeric value)");
            return;
        }



        EVENTS events = new EVENTS(NOM, descrp, DATE, TYPE, STATUS, 2,image_url);

        if (serviceEvents.verifUser(events.getUserId()) == 0) {
            showAlert("User Not Found", "Sorry! There is no USER with the ID = " + events.getUserId() +
                    ". Please verify the User ID.");
        } else {
            serviceEvents.modifier(events,idd);
            event_int.setVisible(true);
            mod_int.setVisible(false);
            showAlert("Event Added", "Event added successfully");
        }
        Input_EVENTS_type.getItems().clear();
        Input_EVENTS_status.getItems().clear();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
