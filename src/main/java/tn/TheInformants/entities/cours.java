package tn.TheInformants.entities;



public class cours {
    private int id;
    private String titre;
    private String description;
    private String niveau;
    private String ImagePath;
    private String link;


    public static int getIdCourse() {
        return idCourse;
    }

    public static void setIdCourse(int idCourse) {
        cours.idCourse = idCourse;
    }

    private static int idCourse;
    public cours(){
    }
    public cours(int id, String titre, String description, String niveau, String ImagePath, String link) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.niveau = niveau;
        this.ImagePath=ImagePath;
        this.link=link;

    }

    public cours(String titre, String description, String niveau, String ImagePath, String link) {
        this.titre = titre;
        this.description = description;
        this.niveau = niveau;
        this.ImagePath=ImagePath;
        this.link=link;

    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getNiveau() {
        return niveau;
    }




    public void setId(int id) {

        // Validation : Vérifier si l'id est un entier positif
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID du cours doit être un entier positif.");
        }
        this.id = id;
    }


    public void setTitre(String titre) {

            // Vérifier si le titre est vide ou ne contient pas de lettres
            if (titre == null || titre.trim().isEmpty() || !titre.matches(".*[a-zA-Z].*")) {
                throw new IllegalArgumentException("Le titre doit contenir des lettres et ne peut pas être vide.");
            }
            this.titre = titre;
        }



    public void setDescription(String description) {

        this.description = description;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "cours{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", niveau='" + niveau + '\'' +
                ", ImagePath='" + ImagePath + '\'' +
                ", link='" + link + '\'' +

                '}';
    }
}
