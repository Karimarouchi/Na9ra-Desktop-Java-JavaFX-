package tn.TheInformants.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tn.TheInformants.entities.CertificateGenerator;
import tn.TheInformants.entities.Questiont;
import tn.TheInformants.entities.Reponse;
import tn.TheInformants.entities.Test;
import tn.TheInformants.services.ServicesQuestion;
import tn.TheInformants.services.ServicesTest;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.*;

public class AffichageTestController implements Initializable {
    @FXML
    private Label test_Name2;
    @FXML
    private AnchorPane result_anchor;

    @FXML
    private Button returnButton;

    @FXML
    private Label timerLabel;
    @FXML
    private Button generateButton;
    @FXML
    private AnchorPane main_anchor;
    @FXML
    private VBox option_vbox;
    @FXML
    private Button submit_btn;
    @FXML
    private Label test_Name;
    @FXML
    private Label scoreLabel;

    private Test mainTest = new Test();
    private Map<Integer, ToggleGroup> questionGroups = new HashMap<>();
    private Timeline timeline;
    private Integer tempsRestant;
    public void startTimer() {
        if (mainTest.getTemp_pris() != null) {
            System.out.println("mainTest.getTemp_pris()"+mainTest.getTemp_pris());
            tempsRestant = convertTimeToMinutes(mainTest.getTemp_pris())*60;
            System.out.println("tempsRestant"+tempsRestant);
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1), event -> {
                        tempsRestant--;
                        updateTimerLabel();
                        if (tempsRestant <= 0) {
                            timeline.stop();
                            submitTest();
                        }
                    })
            );
            timeline.playFromStart();
        }
    }
    private int convertTimeToMinutes(String timeString) {
        int totalMinutes = 0;
        String[] parts = timeString.split("\\s+");
        System.out.println("parts"+ Arrays.toString(parts));
        for (int i = 0; i < parts.length; i += 2) {
            int value = Integer.parseInt(parts[i]);
            String unit = parts[i + 1].toLowerCase();
            if (unit.startsWith("hour")) {
                totalMinutes += value * 60;
            } else if (unit.startsWith("minute")) {
                totalMinutes += value;
            }}
        return totalMinutes;
    }
    private void updateTimerLabel() {
        int hours = tempsRestant / 3600;
        int minutes = (tempsRestant % 3600) / 60;
        int seconds = tempsRestant % 60;
        timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        if (tempsRestant <= 0) {
            timeline.stop();
            submitTest();
            disableSubmitButton();
        }
    }

    private void submitTest() {
        result_anchor.setVisible(true);

        if (timeline != null) {
            timeline.stop();
        }

        timerLabel.setText("Time expired");

        int score = calculateScore();
        option_vbox.getChildren().clear();
        submit_btn.setVisible(false);
        timerLabel.setVisible(false);

        int currentUserId = 11;
        double percentage = ((double) score / questionGroups.size()) * 100;
        String percentageScore = String.format("%.2f%%", percentage);
        ServicesTest servicesTest = new ServicesTest();

        try {
            boolean scoreExiste = servicesTest.verifierScoreExiste(mainTest.getId_Test(), LoginController.user1.getUser_id());

            if (!scoreExiste) {
                servicesTest.ajouterScore(mainTest, LoginController.user1.getUser_id(), percentageScore);
            } else {
                servicesTest.modifierScore(mainTest, LoginController.user1.getUser_id(), percentageScore);
            }

            option_vbox.getChildren().clear();
            submit_btn.setVisible(false);
            timerLabel.setVisible(false);
            test_Name.setVisible(false);


            scoreLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

            scoreLabel.setText("Your score is: " + percentageScore);
            test_Name2.setText(mainTest.getNom_Test());
            test_Name2.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;  -fx-text-fill: #141484");

            returnButton.setOnAction(e -> {
                result_anchor.setVisible(false);

                option_vbox.getChildren().clear();
                submit_btn.setDisable(true);
                System.out.println("mainTest" + mainTest);
                setTestReponse(mainTest);
            });

            generateButton.setOnAction(e -> {
                String htmlTemplatePath = "/gui/Rresources/certificate_template.html";

                InputStream inputStream = getClass().getResourceAsStream(htmlTemplatePath);
                if (inputStream != null) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line);
                            stringBuilder.append('\n');
                        }
                        String htmlTemplate = stringBuilder.toString();

                        String recipientName = "John Doe";
                        String testName = mainTest.getNom_Test();

                        System.out.println(" mainTest.getNom_Test();" + mainTest.getNom_Test());
                        String filledTemplate = htmlTemplate
                                .replace("{{RecipientName}}", recipientName)
                                .replace("{{TestName}}", testName)
                                .replace("{{Score}}", percentageScore);

                        InputStream pdfStream = CertificateGenerator.generateCertificate(filledTemplate);
                        if (pdfStream != null) {
                            // Save the PDF stream to a file
                            File outputFile = new File("generated_certificate.pdf");
                            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = pdfStream.read(buffer)) != -1) {
                                    fos.write(buffer, 0, bytesRead);
                                }
                                System.out.println("PDF saved successfully.");
                                // Optionally, open the PDF file automatically after saving
                                Desktop.getDesktop().open(outputFile);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } finally {
                                pdfStream.close();
                            }
                        } else {
                            System.err.println("Failed to generate or save the PDF file.");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException("Error reading HTML template file", ex);
                    }
                } else {
                    System.err.println("Could not find HTML template file: " + htmlTemplatePath);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private int calculateScore() {
        int score = 0;
        for (Map.Entry<Integer, ToggleGroup> entry : questionGroups.entrySet()) {
            RadioButton selected = (RadioButton) entry.getValue().getSelectedToggle();
            if (selected != null) {
                Reponse response = (Reponse) selected.getUserData();
                if (response.getCorrect()) {
                    score++;
                }
            }
        }
        return score;
    }


    @FXML
    void submit_btn_OnAction(ActionEvent event) throws SQLException {
        result_anchor.setVisible(true);

        int score = calculateScore();
        int totalQuestions = questionGroups.size();
        int currentUserId = 11;
        double percentage = ((double) score / totalQuestions) * 100;
        String percentageScore = String.format("%.2f%%", percentage);
        ServicesTest servicesTest = new ServicesTest();
        System.out.println("LoginController.user1.getUser_id()"+LoginController.user1.getUser_id());
        try {
            boolean scoreExiste = servicesTest.verifierScoreExiste(mainTest.getId_Test(), LoginController.user1.getUser_id());
            if (!scoreExiste) {
                servicesTest.ajouterScore(mainTest, LoginController.user1.getUser_id(), percentageScore);
            } else {
                servicesTest.modifierScore(mainTest, LoginController.user1.getUser_id(), percentageScore);
            }

            option_vbox.getChildren().clear();
            submit_btn.setVisible(false);
            timerLabel.setVisible(false);
            test_Name.setVisible(false);


            scoreLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

            scoreLabel.setText("Your score is: " + percentageScore);
            test_Name2.setText(mainTest.getNom_Test());
            test_Name2.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;  -fx-text-fill: #141484");

            returnButton.setOnAction(e -> {
                result_anchor.setVisible(false);

                option_vbox.getChildren().clear();
                submit_btn.setDisable(true);
                System.out.println("mainTest" + mainTest);
                setTestReponse(mainTest);
            });

            generateButton.setOnAction(e -> {
                String htmlTemplatePath = "/gui/Rresources/certificate_template.html";

                InputStream inputStream = getClass().getResourceAsStream(htmlTemplatePath);
                if (inputStream != null) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line);
                            stringBuilder.append('\n');
                        }
                        String htmlTemplate = stringBuilder.toString();

                        String recipientName = "John Doe";
                        String testName = mainTest.getNom_Test();

                        System.out.println(" mainTest.getNom_Test();" + mainTest.getNom_Test());
                        String filledTemplate = htmlTemplate
                                .replace("{{RecipientName}}", recipientName)
                                .replace("{{TestName}}", testName)
                                .replace("{{Score}}", percentageScore);

                        InputStream pdfStream = CertificateGenerator.generateCertificate(filledTemplate);
                        if (pdfStream != null) {
                            // Save the PDF stream to a file
                            File outputFile = new File("generated_certificate.pdf");
                            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = pdfStream.read(buffer)) != -1) {
                                    fos.write(buffer, 0, bytesRead);
                                }
                                System.out.println("PDF saved successfully.");
                                // Optionally, open the PDF file automatically after saving
                                Desktop.getDesktop().open(outputFile);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } finally {
                                pdfStream.close();
                            }
                        } else {
                            System.err.println("Failed to generate or save the PDF file.");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException("Error reading HTML template file", ex);
                    }
                } else {
                    System.err.println("Could not find HTML template file: " + htmlTemplatePath);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setTest(Test test) {
        result_anchor.setVisible(false);
        this.mainTest = test;
        test_Name.setText(test.getNom_Test());
        test_Name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;  -fx-text-fill: #141484");

        option_vbox.getChildren().clear();

        try {
            ServicesTest testService = new ServicesTest();
            ServicesQuestion questionService = new ServicesQuestion();

            List<Questiont> questions = testService.getQuestionsForTest(test.getId_Test(), test);
            for (Questiont question : questions) {
                VBox questionBox = new VBox(10);
                Label questionLabel = new Label(question.getText());
                questionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                questionBox.getChildren().add(questionLabel);

                ToggleGroup group = new ToggleGroup();
                questionGroups.put(question.getId_Question(), group);

                List<Reponse> responses = questionService.getResponsesForQuestion(question.getId_Question(), question);
                for (Reponse response : responses) {
                    RadioButton option = new RadioButton(response.getReponse());
                    option.setUserData(response);
                    option.setToggleGroup(group);
                    option.setStyle("-fx-font-size: 16px;");
                    questionBox.getChildren().add(option);
                }

                option_vbox.getChildren().add(questionBox);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        startTimer();

    }
    public void setTestReponse(Test test) {
        System.out.println("hello dear");
        this.mainTest = test;
        test_Name.setText(mainTest.getNom_Test());
        test_Name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;  -fx-text-fill: #141484");

        // Clear the VBox and the questionGroups map
        option_vbox.getChildren().clear();
        questionGroups.clear(); // Add this line to clear existing question groups

        try {
            ServicesTest testService = new ServicesTest();
            ServicesQuestion questionService = new ServicesQuestion();
            System.out.println("mainTest"+mainTest);
            mainTest.getQuestions().clear();
            List<Questiont> questionstest = testService.getQuestionsForTest(mainTest.getId_Test(), mainTest);
            System.out.println("+++++questions+++++"+questionstest);
            for (Questiont question : questionstest) {
                VBox questionBox = new VBox(10);
                Label questionLabel = new Label(question.getText());
                questionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                questionBox.getChildren().add(questionLabel);

                ToggleGroup group = new ToggleGroup();
                questionGroups.put(question.getId_Question(), group); // This will now correctly replace the old group

                List<Reponse> responses = questionService.getResponsesForQuestion(question.getId_Question(), question);
                for (Reponse response : responses) {

                    addOptionToQuestionWithResponse(questionBox, response);


                }

                option_vbox.getChildren().add(questionBox);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void addOptionToQuestionWithResponse(VBox questionBox, Reponse response) {
        HBox optionBox = new HBox(10);
        optionBox.setAlignment(Pos.CENTER_LEFT);

        // RadioButton setup
        RadioButton optionRadioButton = new RadioButton();
        optionRadioButton.setToggleGroup(new ToggleGroup());
        optionRadioButton.setSelected(response.getCorrect());
        optionRadioButton.setDisable(true);
        optionRadioButton.setUserData(response.getId_Reponse());
        optionRadioButton.setStyle("-fx-font-size: 16px;");

        // Label setup
        Label optionLabel = new Label(response.getReponse());
        optionLabel.setPrefHeight(20);
        optionLabel.setUserData(response.getId_Reponse());
        optionLabel.setStyle("-fx-font-size: 16px;");


        // Add components to the option box
        optionBox.getChildren().addAll(optionRadioButton, optionLabel);

        // Add the option box to the question box
        questionBox.getChildren().add(optionBox);
    }



    private void disableSubmitButton() {
        submit_btn.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        result_anchor.setVisible(false);

    }
}
