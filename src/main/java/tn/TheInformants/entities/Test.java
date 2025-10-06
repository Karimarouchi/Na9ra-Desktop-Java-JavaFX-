package tn.TheInformants.entities;

import tn.TheInformants.Enums.Categorie;
import tn.TheInformants.Enums.Status;
import tn.TheInformants.entities.Questiont;

import java.util.*;

public class Test {
    private int id_Test;
    private String nom_Test;
    private String description;
    private String temp_pris;
    private Status status;
    private Categorie categorie;
    private Map<Integer, String> resultats_Utilisateurs = new HashMap<>();

    private List<Questiont> Questions ;



    public Test(){
        this.resultats_Utilisateurs  = new HashMap<>();
        this.Questions=new ArrayList<>();

    }


    public Test(String nom_Test, String description, String temp_pris, Status status, Categorie categorie) {
        this.nom_Test = nom_Test;
        this.description = description;
        this.temp_pris = temp_pris;
        this.status = status;
        this.categorie = categorie;
        this.resultats_Utilisateurs  = new HashMap<>();
        this.Questions=new ArrayList<>();




    }

    public Test(int id_Test,String nom_Test, String description, String temp_pris, Status status, Categorie categorie) {
        this.id_Test = id_Test;
        this.nom_Test = nom_Test;
        this.description = description;
        this.temp_pris = temp_pris;
        this.status = status;
        this.categorie = categorie;
        this.resultats_Utilisateurs  = new HashMap<>();
        this.Questions=new ArrayList<>();




    }




    public int getId_Test() {
        return id_Test;
    }

    public String getNom_Test() {
        return nom_Test;
    }

    public String getDescription() {
        return description;
    }

    public String getTemp_pris() {
        return temp_pris;
    }

    public Status getStatus() {
        return status;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setId_Test(int id_Test) {
        this.id_Test = id_Test;
    }
    public void setNom_Test(String nom_Test) {
        this.nom_Test = nom_Test;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp_pris(String temp_pris) {
        this.temp_pris = temp_pris;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public void ajouterScore(int idUtilisateur, String score) {
        resultats_Utilisateurs.put(idUtilisateur, score);
    }
    public void modifierScore(int idUtilisateur, String nouveauScore)
    {        resultats_Utilisateurs.put(idUtilisateur, nouveauScore);
    };
    public void  supprimerScore(int idUtilisateur) {
        resultats_Utilisateurs.remove(idUtilisateur);
    }

    public String getScore(int idUtilisateur) {
        return resultats_Utilisateurs.getOrDefault(idUtilisateur, "Score non disponible");
    }
    public Map<Integer, String> getResultats_Utilisateurs() {
        return resultats_Utilisateurs;
    }
    public void ajouterQuestion(Questiont question) {
        Questions.add(question);
    }
    public void ajouterListQuestion(List<Questiont> questionlist) {
        this.Questions=questionlist;
    }
    public void supprimerQuestion(Questiont question) {
        Questions.remove(question);

    }
    public List<Questiont> getQuestions() {
        return Questions;
    }





    @Override
    public String toString() {
        return "Test{" +
                "id_Test=" + id_Test +
                ", nom_Test='" + nom_Test + '\'' +
                ", description='" + description + '\'' +
                ", temp_pris=" + temp_pris +
                ", status=" + status +
                ", categorie=" + categorie +
                '}';
    }


}
