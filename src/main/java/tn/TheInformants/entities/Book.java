package tn.TheInformants.entities;

public class Book {

    public Book() {

    }

    // Enum pour la disponibilité
    public enum Disponibilite {
        DISPO, NON_DISPO
    }

    // Enum pour la catégorie
    public enum Categorie {
        FICTION, NON_FICTION, SCIENCE_FICTION, MYSTERE, AUTRE
    }

    // Attributs de la classe Books
    private int id_liv;
    private String nom_liv;
    private Disponibilite disponibilite;
    private Categorie categorie;
    private double prix_liv;

    private String ImagePath;
    private String pdfPath;




    // Constructeur
    public Book(int id_liv, String nom_liv, Disponibilite disponibilite, Categorie categorie, double prix_liv,String ImagePath,String pdfPath) {
        this.id_liv = id_liv;
        this.nom_liv = nom_liv;
        this.disponibilite = disponibilite;
        this.categorie = categorie;
        this.prix_liv = prix_liv;
        this.ImagePath = ImagePath;
        this.pdfPath = pdfPath;


    }
    public Book( String nom_liv, Disponibilite disponibilite, Categorie categorie, double prix_liv,String ImagePath,String pdfPath) {

        this.nom_liv = nom_liv;
        this.disponibilite = disponibilite;
        this.categorie = categorie;
        this.prix_liv = prix_liv;
        this.ImagePath = ImagePath;
        this.pdfPath = pdfPath;

    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }
    // Méthodes getters et setters

    public int getId_liv() {
        return id_liv;
    }

    public void setId_liv(int id_liv) {
        this.id_liv = id_liv;
    }

    public String getNom_liv() {
        return nom_liv;
    }

    public void setNom_liv(String nom_liv) {
        this.nom_liv = nom_liv;
    }

    public Disponibilite getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Disponibilite disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public double getPrix_liv() {
        return prix_liv;
    }

    public void setPrix_liv(double prix_liv) {
        this.prix_liv = prix_liv;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id_liv=" + id_liv +
                ", nom_liv='" + nom_liv + '\'' +
                ", disponibilite=" + disponibilite +
                ", categorie=" + categorie +
                ", prix_liv=" + prix_liv +
                '}';
    }
}
