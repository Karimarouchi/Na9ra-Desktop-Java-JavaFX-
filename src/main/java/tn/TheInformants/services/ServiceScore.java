package tn.TheInformants.services;

import tn.TheInformants.entities.Score;
import tn.TheInformants.iservices.IService;
import tn.TheInformants.Utils.MyDataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceScore implements IService<Score> {
    private static Connection connection;

    public ServiceScore(){
        connection= MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Score score) throws SQLException {
        String sql = "INSERT INTO `score`(`score`, `datesc`, `quiz_id`, `user_id`) VALUES (?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,score.getScore());
        LocalDateTime localDateTime = LocalDateTime.now();

        // Convert LocalDateTime to java.sql.Date
        Date sqlDate = Date.valueOf(localDateTime.toLocalDate());
        statement.setDate(2,sqlDate);
        System.out.println(score.getQuiz_id());
        statement.setInt(3,score.getQuiz_id());
        statement.setInt(4,score.getUser_id());
        statement.executeUpdate();
    }

    @Override
    public void modifier(Score score) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public List<Score> afficher(){
        return null;
    }


}
