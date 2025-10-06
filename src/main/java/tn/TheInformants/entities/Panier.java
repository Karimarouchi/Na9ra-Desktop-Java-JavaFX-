package tn.TheInformants.entities;

public class Panier {
private int id_panier;
private int id_liv;
private int user_id;

private double total_price;
private String nom_liv;
private String imagePath;

    private String pdfPath;
    public Panier() {

    }

    public void setNom_liv(String nom_liv) {
        this.nom_liv = nom_liv;
    }

    public String getNom_liv() {
        return nom_liv;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Panier(int id_panier, int id_liv, double total_price, String nom_liv, String imagePath,String pdfPath) {
        this.id_panier = id_panier;
        this.id_liv = id_liv;
        this.total_price = total_price;
        this.nom_liv = nom_liv;
        this.imagePath = imagePath;
        this.pdfPath = pdfPath;

    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public Panier(int id_liv, double total_price, String nom_liv, String imagePath,String pdfPath) {
        this.id_liv = id_liv;
        this.total_price = total_price;
        this.nom_liv = nom_liv;
        this.imagePath = imagePath;
        this.pdfPath = pdfPath;



    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", id_liv=" + id_liv +
                ", total_price=" + total_price +
                ", nom_liv='" + nom_liv + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", pdfPath='" + pdfPath + '\'' +
                '}';
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setId_liv(int id_liv) {
        this.id_liv = id_liv;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getId_panier() {
        return id_panier;
    }

    public int getId_liv() {
        return id_liv;
    }

    public double getTotal_price() {
        return total_price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
