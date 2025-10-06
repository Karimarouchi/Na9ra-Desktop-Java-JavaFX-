package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.TheInformants.entities.Test;
import tn.TheInformants.services.ServicesTest;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TestUIuserController implements Initializable {
    private List<Test> testlist =new ArrayList<>();
    @FXML
    private AnchorPane contentDisplayArea;

    @FXML

    private VBox vBox;
    public AnchorPane getContentDisplayArea() {
        return contentDisplayArea;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final int numElementsPerLine = 5;
        ServicesTest testService = new ServicesTest();
        try {
            List<Test> testList = testService.recuperer();

            HBox currentHBox = new HBox(8.0);
            int count = 0;
            for (Test test : testList) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/TestCard.fxml"));
                try {
                    Parent p = fxmlLoader.load();
                    HBox.setMargin(p, new Insets(20));

                    TestCardController testCardController = fxmlLoader.getController();
                    testCardController.setCard(test);
                    testCardController.setParentController(this);

                    currentHBox.getChildren().add(p);
                    count++;

                    if (count % numElementsPerLine == 0 || count == testList.size()) {
                        vBox.getChildren().add(currentHBox);
                        currentHBox = new HBox(8.0);
                        count = 0;
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }}

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}






