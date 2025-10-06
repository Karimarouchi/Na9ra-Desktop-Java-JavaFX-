package tn.TheInformants.services;

import tn.TheInformants.Enums.Categorie;
import tn.TheInformants.Enums.Status;
import tn.TheInformants.Utils.MyDataBase;
import tn.TheInformants.entities.Questiont;
import tn.TheInformants.entities.Test;
import tn.TheInformants.iservices.IService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServicesTest implements IService<Test> {
    private Connection connection;
    public ServicesTest() {
        connection = MyDataBase.getInstance().getConnection();
    }
    @Override


    public void ajouter(Test test) throws SQLException {
        String sql = "INSERT INTO TEST (nom_Test, description, temp_pris, status, categorie) VALUES ('" + test.getNom_Test() + "','" + test.getDescription() + "','" + test.getTemp_pris() + "','" + test.getStatus() + "','" + test.getCategorie() + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void modifier(Test test) throws SQLException {
        Date d=new Date(2022/2/2);
        String sql = "UPDATE TEST SET nom_Test=?, description=?, temp_pris=?, categorie=? WHERE id_Test=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, test.getNom_Test());
        preparedStatement.setString(2, test.getDescription());
       // preparedStatement.setDate(3, new java.sql.Date(test.getTemp_pris().getTime()));
        preparedStatement.setDate(3, d);

        preparedStatement.setString(4, test.getCategorie().toString());
        preparedStatement.setInt(5, test.getId_Test());
        preparedStatement.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public List<Test> afficher() throws SQLException {
        return null;
    }


    public void supprimer(Test test) throws SQLException {
        System.out.println("test in the delete "+test);
        String sql = "DELETE FROM TEST WHERE id_Test=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, test.getId_Test());
        preparedStatement.executeUpdate();
    }

    public void deleteTestById(int testId) throws SQLException{
        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("DELETE FROM test_utilisateur WHERE id_Test = " + testId);

            statement.executeUpdate("DELETE FROM test_question WHERE id_Test = " + testId);

            statement.executeUpdate("DELETE FROM test WHERE id_Test = " + testId);

            System.out.println("Test with ID " + testId + " and all related data have been successfully deleted.");
        } catch (SQLException e) {
            System.out.println("Error deleting test with ID " + testId + ": " + e.getMessage());
        }
    }


    public int getNumberOfActiveTests() throws SQLException {
        int activeTestsCount = 0;
        String query = "SELECT COUNT(*) AS count FROM test WHERE status = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "ACTIF"); // Set the status parameter to "ACTIF"
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    activeTestsCount = resultSet.getInt("count");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            throw e; // Rethrow the exception to ensure that it can be handled or logged by the caller
        }
        return activeTestsCount;
    }

    public int getNumberOfInactiveTests() throws SQLException {
        int inactiveTestsCount = 0;
        // Since the query does not actually need dynamic values, a PreparedStatement here is not required for safety,
        // but using it for consistency in handling queries
        String query = "SELECT COUNT(*) AS count FROM test WHERE status = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "INACTIF"); // Set the status parameter to "INACTIF"
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    inactiveTestsCount = resultSet.getInt("count");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            throw e; // Rethrow the exception to ensure that it can be handled or logged by the caller
        }
        return inactiveTestsCount;
    }

    private double parseScore(String scoreStr) {
        try {
            return Double.parseDouble(scoreStr.split("%")[0]);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing score: " + scoreStr);
            return 0.0;
        }
    }
    public double calculateHighestScore() throws SQLException {
        double highestScore = 0.0; // Default to 0
        String query = "SELECT score FROM test_utilisateur";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String scoreStr = resultSet.getString("score");
                double score = parseScore(scoreStr);
                if (score > highestScore) {
                    highestScore = score;
                }
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            throw e;
        }
        return highestScore;
    }

    // Method to calculate the lowest score
    public double calculateLowestScore() throws SQLException {
        double lowestScore = Double.MAX_VALUE; // Initialize to the maximum possible value
        String query = "SELECT score FROM test_utilisateur";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String scoreStr = resultSet.getString("score");
                double score = parseScore(scoreStr);
                if (score < lowestScore) {
                    lowestScore = score;
                }
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            throw e;
        }
        return lowestScore == Double.MAX_VALUE ? 0.0 : lowestScore;
    }



    public List<Test> recuperer() throws SQLException {
        List<Test> list = new ArrayList<>();
        String sql = "SELECT * FROM TEST";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Test t = new Test();
            t.setId_Test(rs.getInt("id_Test"));
            t.setNom_Test(rs.getString("nom_Test"));
            t.setDescription(rs.getString("description"));
            t.setTemp_pris(rs.getString("temp_pris"));
            t.setStatus(Status.valueOf(rs.getString("status")));
            t.setCategorie(Categorie.valueOf(rs.getString("categorie")));
            list.add(t);
        }
        return list;
    }

    public void ajouterScore(Test test, int idUtilisateur, String score) throws SQLException {
        test.ajouterScore(idUtilisateur,score);
        String sql = "INSERT INTO test_utilisateur (id_Test, user_id, score) VALUES ";
        StringBuilder values = new StringBuilder();
        System.out.println(test);
        for (Map.Entry<Integer, String> entry : test.getResultats_Utilisateurs().entrySet()) {
            values.append("(").append(test.getId_Test()).append(", ").append(entry.getKey()).append(", '").append(entry.getValue()).append("'),");
        }

        if (values.length() > 0) {
            values.deleteCharAt(values.length() - 1);
            sql += values.toString();

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } else {
            System.out.println("La chaîne 'values' est vide. Aucune opération d'insertion ne sera effectuée.");
        }
    }
    public void modifierScore(Test test, int idUtilisateur, String nouveauScore) throws SQLException {
        test.modifierScore(idUtilisateur, nouveauScore);

        String sql = "UPDATE test_utilisateur SET score=? WHERE id_Test=? AND user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nouveauScore);
        preparedStatement.setInt(2, test.getId_Test());
        preparedStatement.setInt(3, idUtilisateur);
        preparedStatement.executeUpdate();
    }

    public void supprimerScore(Test test, int idUtilisateur) throws SQLException {
        test.supprimerScore(idUtilisateur);

        String sql = "DELETE FROM test_utilisateur WHERE id_Test=? AND user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, test.getId_Test());
        preparedStatement.setInt(2, idUtilisateur);
        preparedStatement.executeUpdate();
    }

    public void  ajouterQuestion(int id_test, int id_question) throws SQLException {


        String sql = "INSERT INTO test_question (id_Test,id_Questiont) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id_test);
        preparedStatement.setInt(2,id_question);
        preparedStatement.executeUpdate();


    }
    public void supprimerQuestion(int id_test, int id_questiont)  throws SQLException {

        String sql = "DELETE FROM test_question WHERE id_Test=? AND id_Questiont=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id_test);
        preparedStatement.setInt(2,id_questiont);
        preparedStatement.executeUpdate();
    }

    public int recupererIdTestParNom(String name) throws SQLException {
        int idTest = -1;

        String sql = "SELECT id_Test FROM test WHERE nom_Test = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            idTest = resultSet.getInt("id_Test");
        }

        return idTest;
    }

    public List<Questiont> getQuestionsForTest(int testId,Test test) throws SQLException {
        String query = "SELECT q.* " +
                "FROM test_question tq " +
                "JOIN questiont q ON tq.id_Questiont = q.id_Questiont " +
                "WHERE tq.id_Test = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, testId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int q = resultSet.getInt("id_Questiont");

                String text = resultSet.getString("text");

                Questiont questiont = new Questiont(q,text);
                test.ajouterQuestion(questiont);
            }
        }
        System.out.println("test.getQuestions()"+test.getQuestions());
        return test.getQuestions();
    }

    public boolean testNameExists(String testName) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM test WHERE nom_Test = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, testName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }
    public boolean verifierScoreExiste(int idTest, int idUtilisateur) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM test_utilisateur WHERE id_Test = ? AND user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idTest);
            preparedStatement.setInt(2, idUtilisateur);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("count") > 0;
                }
            }
        }
        return false;
    }

    public boolean checkIfDisplayed(int idUtilisateur, int idTest) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM test_utilisateur WHERE id_Test = ? AND user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idTest);
            preparedStatement.setInt(2, idUtilisateur);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("resultSet" + resultSet);
                if (resultSet.next()) {
                    System.out.println("resultSet.getInt(\"count\")" + resultSet.getInt("count"));
                    return resultSet.getInt("count") > 0;
                }
            }
        }

        return false;
    }
    public ResultSet fetchHighestScores() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT nom_Test, MAX(score) AS highest_score FROM test_utilisateur JOIN test ON test_utilisateur.id_Test = test.id_Test GROUP BY nom_Test";
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getTotalNumberOfTests() throws SQLException {
        int totalTests = 0;
        String query = "SELECT COUNT(*) AS total FROM test";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                totalTests = resultSet.getInt("total");
            }
        }

        return totalTests;
    }

    public double calculateAverageNumberOfQuestionsPerTest() throws SQLException {
        double averageNumberOfQuestions = 0.0;

        // Query to get the total number of questions
        String totalQuestionsQuery = "SELECT COUNT(*) AS totalQuestions FROM question";

        // Query to get the total number of tests
        String totalTestsQuery = "SELECT COUNT(*) AS totalTests FROM test";

        try (PreparedStatement totalQuestionsStatement = connection.prepareStatement(totalQuestionsQuery);
             ResultSet totalQuestionsResultSet = totalQuestionsStatement.executeQuery();
             PreparedStatement totalTestsStatement = connection.prepareStatement(totalTestsQuery);
             ResultSet totalTestsResultSet = totalTestsStatement.executeQuery()) {

            int totalQuestions = 0;
            int totalTests = 0;

            // Get the total number of questions
            if (totalQuestionsResultSet.next()) {
                totalQuestions = totalQuestionsResultSet.getInt("totalQuestions");
            }

            // Get the total number of tests
            if (totalTestsResultSet.next()) {
                totalTests = totalTestsResultSet.getInt("totalTests");
            }

            // Calculate the average number of questions per test
            if (totalTests > 0) {
                averageNumberOfQuestions = (double) totalQuestions / totalTests;
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            throw e;
        }

        return averageNumberOfQuestions;
    }
    public double calculateAverageTestDurationInMinutes() throws SQLException {
        double totalDurationInMinutes = 0.0;
        int count = 0;
        String query = "SELECT temp_pris FROM test";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            Pattern hourPattern = Pattern.compile("(\\d+) hour");
            Pattern minutePattern = Pattern.compile("(\\d+) minute");

            while (resultSet.next()) {
                String durationStr = resultSet.getString("temp_pris");
                Matcher hourMatcher = hourPattern.matcher(durationStr);
                Matcher minuteMatcher = minutePattern.matcher(durationStr);
                int hours = 0;
                int minutes = 0;

                if (hourMatcher.find()) {
                    hours = Integer.parseInt(hourMatcher.group(1));
                }
                if (minuteMatcher.find()) {
                    minutes = Integer.parseInt(minuteMatcher.group(1));
                }

                totalDurationInMinutes += hours * 60 + minutes;
                count++;
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            throw e;
        }

        return count > 0 ? totalDurationInMinutes / count : 0;
    }

}
