package tn.TheInformants.services;

import tn.TheInformants.entities.EVENTS;
import tn.TheInformants.Enums.Status;
import tn.TheInformants.Enums.Typee;
import tn.TheInformants.Utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEvents implements  IService<EVENTS> {
    private Connection connection;

    public ServiceEvents(){
        connection= MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(EVENTS events) throws SQLException {
        String sql = "INSERT INTO events ( `NOM`, `DESCRIPTION`,`typee`,`DATEe`,`STATUS`,`image_url`,`userid_`) VALUES  (?,?,?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,events.getNom());
        statement.setString(2,events.getDescription());
        statement.setDate(4,events.getDate());
        System.out.println(events.getStatus());
        statement.setString(5,String.valueOf( events.getStatus()));
        statement.setString(3, String.valueOf(events.getType()));
        statement.setString(6, events.getImage_url());
        statement.setInt(7, events.getUserId());
        statement.executeUpdate();

    }

    @Override
    public void modifier(EVENTS events) throws SQLException {

    }

    public int verifUser(int user_id) throws SQLException {

        String sql = "SELECT COUNT(*)  AS recordCount FROM  user  where user_id= '"+user_id+"'";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);// prepared statement on ecrit la requete et puis on ajoute les attributs, seulement execution se repete, utiliser lorsque on a des champs a ajouter (insert/update)
        ResultSet r=preparedStatement.executeQuery(sql);
        r.next();
        int count = r.getInt("recordCount");
        r.close();
        return  count;
    }



    public void modifier(EVENTS events, int id) throws SQLException {
        System.out.println(events.getId());
        String sql = "UPDATE events set nom = ?, description = ? ,datee = ?,status =?,typee = ?  where event_id = ? ";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);// prepared statement on ecrit la requete et puis on ajoute les attributs, seulement execution se repete, utiliser lorsque on a des champs a ajouter (insert/update)
        preparedStatement.setString(1,events.getNom());
        preparedStatement.setString(2,events.getDescription());
        preparedStatement.setDate(3,events.getDate());
        preparedStatement.setString(4,String.valueOf( events.getStatus()));
        preparedStatement.setString(5, String.valueOf(events.getType()));
        preparedStatement.setString(6, String.valueOf(id));
        preparedStatement.executeUpdate();

    }

    @Override
    public void supprimer(int EVENT_ID) throws SQLException {
        String sql= "delete from events where EVENT_ID = ?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setInt(1,EVENT_ID);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<EVENTS> afficher() throws SQLException {
        List<EVENTS> events= new ArrayList<>();
        String sql = "select * from events";
        Statement statement =connection.createStatement();//retounne list events
        ResultSet rs = statement.executeQuery(sql);// on va enregistrer la liste des events dans result set qui stock les donnes sous forme des rows
        while (rs.next()) {
            EVENTS e = new EVENTS();

            e.setNom(rs.getString("NOM"));
            e.setId(rs.getInt("event_id"));
            e.setDescription(rs.getString("DESCRIPTION"));
            e.setStatus(Status.valueOf(rs.getString("STATUS")));
            e.setDate(rs.getDate("datee"));
            e.setType(Typee.valueOf(rs.getString("TYPEe")));
            e.setUSER_ID1(rs.getInt("user_id"));
            e.setImage_url(rs.getString("image_url"));

            events.add(e);
        }
        return events;
    }
    public void  ajouterUSER(int EVENT_ID, int USER_ID) throws SQLException {


        String sql = "INSERT INTO USER_EVENTS (EVENT_ID,USER_ID) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,EVENT_ID);
        preparedStatement.setInt(2,USER_ID);
        preparedStatement.executeUpdate();


    }
}
