package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.TheInformants.entities.Score;
import tn.TheInformants.entities.user;
import tn.TheInformants.services.serviceuser;


public class HighScoreItem {
    @FXML
    private Label datehs;

    @FXML
    private Label namehs;

    @FXML
    private Label scoree;
    private QuizController quizController;
    private  Score score;
    public void setDATA(Score score, QuizController quizController){
        serviceuser users= new serviceuser();
        user u =users.rechercherUserParid(score.getUser_id());
        System.out.println(u.getNom());
        namehs.setText(u.getNom());
        datehs.setText(String.valueOf(score.getDatesc()));
        scoree.setText(String.valueOf(score.getScore()));
        this.quizController=quizController;
        this.score=score;


    }
}
