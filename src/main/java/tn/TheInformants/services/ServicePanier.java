package tn.TheInformants.services;

import tn.TheInformants.entities.Panier;
import tn.TheInformants.Utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicePanier implements Iservices<Panier> {

    private final Connection connection;
    private static ServicePanier instance;

    public ServicePanier() {
        connection = MyDataBase.getInstance().getConnection();
    }

    public static ServicePanier getInstance() throws SQLException {
        if (instance == null)
            instance = new ServicePanier();
        return instance;
    }

    @Override
    public void ajouter(Panier panier) throws SQLException {
        String sql = "INSERT INTO panier (id_panier, id_liv, total_price, nom_liv, imagePath, pdfPath,user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, panier.getId_panier());
            preparedStatement.setInt(2, panier.getId_liv());
            preparedStatement.setDouble(3, panier.getTotal_price());
            preparedStatement.setString(4, panier.getNom_liv());
            preparedStatement.setString(5, panier.getImagePath());
            preparedStatement.setString(6, panier.getPdfPath());
            preparedStatement.setInt(7, panier.getUser_id());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void modifier(Panier panier) throws SQLException {
        // Implement modification logic here
        // Example:
        // String sql = "UPDATE panier SET id_liv = ?, total_price = ?, nom_liv = ?, imagePath = ?, pdfPath = ? WHERE id_panier = ?";
    }

    @Override
    public void supprimer(Panier panier) throws SQLException {
        // Implement deletion logic here
        // Example:
        // String sql = "DELETE FROM panier WHERE id_panier = ?";
    }

    @Override
    public List<Panier> recuperer() throws SQLException {
        List<Panier> paniersList = new ArrayList<>();
        String sql = "SELECT * FROM panier";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Panier panier = new Panier();
                panier.setId_panier(rs.getInt("id_panier"));
                panier.setId_liv(rs.getInt("id_liv"));
                panier.setTotal_price(rs.getDouble("total_price"));
                panier.setNom_liv(rs.getString("nom_liv"));
                panier.setImagePath(rs.getString("imagePath"));
                panier.setPdfPath(rs.getString("pdfPath"));
                panier.setUser_id(rs.getInt("user_id"));

                paniersList.add(panier);
            }
        }
        return paniersList;
    }
}
