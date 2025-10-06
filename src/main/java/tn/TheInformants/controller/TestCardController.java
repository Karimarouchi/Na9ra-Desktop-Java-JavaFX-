package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.TheInformants.entities.Test;
import tn.TheInformants.services.ServicesTest;

import java.io.IOException;
import java.sql.SQLException;

public class TestCardController {

    @FXML
    private Label test_desc;

    @FXML
    private ImageView test_logo;

    @FXML
    private Label test_name;
    @FXML

    private AnchorPane cardRoot;
    private Test test;
    private TestUIuserController parentController;

    public void setParentController(TestUIuserController controller) {
        this.parentController = controller;
    }
    public void setCard(Test test) {
        System.out.println("hello");
        this.test = test;

        test_desc.setText(test.getDescription());
        test_name.setText(test.getNom_Test());
        if (test.getCategorie().toString().equals("University"))
        {
            Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/icons8-university-24.png"));
            test_logo.setImage(newImage);
        }
        else if (test.getCategorie().toString().equals("Language"))
        {
            Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/icons8-language-24 (1).png"));
            test_logo.setImage(newImage);
        }
        else if (test.getCategorie().toString().equals("Academic"))
        {
            Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/icons8-mortarboard-24.png"));
            test_logo.setImage(newImage);
        }
        else if (test.getCategorie().toString().equals("Skills"))
        {
            Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/icons8-knowledge-transfer-24.png"));
            test_logo.setImage(newImage);
        }
        else if (test.getCategorie().toString().equals("Citizenship"))
        {
            Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/icons8-global-citizen-24.png"));
            test_logo.setImage(newImage);
        }
        else if (test.getCategorie().toString().equals("Knowledge"))
        {
            Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/icons8-problem-solving-skills-24.png"));
            test_logo.setImage(newImage);
        }
        else if (test.getCategorie().toString().equals("IQ"))
        {
            Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/icons8-brain-24.png"));
            test_logo.setImage(newImage);
        }

        cardRoot.setOnMouseClicked(event -> openTestDetails());


    }
    private void openTestDetails() {

        ServicesTest testService = new ServicesTest();
        try {
            boolean hasDisplayed = testService.checkIfDisplayed(LoginController.user1.getUser_id(),test.getId_Test());
            System.out.println("hasDisplayed"+hasDisplayed);
            if (hasDisplayed==false) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/User/AffichageTest.fxml"));
                Parent openTest = loader.load();

                AffichageTestController controllerAffichageTest = loader.getController();
                System.out.println("controllerAffichageTest" + controllerAffichageTest);


                controllerAffichageTest.setTest(test);
                System.out.println(test);

                parentController.getContentDisplayArea().getChildren().setAll(openTest);
            }else{    showAlert("you can dispaly a test only once");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert( String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.showAndWait();
    }
    }

