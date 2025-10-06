package tn.TheInformants.services;

import tn.TheInformants.Utils.MyDataBase;
import tn.TheInformants.entities.Reponse;
import tn.TheInformants.iservices.IService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicesReponses implements IService<Reponse>{
    private Connection connection;

    public ServicesReponses() {
        connection = MyDataBase.getInstance().getConnection();
    }

    public void ajouter(Reponse reponse) throws SQLException {
        String sql = "INSERT INTO reponse (reponse,is_correct) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, reponse.getReponse());
        preparedStatement.setBoolean(2, reponse.getCorrect());

        preparedStatement.executeUpdate();
    }

    @Override
    public void modifier(Reponse reponse) throws SQLException {
        System.out.println("in the modify response "+reponse);
        String sql = "UPDATE reponse SET reponse=? , is_correct=? WHERE id_Reponse=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, reponse.getReponse());
        preparedStatement.setBoolean(2, reponse.getCorrect());
        preparedStatement.setInt(3, reponse.getId_Reponse());

        preparedStatement.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public List<Reponse> afficher() throws SQLException {
        return null;
    }


    public void supprimer(Reponse reponse) throws SQLException {
        String sql = "DELETE FROM reponse WHERE id_Reponse=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, reponse.getId_Reponse());
        preparedStatement.executeUpdate();
    }


    public List<Reponse> recuperer() throws SQLException {
        List<Reponse> list = new ArrayList<>();
        String sql = "SELECT * FROM reponse";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Reponse r = new Reponse();
            r.setId_Reponse(rs.getInt("id_Reponse"));
            r.setReponse(rs.getString("reponse"));
            r.setCorrect(rs.getBoolean("is_correct"));

            list.add(r);
        }
        return list;
    }
    public int recupererIdReponseParReponse(String reponse) throws SQLException {
        int idReponse = -1;

        String sql = "SELECT id_Reponse FROM reponse WHERE reponse = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, reponse);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            idReponse = resultSet.getInt("id_Reponse");
        }

        return idReponse;
    }
    public Reponse recupererReponseParText(String text) throws SQLException {
        Reponse reponse = null;

        String sql = "SELECT * FROM reponse WHERE reponse = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, text);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id_Reponse");
                    boolean isCorrect = resultSet.getBoolean("is_correct");
                    String reponseText = resultSet.getString("reponse");
                    reponse = new Reponse(id, reponseText, isCorrect);
                }
            }
        }

        return reponse;
    }

    public Reponse recupererReponseParId(int id) throws SQLException {
        Reponse reponse = null;

        String sql = "SELECT * FROM reponse WHERE id_Reponse = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int responseId = resultSet.getInt("id_Reponse");
                    boolean isCorrect = resultSet.getBoolean("is_correct");
                    String responseText = resultSet.getString("reponse");
                    reponse = new Reponse(responseId, responseText, isCorrect);
                }
            }
        }

        return reponse;
    }



}







