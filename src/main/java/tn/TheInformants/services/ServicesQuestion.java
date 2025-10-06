package tn.TheInformants.services;

import tn.TheInformants.Utils.MyDataBase;
import tn.TheInformants.entities.Questiont;
import tn.TheInformants.entities.Reponse;
import tn.TheInformants.iservices.IService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicesQuestion implements IService<Questiont> {
    private Connection connection;

    public ServicesQuestion() {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Questiont question) throws SQLException {
        //Date date=new Date(2000/2/1);
        String sql = "INSERT INTO QUESTIONT (text) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, question.getText());
      //  preparedStatement.setInt(2, question.getTemp_imparti());
        preparedStatement.executeUpdate();
    }

    @Override
    public void modifier(Questiont question) throws SQLException {
        String sql = "UPDATE QUESTIONT SET  text=? WHERE id_Questiont=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, question.getText());
        preparedStatement.setInt(2, question.getId_Question());
        preparedStatement.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public List<Questiont> afficher() throws SQLException {
        return null;
    }


    public void supprimer(Questiont question) throws SQLException {
        String sql = "DELETE FROM QUESTIONT WHERE id_Questiont=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, question.getId_Question());
        preparedStatement.executeUpdate();
    }


    public List<Questiont> recuperer() throws SQLException {
        List<Questiont> list = new ArrayList<>();
        String sql = "SELECT * FROM QUESTIONT";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Questiont q = new Questiont();
            q.setId_Question(rs.getInt("id_Question"));
            q.setText(rs.getString("text"));
            list.add(q);
        }
        return list;
    }
    public int recupererIdQuestionParText(String text) throws SQLException {
        int idQuestiont = -1;


        String sql = "SELECT id_Questiont FROM questiont WHERE text = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, text);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            idQuestiont = resultSet.getInt("id_Questiont");
        }

        return idQuestiont;
    }



    public void ajouterReponse(int id_questiont, int id_reponse) throws SQLException {
        String sql = "INSERT INTO question_reponse (id_Questiont, id_Reponse) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id_questiont);
        preparedStatement.setInt(2, id_reponse);
        preparedStatement.executeUpdate();
    }

    public void supprimerReponse(int id_questiont, int id_reponse) throws SQLException {

        String sql = "DELETE FROM question_reponse WHERE id_Questiont=? AND id_Reponse=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id_questiont);
        preparedStatement.setInt(2,id_reponse);
        preparedStatement.executeUpdate();
    }
    public List<Reponse> getResponsesForQuestion(int questionId, Questiont questions) throws SQLException {
        String query = "SELECT r.* " +
                "FROM question_reponse qr " +
                "JOIN reponse r ON qr.id_Reponse = r.id_Reponse " +
                "WHERE qr.id_Questiont = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int responseId = resultSet.getInt("id_Reponse");
                String response = resultSet.getString("reponse");
                boolean isCorrect = resultSet.getBoolean("is_correct");
                Reponse reponse = new Reponse(responseId, response, isCorrect);
                System.out.println("======="+reponse);
                questions.ajouterReponse(reponse);
            }
        }

        return questions.getReponses();
    }


        public Questiont recupererQuestionParId(int idQ) throws SQLException {
            Questiont question = new Questiont();

            String sql = "SELECT * FROM questiont WHERE id_Questiont = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idQ);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id_Questiont");
                        String questionText = resultSet.getString("text");
                        question = new Questiont(id, questionText);
                    }
                }
            }

            return question;
        }

}


