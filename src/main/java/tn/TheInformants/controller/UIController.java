package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {
    @FXML
    private Button Dashboard_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private Button book_btn;

    @FXML
    private Button cours_btn;

    @FXML
    private Button evenements_btn;

    @FXML
    private AnchorPane midlast;

    @FXML
    private Button quizz_btn;

    @FXML
    private Button test_btn;

    @FXML
    private Button user_btn;

    @FXML
    private Line l1;

    @FXML
    private Line l2;

    @FXML
    private Line l3;

    @FXML
    private Line l4;

    @FXML
    private Line l5;

    @FXML
    private Line l6;

    @FXML
    private Line l7;

public void Dashboard_btn_clicked(){
    l1.setVisible(true);
    l2.setVisible(false);
    l3.setVisible(false);
    l4.setVisible(false);
    l5.setVisible(false);
    l6.setVisible(false);
    l7.setVisible(false);
    cours_btn.setStyle("-fx-text-fill: #9b9aac;");
    evenements_btn.setStyle("-fx-text-fill: #9b9aac;");
    quizz_btn.setStyle("-fx-text-fill: #9b9aac;");
    test_btn.setStyle("-fx-text-fill: #9b9aac;");
    Dashboard_btn.setStyle("-fx-text-fill: black;");
    book_btn.setStyle("-fx-text-fill: #9b9aac;");
    user_btn.setStyle("-fx-text-fill:  #9b9aac;");

    // Change the image of the button
    Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard5.png"));
    ImageView imageView = new ImageView(newImage);
    imageView.setFitWidth(24); // Set width according to your requirement
    imageView.setFitHeight(24); // Set height according to your requirement
    Dashboard_btn.setGraphic(imageView);
    Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course.png"));
    ImageView imageView1 = new ImageView(newImage1);
    imageView1.setFitWidth(24); // Set width according to your requirement
    imageView1.setFitHeight(24); // Set height according to your requirement
    cours_btn.setGraphic(imageView1);
    Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event.png"));
    ImageView imageView2 = new ImageView(newImage2);
    imageView2.setFitWidth(24); // Set width according to your requirement
    imageView2.setFitHeight(24); // Set height according to your requirement
    evenements_btn.setGraphic(imageView2);
    Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/test.png"));
    ImageView imageView3 = new ImageView(newImage3);
    imageView3.setFitWidth(24); // Set width according to your requirement
    imageView3.setFitHeight(24); // Set height according to your requirement
    test_btn.setGraphic(imageView3);
    Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/ebook.png"));
    ImageView imageView4 = new ImageView(newImage4);
    imageView4.setFitWidth(24); // Set width according to your requirement
    imageView4.setFitHeight(24); // Set height according to your requirement
    book_btn.setGraphic(imageView4);
    Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/quiz.png"));
    ImageView imageView5 = new ImageView(newImage5);
    imageView5.setFitWidth(24); // Set width according to your requirement
    imageView5.setFitHeight(24); // Set height according to your requirement
    quizz_btn.setGraphic(imageView5);
}
    public void cours_btn_clicked(){
        l1.setVisible(false);
        l2.setVisible(true);
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(false);
        l7.setVisible(false);
        cours_btn.setStyle("-fx-text-fill: black;");
        evenements_btn.setStyle("-fx-text-fill: #9b9aac;");
        quizz_btn.setStyle("-fx-text-fill: #9b9aac;");
        test_btn.setStyle("-fx-text-fill: #9b9aac;");
        Dashboard_btn.setStyle("-fx-text-fill: #9b9aac;");
        book_btn.setStyle("-fx-text-fill: #9b9aac;");
        user_btn.setStyle("-fx-text-fill:  #9b9aac;");

        // Change the image of the button
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard4.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24); // Set height according to your requirement
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course2.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24); // Set height according to your requirement
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24); // Set height according to your requirement
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/test.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24); // Set height according to your requirement
        test_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/ebook.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24); // Set height according to your requirement
        book_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/quiz.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24); // Set height according to your requirement
        quizz_btn.setGraphic(imageView5);
    }
    public void evenements_btn_clicked(){
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(true);
        l4.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(false);
        l7.setVisible(false);
        user_btn.setStyle("-fx-text-fill:  #9b9aac;");
        cours_btn.setStyle("-fx-text-fill: #9b9aac;");
        evenements_btn.setStyle("-fx-text-fill: black;");
        quizz_btn.setStyle("-fx-text-fill: #9b9aac;");
        test_btn.setStyle("-fx-text-fill: #9b9aac;");
        Dashboard_btn.setStyle("-fx-text-fill: #9b9aac");
        book_btn.setStyle("-fx-text-fill: #9b9aac;");

        // Change the image of the button
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard4.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24); // Set height according to your requirement
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24); // Set height according to your requirement
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event2.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24); // Set height according to your requirement
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/test.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24); // Set height according to your requirement
        test_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/ebook.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24); // Set height according to your requirement
        book_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/quiz.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24); // Set height according to your requirement
        quizz_btn.setGraphic(imageView5);
    }
    public void book_btn_clicked(){
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(true);
        l6.setVisible(false);
        l7.setVisible(false);
        cours_btn.setStyle("-fx-text-fill: #9b9aac;");
        evenements_btn.setStyle("-fx-text-fill: #9b9aac;");
        quizz_btn.setStyle("-fx-text-fill: #9b9aac;");
        test_btn.setStyle("-fx-text-fill: #9b9aac;");
        Dashboard_btn.setStyle("-fx-text-fill:  #9b9aac;");
        book_btn.setStyle("-fx-text-fill: black;");
        user_btn.setStyle("-fx-text-fill:  #9b9aac;");

        // Change the image of the button
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard4.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24); // Set height according to your requirement
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24); // Set height according to your requirement
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24); // Set height according to your requirement
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/test.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24); // Set height according to your requirement
        test_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/book2.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24); // Set height according to your requirement
        book_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/quiz.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24); // Set height according to your requirement
        quizz_btn.setGraphic(imageView5);
    }
    public void test_btn_clicked(){
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(true);
        l5.setVisible(false);
        l6.setVisible(false);
        l7.setVisible(false);
        cours_btn.setStyle("-fx-text-fill: #9b9aac;");
        evenements_btn.setStyle("-fx-text-fill: #9b9aac;");
        quizz_btn.setStyle("-fx-text-fill: #9b9aac;");
        test_btn.setStyle("-fx-text-fill: black;");
        Dashboard_btn.setStyle("-fx-text-fill: #9b9aac;");
        book_btn.setStyle("-fx-text-fill: #9b9aac;");
        user_btn.setStyle("-fx-text-fill:  #9b9aac;");

        // Change the image of the button
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard4.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24); // Set height according to your requirement
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24); // Set height according to your requirement
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24); // Set height according to your requirement
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/test2.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24); // Set height according to your requirement
        test_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/ebook.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24); // Set height according to your requirement
        book_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/quiz.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24); // Set height according to your requirement
        quizz_btn.setGraphic(imageView5);
    }
    public void quiz_btn_clicked(){
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(true);
        l7.setVisible(false);
        user_btn.setStyle("-fx-text-fill:  #9b9aac;");
        cours_btn.setStyle("-fx-text-fill: #9b9aac;");
        evenements_btn.setStyle("-fx-text-fill: #9b9aac;");
        quizz_btn.setStyle("-fx-text-fill:black;");
        test_btn.setStyle("-fx-text-fill: #9b9aac;");
        Dashboard_btn.setStyle("-fx-text-fill: #9b9aac;");
        book_btn.setStyle("-fx-text-fill: #9b9aac;");

        // Change the image of the button
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard4.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24); // Set height according to your requirement
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24); // Set height according to your requirement
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24); // Set height according to your requirement
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/test.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24); // Set height according to your requirement
        test_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/ebook.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24); // Set height according to your requirement
        book_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/quizz2.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24); // Set height according to your requirement
        quizz_btn.setGraphic(imageView5);
    }
    @FXML
    void user_btn_clicked() {
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(false);
        l7.setVisible(true);
        user_btn.setStyle("-fx-text-fill:  black;");
        cours_btn.setStyle("-fx-text-fill: #9b9aac;");
        evenements_btn.setStyle("-fx-text-fill: #9b9aac;");
        quizz_btn.setStyle("-fx-text-fill:#9b9aac;");
        test_btn.setStyle("-fx-text-fill: #9b9aac;");
        Dashboard_btn.setStyle("-fx-text-fill: #9b9aac;");
        book_btn.setStyle("-fx-text-fill: #9b9aac;");

        // Change the image of the button
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard4.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24); // Set height according to your requirement
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24); // Set height according to your requirement
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24); // Set height according to your requirement
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/test.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24); // Set height according to your requirement
        test_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/ebook.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24); // Set height according to your requirement
        book_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/quizz2.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24); // Set height according to your requirement
        quizz_btn.setGraphic(imageView5);
    }



    public void logout() throws IOException {
//        logout_btn.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/sharedInterface/AI.fxml"));
        Stage stage =new Stage();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(false);
        l7.setVisible(false);
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/gui/User/DashboardUI.fxml"));
        try {
            Parent p = (Parent) fxmlLoader1.load();
            midlast.getChildren().clear();
            midlast.getChildren().add(p);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        cours_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/test.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Dashboard_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/DashboardUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        book_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/UIBOOK.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        evenements_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/EvenementUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        test_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/TestUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        quizz_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/QuizUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
       user_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/userUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
