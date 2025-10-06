package tn.TheInformants.entities;

import java.util.ArrayList;
import java.util.List;

public class Reponse {
    private int id_Reponse;

    private Boolean isCorrect;
    private String reponse;
    public Reponse(){

    }

    public Reponse(String reponse,Boolean iscorrect) {
        this.reponse = reponse;
        this.isCorrect=iscorrect;

    }

    public Reponse(int id_Reponse, String reponse,Boolean iscorrect) {
        this.id_Reponse = id_Reponse;
        this.reponse = reponse;
        this.isCorrect=iscorrect;

    }

    public int getId_Reponse() {
        return id_Reponse;
    }

    public String getReponse() {
        return reponse;
    }



    public void setId_Reponse(int id_Reponse) {
        this.id_Reponse = id_Reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id_Reponse=" + id_Reponse +
                ", reponse='" + reponse + '\'' +
                ",is_correct=" + isCorrect + '\'' +

                '}';
    }


}
