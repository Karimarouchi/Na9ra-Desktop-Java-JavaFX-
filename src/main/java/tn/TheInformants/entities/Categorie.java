package tn.TheInformants.entities;

public class Categorie {
    private int id_categorie;
    private String name;
    private String description;
    public Categorie(){}

    public Categorie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Categorie(int id_categorie, String name, String description) {
        this.id_categorie = id_categorie;
        this.name = name;
        this.description = description;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id_categorie=" + id_categorie +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

