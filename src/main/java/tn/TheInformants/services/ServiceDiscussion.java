package tn.TheInformants.services;

import tn.TheInformants.entities.discussion;
import tn.TheInformants.Utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDiscussion implements Iservices<discussion> {

    private Connection connection;
    private static ServiceCours instance;

    public ServiceDiscussion()
    {
        connection= MyDataBase.getInstance().getConnection();
    }
    public static ServiceCours getInstance() throws SQLException{
        if(instance==null)
            instance=new ServiceCours();
        return instance;
    }

    @Override
    public List<discussion> recuperer() throws SQLException {
        List<discussion> discussionList=new ArrayList<>();
        String sql="SELECT * FROM discussion";
        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);
        while (rs.next()){
            discussion c=new discussion();
            c.setIdDiscussion(rs.getInt("id_Discussion"));
            c.setIdCours(rs.getInt("id_Cours"));

            c.setTitreDiscussion(rs.getString("Titre_DISCUSSION"));
            c.setMessage(rs.getString("Message"));
            c.setDatePost(rs.getDate("date_Post"));
            c.setUser_id(rs.getInt("user_id"));

            discussionList.add(c);
        }
        System.out.println(discussionList);
        return discussionList;
    }

    @Override
    public void ajouter(discussion discussion) throws SQLException {
        String sql = "INSERT INTO discussion (id_Cours, Titre_DISCUSSION, Message, user_id) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, discussion.getIdCours());
        preparedStatement.setString(2, discussion.getTitreDiscussion());
        preparedStatement.setString(3, discussion.getMessage());
        System.out.println(discussion.getUser_id());
        preparedStatement.setInt(4, discussion.getUser_id());
        preparedStatement.executeUpdate();
    }

    @Override
    public void modifier(discussion discussion) throws SQLException {
        String sql = "UPDATE discussion SET Titre_DISCUSSION = ?, Message = ? WHERE id_Discussion = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, discussion.getTitreDiscussion());
        preparedStatement.setString(2, discussion.getMessage());
        preparedStatement.setInt(3, discussion.getIdDiscussion());
        preparedStatement.executeUpdate();
    }


    @Override
    public void supprimer(discussion discussion) throws SQLException {
        String sql = "DELETE FROM discussion WHERE id_Discussion = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, discussion.getIdDiscussion());
        preparedStatement.executeUpdate();
    }

    public discussion getById(int id) throws SQLException {
        String sql = "SELECT * FROM discussion WHERE id_Discussion = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            discussion c = new discussion();
            c.setIdDiscussion(rs.getInt("id_Discussion"));
            c.setIdCours(rs.getInt("id_Cours"));
            c.setTitreDiscussion(rs.getString("Titre_DISCUSSION"));
            c.setMessage(rs.getString("Message"));
            c.setDatePost(rs.getDate("date_Post"));
            c.setUser_id(rs.getInt("user_id"));
            return c;
        }

        return null; // Return null if no discussion with the given ID is found
    }

    public List<discussion> getAllCourseComments(int courseId) throws SQLException {
        List<discussion> courseComments = new ArrayList<>();
        String sql = "SELECT * FROM discussion WHERE id_Cours = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, courseId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    discussion c = new discussion();
                    c.setIdDiscussion(rs.getInt("id_Discussion"));
                    c.setIdCours(rs.getInt("id_Cours"));
                    c.setTitreDiscussion(rs.getString("Titre_DISCUSSION"));
                    c.setMessage(rs.getString("Message"));
                    c.setDatePost(rs.getDate("date_Post"));
                    c.setUser_id(rs.getInt("user_id"));

                    courseComments.add(c);
                }
            }
        }

        return courseComments;
    }
}
