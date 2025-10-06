package tn.TheInformants.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class AIController implements Initializable {
    @FXML
    private Label Course_label;

    @FXML
    private Button Dashboard_btn;

    @FXML
    private Label Dashboard_label;

    @FXML
    private Label Events_label;

    @FXML
    private Button book_btn;

    @FXML
    private Button cours_btn;

    @FXML
    private Label ebooks_label;

    @FXML
    private Button evenements_btn;
    @FXML
    private AnchorPane midlast;
    @FXML
    private Button logout_btn;

    @FXML
    private Label quiz_label;

    @FXML
    private Button quizz_btn;

    @FXML
    private Button test_btn;

    @FXML
    private Label test_label;
    @FXML
    public void Dashboard_btn_clicked(){
        Dashboard_btn.setStyle("-fx-background-color: #1478FF;  -fx-effect: dropshadow(three-pass-box,rgba(20,120,255,0.5),30,0,0,0);");
        Dashboard_label.setStyle("-fx-text-fill: #1478FF;");


        cours_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Course_label.setStyle("-fx-text-fill: black;");

        evenements_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        quizz_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        test_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        book_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        ebooks_label.setStyle("-fx-text-fill: black;");
        quiz_label.setStyle("-fx-text-fill: black;");
        test_label.setStyle("-fx-text-fill: black;");
        Events_label.setStyle("-fx-text-fill: black;");
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard1.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24);
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course2.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24);
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event2.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24);
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/quizz2.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24);
        quizz_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/test2.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24);
        test_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/book2.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24);
        book_btn.setGraphic(imageView5);
    }
    @FXML
    void book_btn_clicked() {
        Dashboard_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Dashboard_label.setStyle("-fx-text-fill: black;");


        cours_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Course_label.setStyle("-fx-text-fill: black;");

        evenements_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        quizz_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        test_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        book_btn.setStyle("-fx-background-color: #1478FF;  -fx-effect: dropshadow(three-pass-box,rgba(20,120,255,0.5),30,0,0,0);");
        ebooks_label.setStyle("-fx-text-fill: #1478FF;");
        quiz_label.setStyle("-fx-text-fill: black;");
        test_label.setStyle("-fx-text-fill: black;");
        Events_label.setStyle("-fx-text-fill: black;");
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24);
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course2.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24);
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event2.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24);
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/quizz2.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24);
        quizz_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/test2.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24);
        test_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/book3.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24);
        book_btn.setGraphic(imageView5);
    }

    @FXML
    void cours_btn_clicked() {
        Dashboard_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Dashboard_label.setStyle("-fx-text-fill: black;");


        cours_btn.setStyle("-fx-background-color: #1478FF;  -fx-effect: dropshadow(three-pass-box,rgba(20,120,255,0.5),30,0,0,0);");
        Course_label.setStyle("-fx-text-fill: #1478FF;");

        evenements_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        quizz_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        test_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        book_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        ebooks_label.setStyle("-fx-text-fill: black;");
        quiz_label.setStyle("-fx-text-fill: black;");
        test_label.setStyle("-fx-text-fill: black;");
        Events_label.setStyle("-fx-text-fill: black;");
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24);
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course3.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24);
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event2.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24);
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/quizz2.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24);
        quizz_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/test2.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24);
        test_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/book2.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24);
        book_btn.setGraphic(imageView5);
    }

    @FXML
    void evenements_btn_clicked() {
        Dashboard_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Dashboard_label.setStyle("-fx-text-fill: black;");


        cours_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Course_label.setStyle("-fx-text-fill: black;");

        evenements_btn.setStyle("-fx-background-color: #1478FF;  -fx-effect: dropshadow(three-pass-box,rgba(20,120,255,0.5),30,0,0,0);");
        quizz_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        test_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        book_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        ebooks_label.setStyle("-fx-text-fill: black;");
        quiz_label.setStyle("-fx-text-fill: black;");
        test_label.setStyle("-fx-text-fill: black;");
        Events_label.setStyle("-fx-text-fill: #1478FF;");
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24);
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course2.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24);
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event3.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24);
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/quizz2.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24);
        quizz_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/test2.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24);
        test_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/book2.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24);
        book_btn.setGraphic(imageView5);
    }

    @FXML
    void quiz_btn_clicked() {
        Dashboard_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Dashboard_label.setStyle("-fx-text-fill: black;");


        cours_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Course_label.setStyle("");

        evenements_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        quizz_btn.setStyle("-fx-background-color: #1478FF;  -fx-effect: dropshadow(three-pass-box,rgba(20,120,255,0.5),30,0,0,0);");
        test_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        book_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        ebooks_label.setStyle("-fx-text-fill: black;");
        quiz_label.setStyle("-fx-text-fill: #1478FF;");
        test_label.setStyle("-fx-text-fill: black;");
        Events_label.setStyle("-fx-text-fill: black;");
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24);
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course2.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24);
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event2.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24);
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/quizz3.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24);
        quizz_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/test2.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24);
        test_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/book2.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24);
        book_btn.setGraphic(imageView5);
    }

    @FXML
    void test_btn_clicked() {
        Dashboard_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Dashboard_label.setStyle("-fx-text-fill: black;");


        cours_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        Course_label.setStyle("-fx-text-fill: black;");

        evenements_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        quizz_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        test_btn.setStyle("-fx-background-color: #1478FF;  -fx-effect: dropshadow(three-pass-box,rgba(20,120,255,0.5),30,0,0,0);");
        book_btn.setStyle("-fx-background-color: rgba(243, 248, 254, 1);");
        ebooks_label.setStyle("-fx-text-fill: black;");
        quiz_label.setStyle("-fx-text-fill: black;");
        test_label.setStyle("-fx-text-fill: #1478FF;");
        Events_label.setStyle("-fx-text-fill: black;");
        Image newImage = new Image(getClass().getResourceAsStream("/gui/Rresources/dashboard.png"));
        ImageView imageView = new ImageView(newImage);
        imageView.setFitWidth(24); // Set width according to your requirement
        imageView.setFitHeight(24);
        Dashboard_btn.setGraphic(imageView);
        Image newImage1 = new Image(getClass().getResourceAsStream("/gui/Rresources/course2.png"));
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(24); // Set width according to your requirement
        imageView1.setFitHeight(24);
        cours_btn.setGraphic(imageView1);
        Image newImage2 = new Image(getClass().getResourceAsStream("/gui/Rresources/event2.png"));
        ImageView imageView2 = new ImageView(newImage2);
        imageView2.setFitWidth(24); // Set width according to your requirement
        imageView2.setFitHeight(24);
        evenements_btn.setGraphic(imageView2);
        Image newImage3 = new Image(getClass().getResourceAsStream("/gui/Rresources/quizz2.png"));
        ImageView imageView3 = new ImageView(newImage3);
        imageView3.setFitWidth(24); // Set width according to your requirement
        imageView3.setFitHeight(24);
        quizz_btn.setGraphic(imageView3);
        Image newImage4 = new Image(getClass().getResourceAsStream("/gui/Rresources/test3.png"));
        ImageView imageView4 = new ImageView(newImage4);
        imageView4.setFitWidth(24); // Set width according to your requirement
        imageView4.setFitHeight(24);
        test_btn.setGraphic(imageView4);
        Image newImage5 = new Image(getClass().getResourceAsStream("/gui/Rresources/book2.png"));
        ImageView imageView5 = new ImageView(newImage5);
        imageView5.setFitWidth(24); // Set width according to your requirement
        imageView5.setFitHeight(24);
        book_btn.setGraphic(imageView5);
    }
    @FXML
    public void logout()  {
System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        FXMLLoader fxmlLoader121 = new FXMLLoader(getClass().getResource("/gui/Admin/DashboardUI.fxml"));
        try {
            Parent p = (Parent) fxmlLoader121.load();
            midlast.getChildren().clear();
            midlast.getChildren().add(p);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        cours_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/CourseUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Dashboard_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/DashboardUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        book_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/eBookUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        evenements_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/EvenementUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        test_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/TestUI.fxml"));
            try {
                Parent p = (Parent) fxmlLoader.load();
                midlast.getChildren().clear();
                midlast.getChildren().add(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        quizz_btn.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/QuizUI.fxml"));
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
