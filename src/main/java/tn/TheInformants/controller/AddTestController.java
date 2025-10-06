package tn.TheInformants.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import tn.TheInformants.Enums.Categorie;
import tn.TheInformants.Enums.Status;
import tn.TheInformants.entities.Questiont;
import tn.TheInformants.entities.Reponse;
import tn.TheInformants.entities.Test;
import tn.TheInformants.services.ServicesQuestion;
import tn.TheInformants.services.ServicesReponses;
import tn.TheInformants.services.ServicesTest;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddTestController implements Initializable {
    @FXML
    private Button Add_Questions;

    @FXML
    private Button Terminate_btn;
    @FXML
    private AnchorPane main_anchor;
    private Categorie categorie;
    private int optionCount = 0;
    private Boolean val=false;

    @FXML
    private VBox option_vbox;

    @FXML
    private Button ajouter_btn, Validate_btn;

    @FXML
    private ComboBox<Categorie> test_categorie;

    @FXML
    private TextArea test_description;

    @FXML
    private TextField test_name;
    @FXML
    private TextField test_time;

private Test test;
    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Add_Questions.setDisable(false);
        Terminate_btn.setDisable(false);
        String testName = test_name.getText();
        String testTime = test_time.getText();
        String testDescription = test_description.getText();

        test_categorie.setItems(FXCollections.observableArrayList(categorie.values()));


    }

    @FXML
    void ajouter_btn_OnAction(ActionEvent event) {
        ajouter_btn.setStyle("-fx-background-color: #1478FF;  -fx-effect: dropshadow(three-pass-box,rgba(20,120,255,0.5),30,0,0,0);");
    }

    @FXML
    void validateAction(ActionEvent event) {
        String testName = test_name.getText();
        String testTime = test_time.getText();
        String testDescription = test_description.getText();
        Categorie testCategorie = test_categorie.getValue();

        String testTimeRegex = "^((\\d+\\s+hour(s?)\\s*,\\s*)?(\\d+\\s+minute(s?)\\s*)?)$";


        if (!testName.isEmpty() && !testTime.isEmpty() && !testDescription.isEmpty() && !(testCategorie == null)) {
            if (testTime.matches(testTimeRegex)) {
                ServicesTest st = new ServicesTest();
                try {
                    if (!st.testNameExists(testName)) {
                        Add_Questions.setDisable(false);
                        Terminate_btn.setDisable(false);
                        Validate_btn.setStyle("");
                        Status statut = Status.INACTIF;
                        test = new Test(testName, testDescription, testTime, statut, testCategorie);
                        Add_Questions_btn_OnAction(event);
                        System.out.println("test not added");
                        st.ajouter(test);
                        System.out.println("test added");
                    } else {
                        showAlert("Error", "Existing Test Name", "The test name already exists. Please choose another name.");
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            } else {
                showAlert("Error", "Invalid Time Format",
                        "The time format should be in the form 'X hour(s), Y minute(s)', where X and Y are positive integers.");
            }
        } else {
            showAlert("Error", "Incomplete Fields",
                    "Please fill in all fields!");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/gui/Rresources/alertStyle.css").toExternalForm());
        alert.showAndWait();
    }


    @FXML
    void addQuestion(ActionEvent event) {
        VBox questionBox = new VBox();
        HBox inputContainer = new HBox();

        TextField questionTextField = new TextField();
        questionTextField.setPromptText("Untitled Question");

        Button addOptionButton = new Button("+");
        addOptionButton.setStyle("-fx-background-color: white; " +
                "-fx-font-size: 15px; " + "-fx-border-color: white; " +
                "-fx-text-fill: #9b9aac;");

        addOptionButton.setOnAction(e -> {
            String text = questionTextField.getText();
            if (text == null || text.trim().isEmpty()) {
                showAlert("Validation Error", "The question cannot be empty.");
            }
            else if (text.trim().length() < 8) {
                showAlert("Validation Error", "The question must be at least 8 characters long.");
            }
            else {
                addOptionToQuestion(questionBox);
            }
        });

        inputContainer.getChildren().addAll(questionTextField, addOptionButton);
        HBox.setHgrow(questionTextField, Priority.ALWAYS);
        questionBox.getChildren().add(inputContainer);

        questionTextField.setPrefHeight(48);
        questionTextField.setStyle("-fx-background-color: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-family: sans-serif; " +
                "-fx-border-color: #9b9aac; " +
                "-fx-border-width: 0px 0px 1px 0px; " +
                "-fx-border-radius: 0px 0px 5px 5px;");

        questionTextField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                questionTextField.setStyle("-fx-background-color: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-family: sans-serif; " +
                        "-fx-border-color: #1478FF; " +
                        "-fx-border-width: 0px 0px 1px 0px; " +
                        "-fx-border-radius: 0px 0px 5px 5px;");
            } else {
                questionTextField.setStyle("-fx-background-color: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-family: sans-serif; " +
                        "-fx-border-color: #9b9aac; " +
                        "-fx-border-width: 0px 0px 1px 0px; " +
                        "-fx-border-radius: 0px 0px 5px 5px;");
            }
        });

        option_vbox.setMargin(questionBox, new Insets(0, 0, 10, 0));
        option_vbox.getChildren().add(questionBox);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }



    void addOptionToQuestion(VBox questionBox) {
        HBox optionBox = new HBox();
        optionBox.setSpacing(10);
        optionBox.setAlignment(Pos.CENTER_LEFT);

        CheckBox optionCheckbox = new CheckBox("Response " + optionCount++);
        optionCheckbox.setAlignment(Pos.CENTER_LEFT);

        TextField optionTextField = new TextField();
        optionTextField.setPromptText("Enter Response");
        optionTextField.setPrefWidth(632);
        optionTextField.setPrefHeight(48);
        optionTextField.setAlignment(Pos.CENTER_LEFT);

        optionTextField.setStyle("-fx-background-color: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-family: sans-serif; " +
                "-fx-border-color: #9b9aac; " +
                "-fx-border-width: 0px 0px 1px 0px; " +
                "-fx-border-radius: 0px 0px 5px 5px;");

        optionTextField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                optionTextField.setStyle("-fx-background-color: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-family: sans-serif; " +
                        "-fx-border-color: #1478FF; " +
                        "-fx-border-width: 0px 0px 1px 0px; " +
                        "-fx-border-radius: 0px 0px 5px 5px;");
            } else {
                optionTextField.setStyle("-fx-background-color: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-family: sans-serif; " +
                        "-fx-border-color: #9b9aac; " +
                        "-fx-border-width: 0px 0px 1px 0px; " +
                        "-fx-border-radius: 0px 0px 5px 5px;");
            }
        });




        Button validateButton = new Button("Confirm");
        validateButton.setStyle("-fx-background-color: white; " +
                "-fx-font-size: 15px; " + "-fx-border-color: white; " +
                "-fx-text-fill: #9b9aac;");
        validateButton.setOnAction(e ->
        { String text = optionTextField.getText();
        if (text == null || text.trim().isEmpty()) {
            showAlert("Validation Error", "The response cannot be empty.");
        }  else {
            // The text is valid, you can proceed
            showAlert("Success", "The response is valid.");
        }});

        optionBox.getChildren().addAll(optionCheckbox, optionTextField, validateButton);

        questionBox.getChildren().add(optionBox);
    }

    private void configureTextFieldAppearance(TextField textField) {
        textField.setStyle("-fx-background-color: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-family: sans-serif; " +
                "-fx-border-color: #9b9aac; " +
                "-fx-border-width: 0px 0px 1px 0px; " +
                "-fx-border-radius: 0px 0px 5px 5px;");

        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                textField.setStyle("-fx-background-color: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-family: sans-serif; " +
                        "-fx-border-color: #1478FF; " +
                        "-fx-border-width: 0px 0px 1px 0px; " +
                        "-fx-border-radius: 0px 0px 5px 5px;");
            } else {
                textField.setStyle("-fx-background-color: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-family: sans-serif; " +
                        "-fx-border-color: #9b9aac; " +
                        "-fx-border-width: 0px 0px 1px 0px; " +
                        "-fx-border-radius: 0px 0px 5px 5px;");
            }
        });
    }

    private void validateOptionTextField(TextField textField) {
        String text = textField.getText();
        if (text == null || text.trim().isEmpty()) {
            showAlert("Validation Error", "The response cannot be empty.");
        }  else {
            // The text is valid, you can proceed
            showAlert("Success", "The response is valid.");
        }
    }

    @FXML
    void Add_Questions_btn_OnAction(ActionEvent event)
    {
       try{ addQuestion(event);

    }catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
    }
    @FXML
    void Terminatebtn_OnAction(ActionEvent event) throws IOException {
        List<Questiont> questions = getAllQuestionsFromUI();
        ServicesTest servicesTest = new ServicesTest();
        ServicesQuestion servicesQuestion = new ServicesQuestion();
        ServicesReponses servicesReponses = new ServicesReponses();

        for (Questiont question : questions) {
            try {
                System.out.println("a");
                servicesQuestion.ajouter(question);
                System.out.println("b");

                int testId = servicesTest.recupererIdTestParNom(test.getNom_Test());
                System.out.println("c");

                int questionId = servicesQuestion.recupererIdQuestionParText(question.getText());
                System.out.println("d");

                servicesTest.ajouterQuestion(testId, questionId);
                System.out.println("e");

                for (Reponse reponse : question.getReponses()) {
                    try {
                        System.out.println("f");
                        servicesReponses.ajouter(reponse);
                        System.out.println("g");
                        int responseId = servicesReponses.recupererIdReponseParReponse(reponse.getReponse());
                        System.out.println("h");
                        servicesQuestion.ajouterReponse(questionId, responseId);
                        System.out.println("i");
                    } catch (SQLException ex) {
                        System.err.println("Erreur lors de l'ajout d'une réponse à la base de données:");
                        System.err.println("Question: " + question.getText());
                        System.err.println("Réponse: " + reponse.getReponse());
                        System.err.println("Exception: " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Erreur lors de l'ajout d'une question à la base de données:");
                System.err.println("Question: " + question.getText());
                System.err.println("Exception: " + ex.getMessage());
            }
        }
        Terminate_btn.setDisable(true);

        Add_Questions.setDisable(true);

    }

    private List<Questiont> getAllQuestionsFromUI() {
        List<Questiont> questions = new ArrayList<>();

        for (Node node : option_vbox.getChildren()) {
            if (node instanceof VBox) {
                VBox questionBox = (VBox) node;
                Questiont question = new Questiont();

                HBox questionTitleBox = (HBox) questionBox.getChildren().get(0);
                TextField questionTextField = (TextField) questionTitleBox.getChildren().get(0);
                String questionText = questionTextField.getText().trim();

                if (!questionText.isEmpty()) {
                    question.setText(questionText);

                    System.out.println(questionBox.getChildren().size());
                    for (int i = 1; i < questionBox.getChildren().size(); i++) {
                        HBox optionContainer = (HBox) questionBox.getChildren().get(i);
                        CheckBox checkBox = (CheckBox) optionContainer.getChildren().get(0);
                        TextField optionField = (TextField) optionContainer.getChildren().get(1);
                        Reponse reponse=new Reponse();
                        reponse.setReponse(optionField.getText());
                        reponse.setCorrect(checkBox.isSelected());
                        System.out.println(optionField);
                        if (!optionField.getText().isEmpty()) {
                            question.ajouterReponse(reponse);
                        }
                    }
                    questions.add(question);
                }
            }
        }

        return questions;
    }





















}
