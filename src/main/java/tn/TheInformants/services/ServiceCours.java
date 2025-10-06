package tn.TheInformants.services;

import tn.TheInformants.entities.cours;
import tn.TheInformants.Utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceCours implements Iservices<cours>{
    private Connection connection;
    private static ServiceCours instance;
    public ServiceCours()
    {
        connection= MyDataBase.getInstance().getConnection();
    }
    public static ServiceCours getInstance() throws SQLException{
        if(instance==null)
            instance=new ServiceCours();
        return instance;
    }
    @Override
    public void ajouter(cours cours) throws SQLException {
        String sql = "INSERT INTO cours ( titre, description, niveau,ImagePath,link) VALUES (' "+cours.getTitre()+"','"+cours.getDescription()+"','"+cours.getNiveau()+"','"+cours.getImagePath()+"','"+cours.getLink()+"')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void modifier(cours cours) throws SQLException {
        String sql= "UPDATE cours SET titre = ?, description = ? , niveau = ?  WHERE id = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, cours.getTitre());
        preparedStatement.setString(2, cours.getDescription());
        preparedStatement.setString(3, cours.getNiveau());
        preparedStatement.setInt(4,cours.getId());
        preparedStatement.executeUpdate();


    }

    @Override
    public void supprimer(cours cours) throws SQLException {
        String sql="DELETE FROM cours WHERE id =?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,cours.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public List<cours> recuperer() throws SQLException {
        List<cours> coursList=new ArrayList<>();
        String sql="SELECT * FROM cours";
        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);
        while (rs.next()){
            cours c=new cours();
            c.setId(rs.getInt("id"));
            c.setTitre(rs.getString("titre"));
            c.setDescription(rs.getString("description"));
            c.setNiveau(rs.getString("niveau"));
            c.setImagePath(rs.getString("ImagePath"));
            c.setLink(rs.getString("link"));
            System.out.println("c"+c);
            coursList.add(c);
        }
        System.out.println(coursList);
        return coursList;
    }

    public cours getById(int id) throws SQLException {
        String sql = "SELECT * FROM cours WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                cours c = new cours();
                c.setId(rs.getInt("id"));
                c.setTitre(rs.getString("titre"));
                c.setDescription(rs.getString("description"));
                c.setNiveau(rs.getString("niveau"));
                c.setImagePath(rs.getString("ImagePath"));
                c.setLink(rs.getString("link"));
                return c;
            }
        }
        return null;
    }


    public List<cours> search(String search) throws SQLException {
        List<cours> coursList = new ArrayList<>();
        String sql = "SELECT * FROM cours WHERE titre LIKE ? OR description LIKE ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                cours c = new cours();
                c.setId(rs.getInt("id"));
                c.setTitre(rs.getString("titre"));
                c.setDescription(rs.getString("description"));
                c.setNiveau(rs.getString("niveau"));
                c.setImagePath(rs.getString("ImagePath"));

                coursList.add(c);
            }
        }
        System.out.println(coursList);
        return coursList;
    }

}

