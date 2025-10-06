package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tn.TheInformants.entities.Quiz;
import tn.TheInformants.services.ServiceQuiz;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
public class ItemQuizzController implements Initializable {
    @FXML
    public Button mod_btn;
    @FXML
    private Button highscore_btn;
    @FXML
    public Button sup_btn;
    @FXML
    private Label categ;

    @FXML
    private Text descrp;

    @FXML
    private AnchorPane midlasttt;

    @FXML
    private Label id;

    @FXML
    private Label quest;
    @FXML
    private Rectangle aaa;
    @FXML
    private Label titre;
    private QuizController quizController;
    private  Quiz quiz;
    @FXML
    private ImageView imagev;

    @FXML
    void supp_btn_clicked() throws SQLException {
ServiceQuiz quiz  = new ServiceQuiz();
        quiz.supprimer(Integer.parseInt(id.getText()));
        quizController.itemlist.getChildren().clear();
        quizController.show();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mod_btn.setOnMouseClicked(e -> {

            try {

                quizController.setUpEditPage(quiz);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });

        highscore_btn.setOnMouseClicked(e -> {

            try {

                quizController.setUpHighScore(quiz);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });

        midlasttt.setOnMouseClicked(e -> {
            try {
                quizController.setUpPlayPage(quiz);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }


    public void setDATA(Quiz quiz,QuizController quizController){
        id.setText(String.valueOf(quiz.getQuiz_id()));
descrp.setText(quiz.getDecrp());
        titre.setText(quiz.getTitre());
        quest.setText(String.valueOf(quiz.getNb_quest()));
        descrp.setText(quiz.getDecrp());
categ.setText(quiz.getCategorie());
         //image = new ImageView(imageObject);
        this.quizController=quizController;


        this.quiz=quiz;
        aaa.setArcWidth(30.0);   // Corner radius
        aaa.setArcHeight(30.0);

        ImagePattern pattern = new ImagePattern(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream(quiz.getImage_url())), 244, 168, false, false) // Resizing
        );
        aaa.setFill(pattern);


    }


}
