package tn.TheInformants.services;

import tn.TheInformants.entities.Question;
import tn.TheInformants.entities.Quiz;
import tn.TheInformants.entities.Score;
import tn.TheInformants.iservices.IService;
import tn.TheInformants.Utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceQuiz implements IService<Quiz> {

    private static Connection connection;

    public ServiceQuiz(){
        connection= MyDataBase.getInstance().getConnection();
    }


    @Override
    public void ajouter(Quiz quiz) throws SQLException {
        String sql = "INSERT INTO `Quiz`(`decrp`, `titre`, `nb_quest`, `categorie`, `user_id`, `image_url`) VALUES (?,?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,quiz.getDecrp());
        statement.setString(2,quiz.getTitre());
        statement.setInt(3,quiz.getNb_quest());
        statement.setString(4,quiz.getCategorie());
        statement.setInt(5,quiz.getUser_id());
        statement.setString(6,quiz.getImage_url());
        statement.executeUpdate();

    }

    @Override
    public void modifier(Quiz quiz) throws SQLException {
        String sql = "Update quiz set decrp= ? , titre= ? , nb_quest = ?, categorie= ? , user_id= ?, image_url= ?  where quiz_id = ?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,quiz.getDecrp());
        preparedStatement.setString(2, quiz.getTitre());
        preparedStatement.setInt(3,quiz.getNb_quest());
        preparedStatement.setString(4, quiz.getCategorie());
        preparedStatement.setString(6,quiz.getImage_url());
        preparedStatement.setInt(5,quiz.getUser_id());
        preparedStatement.setInt(7,quiz.getQuiz_id());
        preparedStatement.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {

        String sql= "delete from quiz where quiz_id = ?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

    }
    public int returnid(String quest) throws SQLException {
        String sql = "select * from quiz where titre = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,quest); // Concatenate % to the parameter value
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            return rs.getInt("quiz_id");}
        return 0;
    }
    @Override
    public List<Quiz> afficher() throws SQLException {
        List<Quiz> quizs= new ArrayList<>();
        String sql = "select * from quiz";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            Quiz p = new Quiz();
            p.setQuiz_id(rs.getInt("quiz_id"));
            p.setDecrp(rs.getString("decrp"));
            p.setTitre(rs.getString("titre"));
            p.setNb_quest(rs.getInt("nb_quest"));
            p.setCategorie(rs.getString("categorie"));
            p.setUser_id(rs.getInt("user_id"));
            p.setImage_url(rs.getString("image_url"));

            quizs.add(p);
        }
        return quizs;
    }
    public List<Score> afficherscore(int quiz_id) throws SQLException {
        List<Score> score= new ArrayList<>();
        String sql = "select * from Score where quiz_id = ? order by score DESC ";
        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1,String.valueOf(quiz_id)); // Concatenate % to the parameter value
// Set the value of the first parameter to 'name'

        ResultSet rs = statement.executeQuery();
        int i=0;
        while (rs.next() && i<10){
            Score p = new Score();
            p.setQuiz_id(rs.getInt("quiz_id"));
            p.setId_score(rs.getInt("id_score"));
            p.setScore(rs.getInt("score"));
            p.setDatesc(rs.getDate("datesc"));
            p.setUser_id(rs.getInt("user_id"));


            score.add(p);
            i++;
        }
        return score;
    }





    public void modifier(Quiz quiz, int id) throws SQLException {
        String sql = "Update quiz set decrp= ? , titre= ? , nb_quest = ?, categorie= ? , user_id= ?, image_url= ?  where quiz_id = ?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,quiz.getDecrp());
        preparedStatement.setString(2, quiz.getTitre());
        preparedStatement.setInt(3,quiz.getNb_quest());
        preparedStatement.setString(4, quiz.getCategorie());
        preparedStatement.setString(6,quiz.getImage_url());
        preparedStatement.setInt(5,quiz.getUser_id());
        preparedStatement.setInt(7,id);
        preparedStatement.executeUpdate();
    }


    public static ArrayList<Question> retirer(int id) throws SQLException {
        ArrayList<Question> questions= new ArrayList<>();
        String sql = "select * from question where quiz_id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,String.valueOf(id));

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Question p = new Question();
            p.setId_quest(rs.getInt("id_quest"));
            p.setQuest(rs.getString("quest"));
            p.setRep1(rs.getString("rep1"));
            p.setRep2(rs.getString("rep2"));
            p.setRep3(rs.getString("rep3"));
            p.setRep4(rs.getString("rep4"));
            p.setRepc(rs.getString("repc"));

            questions.add(p);
        }
        return questions;}
    public void delete(int id) throws SQLException {
        String sql= "delete from question where quiz_id = ?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

    }
}
