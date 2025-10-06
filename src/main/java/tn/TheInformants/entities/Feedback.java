package tn.TheInformants.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Feedback {
   private int ID;
    private int USER_ID;
    private int EVENT_ID;
    private String REPONSE;
    private Date DATE;

    public Feedback() {
    }

    public Feedback(Feedback sondageEtFeedback){}

    public Feedback(int ID , int USER_ID, int EVENT_ID , String REPONSE) {
        this.ID = ID;
        this.EVENT_ID=EVENT_ID;
        this.USER_ID = USER_ID;
        this.REPONSE = REPONSE;
        LocalDateTime localDateTime = LocalDateTime.now();
        this.DATE = (Date) Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Feedback(int USER_ID, int EVENT_ID, String REPONSE) {
        this.EVENT_ID=EVENT_ID;
        this.USER_ID = USER_ID;
        this.REPONSE = REPONSE;

        LocalDateTime localDateTime = LocalDateTime.now();
        this.DATE =  Date.valueOf(localDateTime.toLocalDate());

    }



    public void setID(int ID) {
        this.ID = ID;
    }
    public void setEVENT_ID(int EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }


    public void setREPONSE(String REPONSE) {
        this.REPONSE = REPONSE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }



    public int getID() {
        return ID;
    }
    public int getEVENT_ID() {
        return EVENT_ID;
    }
    public int getUSER_ID() {
        return USER_ID;
    }


    public String getREPONSE() {
        return REPONSE;
    }

    public Date getDATE() {
        return DATE;
    }

}
