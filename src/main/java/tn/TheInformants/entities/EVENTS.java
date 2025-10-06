package tn.TheInformants.entities;

import tn.TheInformants.Enums.Status;
import tn.TheInformants.Enums.Typee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EVENTS {
    private int EVENT_ID;
    private String NOM;
    private String DESCRIPTION;
    private Date DATE;
    private int USER_ID1;
    private List<user> ETUDIANTS ;
    private Typee TYPE;
    private Status STATUS;
    private String image_url;

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public EVENTS(){}

    public EVENTS(int EVENT_ID, String nom, String description, Date date, Typee type, Status status) {
        this.EVENT_ID = EVENT_ID;
        this.NOM = nom;
        this.DESCRIPTION = description;
        this.DATE = date;
        this.ETUDIANTS = new ArrayList<>();
        this.TYPE = type;
        this.STATUS = status;

    }

    public void setUSER_ID1(int USER_ID1) {
        this.USER_ID1 = USER_ID1;
    }

    public EVENTS(String nom, String description, Date date, Typee type, Status status, int id_User, String image_url) {
        this.NOM = nom;
        this.DESCRIPTION = description;
        this.DATE = date;
        this.ETUDIANTS = new ArrayList<>();
        this.TYPE = type;
        this.STATUS = status;
        this.USER_ID1 = id_User;
        this.image_url=image_url;

    }



    public void setId(int EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }

    public void setNom(String nom) {
        this.NOM = nom;
    }

    public void setDescription(String description) {
        this.DESCRIPTION = description;
    }

    public void setDate(Date date) {
        this.DATE = date;
    }





    public void setType(Typee type) {
        this.TYPE = type;
    }

    public void setStatus(Status status) {
        this.STATUS = status;
    }

    public int getId() {
        return EVENT_ID;
    }

    public  String getNom() {
        return NOM;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public Date getDate() {
        return DATE;
    }


    public int getUserId() {
        return USER_ID1;
    }


    public Typee getType() {
        return TYPE;
    }

    public Status getStatus() {
        return STATUS;
    }

    @Override
    public String toString() {
        return "EVENTS{" +
                "id=" + EVENT_ID +
                ", nom='" + NOM + '\'' +
                ", description='" + DESCRIPTION + '\'' +
                ", date=" + DATE +
                ", type=" + TYPE +
                ", status=" + STATUS +
                '}';
    }
}
