package tn.TheInformants.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.TheInformants.Enums.Categorie;
import tn.TheInformants.entities.Questiont;
import tn.TheInformants.entities.Reponse;
import tn.TheInformants.entities.Test;
import tn.TheInformants.services.ServicesQuestion;
import tn.TheInformants.services.ServicesReponses;
import tn.TheInformants.services.ServicesTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ModifyFormController {
    @FXML
    private AnchorPane main_anchor;
    @FXML
    private VBox option_vbox;
    @FXML
    private ComboBox<Categorie> test_Categorie;

    @FXML
    private TextArea test_Description;

    @FXML
    private TextField test_Name;
    @FXML
    private Button cancel_btn;
    @FXML
    private Button update_btn;
    private Categorie categorie;

    private Test mainTest=new Test();
    private Questiont mainQuestion=new Questiont();
    @FXML
    private TextField test_time;
  public void initialize() {
      test_Categorie.setItems(FXCollections.observableArrayList(categorie.values()));

  }

        public void setTest(Test test) {
        test_Name.setPromptText(test.getNom_Test());
        test_time.setPromptText(test.getTemp_pris());

        mainTest.setNom_Test(test.getNom_Test());
        mainTest.setTemp_pris(test.getTemp_pris());

        test_Description.setPromptText(test.getDescription());
        mainTest.setDescription(test.getDescription());
        test_Categorie.setPromptText(test.getCategorie().toString());
        mainTest.setCategorie(test.getCategorie());
        mainTest.setId_Test(test.getId_Test());
        System.out.println("The ID of the test: " + test.getId_Test());

        option_vbox.getChildren().clear();
        try {
            ServicesTest testService = new ServicesTest();
            ServicesQuestion questionService = new ServicesQuestion();

            List<Questiont> questions = testService.getQuestionsForTest(test.getId_Test(), test);
            for (Questiont question : questions) {
                VBox questionBox = new VBox(10);
                TextField questionTextField = new TextField();
                questionTextField.setPromptText(question.getText());
                questionTextField.setPrefWidth(50);
                questionTextField.setPrefHeight(48);
                questionBox.getChildren().add(questionTextField);
                questionTextField.setEditable(true);
                questionTextField.getStyleClass().add("question");
                questionTextField.setUserData(question.getId_Question());


                questionTextField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                    if (isNowFocused) {
                        questionTextField.setStyle("-fx-background-color: white; " +
                                "-fx-font-size: 16px; " +
                                "-fx-font-family: sans-serif; " +
                                "-fx-border-color: #1478FF; " +
                                "-fx-border-width: 0px 0px 1px 0px; " +
                                "-fx-border-radius: 0px 0px 5px 5px;");
                                ;
                    } else {
                        questionTextField.setStyle("-fx-background-color: white; " +
                                "-fx-font-size: 16px; " +
                                "-fx-font-family: sans-serif; " +
                                "-fx-border-color: #9b9aac; " +
                                "-fx-border-width: 0px 0px 1px 0px; " +
                                "-fx-border-radius: 0px 0px 5px 5px;");
                    }
                });
                List<Reponse> responses = questionService.getResponsesForQuestion(question.getId_Question(), question);
                for (Reponse response : responses) {
                   addOptionToQuestionWithResponse(questionBox, response);
                }

                option_vbox.setMargin(questionBox, new Insets(0, 0, 10, 0));
                option_vbox.getChildren().add(questionBox);            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void addOptionToQuestionWithResponse(VBox questionBox, Reponse response) {
        HBox optionBox = new HBox(10);
        optionBox.setAlignment(Pos.CENTER_LEFT);

        CheckBox optionCheckbox = new CheckBox();
        optionCheckbox.setSelected(response.getCorrect());
        optionCheckbox.setDisable(false);
        optionCheckbox.setUserData(response.getId_Reponse());

        TextField optionTextField = new TextField(response.getReponse());
        optionTextField.setPrefWidth(632);
        optionTextField.setPrefHeight(48);
        optionTextField.setEditable(true);
        optionTextField.setStyle("-fx-background-color: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-family: sans-serif; " +
                "-fx-border-color: #9b9aac; " +
                "-fx-border-width: 0px 0px 1px 0px; " +
                "-fx-border-radius: 0px 0px 5px 5px;");
        optionTextField.setUserData(response.getId_Reponse());


        optionBox.getChildren().addAll(optionCheckbox, optionTextField);

        questionBox.getChildren().add(optionBox);
    }




    @FXML
    void update_btn_OnAction(ActionEvent event) {
        try {
            ServicesTest testService = new ServicesTest();
            ServicesQuestion questionService = new ServicesQuestion();
            ServicesReponses responseService = new ServicesReponses();


            if (!test_Name.getText().isEmpty() && testService.testNameExists(test_Name.getText())) {
                showAlert("Duplicate Test Name", "A test with this name already exists. Please choose a different name.");
                return;
            }
            if (!test_time.getText().isEmpty() && !test_time.getText().matches("^((\\d+\\s+hour(s?)\\s*,\\s*)?(\\d+\\s+minute(s?)\\s*)?)$")) {
                showAlert("Invalid Time Format","The time format should be in the form 'X hour(s), Y minute(s)', where X and Y are positive integers.");

                return;
            }
            if (!testService.testNameExists(test_Name.getText()) && !test_Name.getText().isEmpty()) {
                mainTest.setNom_Test(test_Name.getText());
            }
            if (!test_time.getText().isEmpty()) {
                mainTest.setTemp_pris(test_time.getText());
            }
            if (!test_Description.getText().isEmpty()) {
                mainTest.setDescription(test_Description.getText());
            }
            if (!test_time.getText().isEmpty()) {
                mainTest.setTemp_pris(test_time.getText());
            }

            if (test_Categorie.getValue() != null) {
                mainTest.setCategorie(test_Categorie.getValue());
            }

            testService.modifier(mainTest);

            for (Node questionNode : option_vbox.getChildren()) {
                if (questionNode instanceof VBox) {
                    VBox questionBox = (VBox) questionNode;
                    TextField questionTextField = (TextField) questionBox.getChildren().get(0);

                    int question_id = (Integer) questionTextField.getUserData();
                    if (!questionTextField.getText().isEmpty()) {
                        Questiont questionToUpdate = questionService.recupererQuestionParId(question_id);
                        questionToUpdate.setText(questionTextField.getText());
                        questionService.modifier(questionToUpdate);
                    }

                    for (Node optionNode : questionBox.getChildren()) {
                        if (optionNode instanceof HBox) {
                            HBox optionHBox = (HBox) optionNode;
                            CheckBox optionCheckBox = (CheckBox) optionHBox.getChildren().get(0);
                            TextField optionTextField = (TextField) optionHBox.getChildren().get(1);

                            int responseId = (Integer) optionTextField.getUserData();



                            if (!optionTextField.getText().isEmpty()) {
                                Reponse responseToUpdate = responseService.recupererReponseParId(responseId);
                                responseToUpdate.setReponse(optionTextField.getText());
                                responseToUpdate.setCorrect(optionCheckBox.isSelected());
                                responseService.modifier(responseToUpdate);
                            }
                        }
                    }
                }
            }

            showAlert("Success", "Test updated successfully!");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/UpdateTest.fxml"));

            try {
                Parent p = (Parent) fxmlLoader.load();
                main_anchor.setTopAnchor(p, 0.0);
                main_anchor.setRightAnchor(p, 0.0);
                main_anchor.setBottomAnchor(p, 0.0);
                main_anchor.setLeftAnchor(p, 0.0);

                main_anchor.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        } catch (SQLException e) {
            showAlert("Database Error", "Error updating test: " + e.getMessage());
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }

    }
    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    void cancel_btn_OnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/UpdateTest.fxml"));

        try {
            Parent p = (Parent) fxmlLoader.load();
            main_anchor.setTopAnchor(p, 0.0);
            main_anchor.setRightAnchor(p, 0.0);
            main_anchor.setBottomAnchor(p, 0.0);
            main_anchor.setLeftAnchor(p, 0.0);

            main_anchor.getChildren().add(p);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}









