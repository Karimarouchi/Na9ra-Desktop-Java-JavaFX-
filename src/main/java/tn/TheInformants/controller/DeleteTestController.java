package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tn.TheInformants.entities.Test;
import tn.TheInformants.services.ServicesTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteTestController {


    @FXML
    private AnchorPane main_anchor;
    @FXML
    private AnchorPane barre_anchor;

    @FXML
    private AnchorPane small_anchor;

    @FXML
    private Label test_category;

    @FXML
    private Label test_description;

    @FXML
    private Label test_name;

    @FXML
    private Label test_status;

    @FXML
    private Label test_time;

    @FXML
    private Button delete_btn;
    @FXML
    private VBox vbox_container;

    private List<Test> testlist =new ArrayList<>();
    private int points = 0;


    @FXML
    void update_btn_OnAction(ActionEvent event) {

    }
    private String updateDescriptionAndPoints(String newDescription) {
        String truncatedDescription = newDescription.substring(0, Math.min(newDescription.length(), 15));

        if (newDescription.length() > 15) {
            truncatedDescription += "...";
        }

        if (truncatedDescription.length() > 15) {
            points += 3;
        }

        test_description.setText(truncatedDescription);

        return truncatedDescription;
    }

    public void initialize() {
        //vbox_container.getChildren().remove(small_anchor);



        ServicesTest testservice = new ServicesTest();
        try {
            testlist = testservice.recuperer();
            double yOffset = 8.0;

            for (Test testL : testlist) {
                AnchorPane smallAnchorClone = new AnchorPane();
                smallAnchorClone.setLayoutX(small_anchor.getLayoutX());
                smallAnchorClone.setLayoutY(yOffset);
                smallAnchorClone.setPrefHeight(small_anchor.getPrefHeight());
                smallAnchorClone.setPrefWidth(small_anchor.getPrefWidth());
                smallAnchorClone.getStyleClass().addAll(small_anchor.getStyleClass());


                Label testName = new Label();
                testName.setLayoutX(test_name.getLayoutX());
                testName.setLayoutY(test_name.getLayoutY());
                testName.setText(test_name.getText() + testL.getNom_Test());

                Label testStatus = new Label();
                testStatus.setLayoutX(test_status.getLayoutX());
                testStatus.setLayoutY(test_status.getLayoutY());
                testStatus.setText(test_status.getText() + testL.getStatus());

                Label testCategory = new Label();
                testCategory.setLayoutX(test_category.getLayoutX());
                testCategory.setLayoutY(test_category.getLayoutY());
                testCategory.setText(test_category.getText() + testL.getCategorie());

                Label testTime = new Label();
                testTime.setLayoutX(test_time.getLayoutX());
                testTime.setLayoutY(test_time.getLayoutY());
                testTime.setText(test_time.getText() + testL.getTemp_pris());

                Label testDescription = new Label();
                testDescription.setLayoutX(test_description.getLayoutX());
                testDescription.setLayoutY(test_description.getLayoutY());
                testDescription.setText(updateDescriptionAndPoints(testL.getDescription()));


                Button DeleteBtnClone = new Button("Delete");
                DeleteBtnClone.setLayoutX(delete_btn.getLayoutX());
                DeleteBtnClone.setLayoutY(delete_btn.getLayoutY());
                DeleteBtnClone.getStyleClass().addAll(delete_btn.getStyleClass());

                DeleteBtnClone.setOnAction(event -> {
                    try {

                        ServicesTest serviceTest = new ServicesTest();
                        serviceTest.deleteTestById(testL.getId_Test());


                        loadData();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Test deleted successfully!", ButtonType.OK);
                        alert.showAndWait();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.err.println("Error deleting test: " + e.getMessage());
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting test: " + e.getMessage(), ButtonType.OK);
                        alert.showAndWait();
                    }
                });

                smallAnchorClone.getChildren().addAll(testName, testStatus, testCategory,testTime,
                        testDescription,DeleteBtnClone);
                vbox_container.getChildren().add(smallAnchorClone);


                yOffset += small_anchor.getPrefHeight() + 5.0;
            }
        } catch (SQLException ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
    }
    private void loadData() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/DeleteForm.fxml"));

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


    @FXML
    public void delete_btn_OnAction(ActionEvent event){}
}