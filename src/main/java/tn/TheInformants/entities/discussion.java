package tn.TheInformants.entities;
import java.util.Date;

public class discussion {


        private int idDiscussion;
        private int idCours;
        private String titreDiscussion;
        private String message;
        private Date datePost;
        private int user_id;

        private  static  int discussionID;


    public static int getDiscussionID() {
        return discussionID;
    }

    public static void setDiscussionID(int discussionID) {
        discussion.discussionID = discussionID;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


public discussion(){}
    public discussion(int idDiscussion, int idCours, String titreDiscussion, String message, Date datePost) {
        this.idDiscussion = idDiscussion;
        this.idCours = idCours;
        this.titreDiscussion = titreDiscussion;
        this.message = message;
        this.datePost = datePost;
    }

    public int getIdDiscussion() {
        return idDiscussion;
    }

    public void setIdDiscussion(int idDiscussion) {
        this.idDiscussion = idDiscussion;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getTitreDiscussion() {
        return titreDiscussion;
    }

    public void setTitreDiscussion(String titreDiscussion) {
        this.titreDiscussion = titreDiscussion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    @Override
    public String toString() {
        return "discussion{" +
                "idDiscussion=" + idDiscussion +
                ", idCours=" + idCours +
                ", titreDiscussion='" + titreDiscussion + '\'' +
                ", message='" + message + '\'' +
                ", datePost=" + datePost +
                '}';
    }
}
