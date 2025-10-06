package tn.TheInformants.entities;

import tn.TheInformants.Enums.Role;

import java.time.LocalDate;

public class user {
    private int user_id;
    private String nom ;
    private String prenom;

    private LocalDate datenes;
    private String mail ;
    private String pswd;
    private Role role;
    private String image;
    private int actif;

    public user() {
    }

    public user(int user_id, String nom, String prenom, LocalDate datenes, String mail, String pswd, Role role, String image,int actif) {
        this.user_id = user_id;
        this.nom = nom;
        this.prenom = prenom;
        this.datenes = datenes;
        this.mail = mail;
        this.pswd = pswd;
        this.role = role;
        this.image = image;
        this.actif=actif;
    }

    public user(String nom, String prenom, LocalDate datenes, String mail, String pswd, Role role, String image,int actif) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenes = datenes;
        this.mail = mail;
        this.pswd = pswd;
        this.role = role;
        this.image = image;
        this.actif=actif;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getactif(){
        return actif;
    }
    public void setActif(int actif){
        this.actif=actif;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDatenes() {
        return datenes;
    }

    public void setDatenes(LocalDate datenes) {
        this.datenes = datenes;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "user{" +
                "user_id=" + user_id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", datenes=" + datenes +
                ", mail='" + mail + '\'' +
                ", pswd='" + pswd + '\'' +
                ", role=" + role +
                ", image='" + image + '\'' +
                ", actif=" + actif +
                '}';
    }
}
