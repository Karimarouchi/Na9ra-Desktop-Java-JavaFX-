package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import tn.TheInformants.entities.cours;
import tn.TheInformants.entities.discussion;
import tn.TheInformants.services.ServiceCours;
import tn.TheInformants.services.ServiceDiscussion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UserCommentsListController implements Initializable {
    @FXML
    private Button CalendarL;

    @FXML
    private Button CommentL;

    @FXML
    private Label TitleLabel;

    @FXML
    private Label TitleLabel1;

    @FXML
    private TextField commentCmtField;

    @FXML
    private GridPane commentsListContainer;

    @FXML
    private Label coursDetailsDescription;

    @FXML
    private ImageView coursDetailsImg;

    @FXML
    private Label coursDetailsLevel;

    @FXML
    private Label coursDetailsTitle;

    @FXML
    private Button searchL;

    @FXML
    private Button searchL1;

    @FXML
    private TextField titleCmtField;

    private  int currentUserId ;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCours serviceCours = new ServiceCours();

        cours course = new cours();
        try {
             course = serviceCours.getById(cours.getIdCourse());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        coursDetailsTitle.setText(course.getTitre());
        coursDetailsDescription.setText(course.getDescription());
        coursDetailsLevel.setText(course.getNiveau());

        Image image = new Image(
                getClass().getResource("/uploads/" + course.getImagePath()).toExternalForm());
        coursDetailsImg.setImage(image);


        try {
            this.setCommentsGridPaneList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //récupérer l'utilisateur
       this.currentUserId= 1;
    }

    private void setCommentsGridPaneList() throws SQLException {
        ServiceDiscussion discussionService = new ServiceDiscussion();

        List<discussion> discussions = null;

        discussions = discussionService.getAllCourseComments(cours.getIdCourse());





        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < discussions.size(); i++) {
                System.out.println(discussions.get(i).toString());

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/User/userCommentsListItem.fxml"));
                VBox oneCommentCard = fxmlLoader.load();
                UserCommentsListItemController commentCardController = fxmlLoader.getController();
                commentCardController.setCommentData(discussions.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                commentsListContainer.add(oneCommentCard, column++, row);
              //  GridPane.setMargin(oneCommentCard, new Insets(10));
                commentsListContainer.setMargin(oneCommentCard, new Insets(0, 0,0, 20));
                //oneCommentCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AddBtn(ActionEvent event) throws SQLException {
        discussion disc = new discussion();
        disc.setTitreDiscussion(titleCmtField.getText());
        disc.setMessage(commentCmtField.getText());
        disc.setUser_id(LoginController.user1.getUser_id());
        disc.setIdCours(cours.getIdCourse());

        ServiceDiscussion serviceDiscussion = new ServiceDiscussion();

        if (containsBadWords(commentCmtField.getText()) || containsBadWords(titleCmtField.getText())  ) {
            System.out.println("bad word detected !");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Bad Word Detected !");
            alert.setContentText("Bad Word Detected !");
            alert.showAndWait();
            return;
        }

        serviceDiscussion.ajouter(disc);

        commentsListContainer.getChildren().clear();
        this.setCommentsGridPaneList();

        titleCmtField.setText("");
        commentCmtField.setText("");

    }

    @FXML
    void UpdateBtn(ActionEvent event) throws SQLException {
        discussion disc = new discussion();
        disc.setTitreDiscussion(titleCmtField.getText());
        disc.setMessage(commentCmtField.getText());
        disc.setIdDiscussion(discussion.getDiscussionID());

        ServiceDiscussion serviceDiscussion = new ServiceDiscussion();

        serviceDiscussion.modifier(disc);

        commentsListContainer.getChildren().clear();
        this.setCommentsGridPaneList();

        titleCmtField.setText("");
        commentCmtField.setText("");


    }

    public boolean containsBadWords(String comment) {
        try {
            File file = new File("src/main/resources/badwords.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String badWord = scanner.nextLine();
                if (comment.toLowerCase().contains(badWord.toLowerCase())) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }



}
