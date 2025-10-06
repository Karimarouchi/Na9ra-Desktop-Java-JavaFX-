package tn.TheInformants.services;

import tn.TheInformants.entities.Feedback;
import tn.TheInformants.Utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Servicesondage implements IService<Feedback>{
    private Connection connection;
    public Servicesondage(){
        connection= MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Feedback sondageEtFeedback) throws SQLException {
        String sql = "INSERT INTO `feedback`(  `USER_ID`,`EVENT_ID`,`REPONSE`,`date`) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);// prepared statement on ecrit la requete et puis on ajoute les attributs, seulement execution se repete, utiliser lorsque on a des champs a ajouter (insert/update)
        preparedStatement.setInt(1,sondageEtFeedback.getUSER_ID());
        preparedStatement.setInt(2, sondageEtFeedback.getEVENT_ID());
        preparedStatement.setString(3,sondageEtFeedback.getREPONSE());
        preparedStatement.setString(4, String.valueOf(sondageEtFeedback.getDATE()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void modifier(Feedback sondageEtFeedback) throws SQLException {
        String sql = "update feedback  set date=?,REPONSE=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);// prepared statement on ecrit la requete et puis on ajoute les attributs, seulement execution se repete, utiliser lorsque on a des champs a ajouter (insert/update)
        preparedStatement.setString(1, String.valueOf(sondageEtFeedback.getDATE()));
        preparedStatement.setString(2,sondageEtFeedback.getREPONSE());


        preparedStatement.executeUpdate();

    }

    @Override
    public void supprimer(int ID) throws SQLException {
        String sql= "delete from feedback where ID = ?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setInt(1,ID);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Feedback> afficher() throws SQLException {
        return null;
    }

    public List<Feedback> afficher(int id) throws SQLException {
        List<Feedback> sondage = new ArrayList<>();
        String sql = "SELECT * FROM feedback WHERE event_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery(); // Corrected: Removed sql parameter
        while (rs.next()) {
            Feedback s = new Feedback();
            s.setID(rs.getInt("ID"));
            s.setEVENT_ID(rs.getInt("EVENT_ID"));
            s.setUSER_ID(rs.getInt("USER_ID"));
            s.setDATE(rs.getDate("Date"));
            s.setREPONSE(rs.getString("REPONSE"));
            sondage.add(s);
        }
        return sondage;
    }

}
