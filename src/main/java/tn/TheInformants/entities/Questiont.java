package tn.TheInformants.entities;

import java.util.ArrayList;
import java.util.List;

public class Questiont {
    private int id_Question;
    private String text;
    //private int temp_imparti;
    private List<Reponse> Reponses ;

    public Questiont(){
        this.Reponses  = new ArrayList<>();


    }

    public Questiont(String text) {
        this.text = text;
       // this.temp_imparti = temp_imparti;
        this.Reponses  = new ArrayList<>();

    }

    public Questiont(int id_Question , String text) {
        this.id_Question = id_Question;
        this.text = text;
       // this.temp_imparti = temp_imparti;
        this.Reponses  = new ArrayList<>();

    }

    public int getId_Question() {
        return id_Question;
    }





    public String getText() {
        return text;
    }


  //  public int getTemp_imparti() {
      //  return temp_imparti;
   // }


    public void setId_Question(int id_Question) {
        this.id_Question = id_Question;
    }

    public void ajouterListReponses(List<Reponse> responselist) {
        this.Reponses=responselist;
    }

    public void setText(String text) {
        this.text = text;
    }


   // public void setTemp_imparti(int temp_imparti) {
    //    this.temp_imparti = temp_imparti;
   // }

    public void ajouterReponse(Reponse response) {
        Reponses.add(response);
    }
    public void supprimerReponse( Reponse response) {
        Reponses.remove(response);
    }
    public List<Reponse> getReponses() {
        return Reponses;
    }
    @Override
    public String toString() {
        return "Question{" +
                "id_Question=" + id_Question +
                ", text='" + text + '\'' +
              //  ", temp_imparti=" + temp_imparti +

                '}';
    }
}