package tn.TheInformants.entities;

public class Quiz {
    private int quiz_id ;
    private String decrp;
    private String titre;
    private int nb_quest;
    private String image_url;

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    private String categorie;
    private int user_id;
    public Quiz(String decrp, String titre, int nb_quest, String categorie, int user_id,String image_url) {

        this.decrp = decrp;
        this.titre = titre;
        this.nb_quest = nb_quest;
        this.categorie = categorie;
        this.user_id = user_id;
        this.image_url=image_url;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public String getDecrp() {
        return decrp;
    }

    public String getTitre() {
        return titre;
    }

    public int getNb_quest() {
        return nb_quest;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public void setDecrp(String decrp) {
        this.decrp = decrp;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setNb_quest(int nb_quest) {
        this.nb_quest = nb_quest;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Quiz() {
    }
}
