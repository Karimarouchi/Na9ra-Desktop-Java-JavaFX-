package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tn.TheInformants.services.ServicesTest;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StatisticController implements Initializable {
    private ServicesTest testUtilisateurService;

    @FXML
    private Label actif_test_bumber;

    @FXML
    private Label average_duration;

    @FXML
    private Label avg_question_number;

    @FXML
    private BarChart<String, Number> chart;

    @FXML
    private Label highest_score;

    @FXML
    private Label innactif_test_number;

    @FXML
    private Label lowest_score;

    @FXML
    private AnchorPane main_anchor;

    @FXML
    private Label total_test_number;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        testUtilisateurService = new ServicesTest();
        try {   populateChart();
          int totalnumber=testUtilisateurService.getTotalNumberOfTests();
          int actiftests=testUtilisateurService.getNumberOfActiveTests();
           int inactiftests=testUtilisateurService.getNumberOfInactiveTests();
            double hiestscore=testUtilisateurService.calculateHighestScore();
            double lowestscore=testUtilisateurService.calculateLowestScore();
            double avgquestionnumber=testUtilisateurService.calculateAverageNumberOfQuestionsPerTest();
            double avgqduration=testUtilisateurService.calculateAverageTestDurationInMinutes();

            System.out.println("inactiftests"+inactiftests);
            System.out.println("actiftests"+actiftests);
            System.out.println("hiestscore"+hiestscore);
            System.out.println("lowestscore"+lowestscore);
            total_test_number.setText(String.valueOf(totalnumber));
          actif_test_bumber.setText(String.valueOf(actiftests));
       innactif_test_number.setText(String.valueOf(inactiftests));
            highest_score.setText(String.valueOf(hiestscore));
            lowest_score.setText(String.valueOf(lowestscore));
            avg_question_number.setText(String.valueOf(avgquestionnumber));
            average_duration.setText(String.valueOf(avgqduration));




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateChart() throws SQLException {
        ResultSet resultSet = testUtilisateurService.fetchHighestScores();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Highest Scores");

        try {
            while (resultSet.next()) {
                String testName = resultSet.getString("nom_Test");
                String highestScoreStr = resultSet.getString("highest_score").replace(",", ".").replace("%", ""); // Remove '%' and replace ',' with '.'
                double highestScore = Double.parseDouble(highestScoreStr);

                series.getData().add(new XYChart.Data<>(testName, highestScore));
            }
            chart.getData().add(series);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet == null) {
                throw new SQLException("ResultSet is null. Check database connection and query.");
            }
        }
    }

}
