package tn.TheInformants.entities;

import java.util.Date;

public class Score {
    int id_score;
    int score;
    Date datesc;
    int quiz_id;

    public int getId_score() {
        return id_score;
    }

    public Score() {
    }

    public Score(int score, int quiz_id, int user_id) {
        this.score = score;
        this.quiz_id = quiz_id;
        this.user_id = user_id;
    }

    public void setId_score(int id_score) {
        this.id_score = id_score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDatesc() {
        return datesc;
    }

    public void setDatesc(Date datesc) {
        this.datesc = datesc;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    int    user_id;
}
