package tn.TheInformants.services;

import tn.TheInformants.entities.Book;
import tn.TheInformants.Utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceBook implements Iservices<Book> {
    private final Connection connection;

    private static ServiceBook instance;

    public ServiceBook() {
        connection = MyDataBase.getInstance().getConnection();
    }

    public static ServiceBook getInstance() throws SQLException {
        if (instance == null)
            instance = new ServiceBook();
        return instance;
    }

    @Override
    public void ajouter(Book book) throws SQLException {
        String sql = "INSERT INTO Books (id_liv, nom_liv, disponibilite_liv, categorie_liv, prix_liv, image_path, pdf_path) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, book.getId_liv());
            preparedStatement.setString(2, book.getNom_liv());
            preparedStatement.setString(3, book.getDisponibilite().toString());
            preparedStatement.setString(4, book.getCategorie().toString());
            preparedStatement.setDouble(5, book.getPrix_liv());
            preparedStatement.setString(6, book.getImagePath());
            preparedStatement.setString(7, book.getPdfPath());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void modifier(Book book) throws SQLException {
        String sql = "UPDATE books SET nom_liv = ?, disponibilite_liv = ?, categorie_liv = ?, prix_liv = ? WHERE id_liv = " + book.getId_liv();


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getNom_liv());
            preparedStatement.setString(2, book.getDisponibilite().toString());
            preparedStatement.setString(3, book.getCategorie().toString());
            preparedStatement.setDouble(4, book.getPrix_liv());
            //preparedStatement.setInt(5, book.getId_liv()); // Spécifie l'enregistrement à mettre à jour

            preparedStatement.executeUpdate();
        }
    }


    @Override
    public void supprimer(Book book) throws SQLException {
        String deletePanierSql = "DELETE FROM panier WHERE id_liv = ?";
        String deleteBooksSql = "DELETE FROM books WHERE id_liv = ?";

        try (PreparedStatement deletePanierStatement = connection.prepareStatement(deletePanierSql);
             PreparedStatement deleteBooksStatement = connection.prepareStatement(deleteBooksSql)) {
            connection.setAutoCommit(false);  // Start transaction

            // Delete from "panier" table
            deletePanierStatement.setInt(1, book.getId_liv());
            deletePanierStatement.executeUpdate();

            // Delete from "books" table
            deleteBooksStatement.setInt(1, book.getId_liv());
            deleteBooksStatement.executeUpdate();

            connection.commit();  // Commit transaction
        } catch (SQLException e) {
            connection.rollback();  // Rollback in case of exception
            throw e;
        } finally {
            connection.setAutoCommit(true);  // Enable auto-commit
        }
    }

    @Override
    public List<Book> recuperer() throws SQLException {
        List<Book> booksList = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book();
                book.setId_liv(rs.getInt("id_liv"));
                book.setNom_liv(rs.getString("Nom_liv"));
                book.setDisponibilite(Book.Disponibilite.valueOf(rs.getString("Disponibilite_liv")));
                book.setCategorie(Book.Categorie.valueOf(rs.getString("Categorie_liv")));
                book.setPrix_liv(rs.getDouble("Prix_liv"));
                book.setImagePath(rs.getString("image_path"));
                book.setPdfPath(rs.getString("pdf_path"));

                booksList.add(book);
            }
        }
        return booksList;
    }

    public double getTotalIncome() throws SQLException {
        double totalIncome = 0;
        String query = "SELECT SUM(Prix_liv) AS totalIncome FROM books";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                totalIncome = resultSet.getDouble("totalIncome");
            }
        }

        return totalIncome;
    }

    public int getTotalBooksCount() throws SQLException {
        int totalBooks = 0;
        String query = "SELECT COUNT(*) AS total FROM books";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                totalBooks = resultSet.getInt("total");
            }
        }

        return totalBooks;
    }
}