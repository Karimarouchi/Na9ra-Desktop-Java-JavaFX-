package tn.TheInformants.services;

import tn.TheInformants.entities.Question;
import tn.TheInformants.iservices.IService;
import tn.TheInformants.Utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceQuestion implements IService<Question> {
    private Connection connection;
    public ServiceQuestion(){
        connection= MyDataBase.getInstance().getConnection();
    }

    public void ajouter(Question question, int id) throws SQLException {
        String sql = "INSERT INTO `Question`(`quest`, `rep1`, `rep2`, `rep3`, `rep4`, `repc`,`quiz_id`) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,question.getQuest());
        statement.setString(2,question.getRep1());
        statement.setString(3,question.getRep2());
        statement.setString(4,question.getRep3());
        statement.setString(5,question.getRep4());
        statement.setString(6,question.getRepc());
        statement.setInt(7,id);
        statement.executeUpdate();
    }






    @Override
    public void ajouter(Question question) {

    }

    @Override
    public void modifier(Question question) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public List<Question> afficher() {
        return null;
    }



    public void modifier(Question question, int id) throws SQLException {
        String sql = "Update question set quest= ? , rep1= ? , rep2 = ?, rep3= ? , rep4= ? , repc= ? where id_quest = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,question.getQuest());
        statement.setString(2,question.getRep1());
        statement.setString(3,question.getRep2());
        statement.setString(4,question.getRep3());
        statement.setString(5,question.getRep4());
        statement.setString(6,question.getRepc());
        statement.setInt(7,question.getQuiz_id());
        statement.executeUpdate();
    }


}


