package tn.TheInformants.controller;

import com.google.gson.*;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.apache.commons.text.StringEscapeUtils;
import tn.TheInformants.Enums.Role;
import tn.TheInformants.entities.Question;
import tn.TheInformants.entities.Quiz;
import tn.TheInformants.entities.Score;
import tn.TheInformants.services.ServiceQuestion;
import tn.TheInformants.services.ServiceQuiz;
import tn.TheInformants.services.ServiceScore;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class QuizController implements Initializable {

    @FXML
    public GridPane itemlist;
    @FXML
    private ImageView imageviewi,imageviewi1;


    @FXML
    private Button ajouterquiz_btn;
    private List<Quiz> q;
    @FXML
    private ComboBox<String> inputquizz_cat;
    @FXML
    private AnchorPane quiz_int;
    @FXML
    public AnchorPane play_int;
    @FXML
    private AnchorPane ajouterquiz_int;

    @FXML
    private ImageView PictureChooser;

    @FXML
    private TextArea inputquizz_descrp;




    @FXML
    private TextField inputquizz_title;
    @FXML
    private ImageView PictureChooser1;

    @FXML
    private TextArea inputquizz_descrp1;
    @FXML
    private ComboBox<String>inputquizz_cat1;
    @FXML
    private TextField inputquizz_id1;



    @FXML
    private TextField inputquizz_title1;
    ArrayList <Question> questions,questionss,questio;
    @FXML
    private TextArea afieldmod1;


    @FXML
    private TextArea afieldmod2;

    @FXML
    private TextArea afieldmod3;

    @FXML
    private TextArea afieldmod4;

    @FXML
    private TextArea questionfieldmod;
    @FXML
    private TextArea afield1;
    @FXML
    private TextField quizrech_field;

    @FXML
    private TextArea afield2;

    @FXML
    private TextArea afield3;

    @FXML
    private TextArea afield4;

    @FXML
    private TextArea questionfield;
    @FXML
    private RadioButton cat1;

    @FXML
    private RadioButton cat2;

    @FXML
    private RadioButton cat3;

    @FXML
    private RadioButton cat4;

    @FXML
    private RadioButton cat5;

    @FXML
    private RadioButton cat6;

    @FXML
    private RadioButton catn1;

    @FXML
    private RadioButton catn2;

    @FXML
    private RadioButton catn3;
    @FXML
    private ComboBox<String> triQuiz;
    ToggleGroup toggleGroup = new ToggleGroup();
    ToggleGroup toggleGroupnb = new ToggleGroup();


public void show(){
    ServiceQuiz serviceQuiz =new ServiceQuiz();
    try {
        q =  serviceQuiz.afficher();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    int col=0;
    int row=1;
    for (Quiz qzz : q ) {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/quizItem.fxml"));
        try {
            AnchorPane p =fxmlLoader.load();
            ItemQuizzController cntrll = fxmlLoader.getController();
            cntrll.setDATA(qzz,this);
            if(col==3){
                col=0;
                row++;
            }
            itemlist.add(p,col++,row);
            GridPane.setMargin(p,new Insets(10));
            //  itemlist.getChildren().add(p);
        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        } }


    // Assign the radio buttons to the toggle group
    cat1.setToggleGroup(toggleGroup);
    cat2.setToggleGroup(toggleGroup);
    cat3.setToggleGroup(toggleGroup);
    cat4.setToggleGroup(toggleGroup);
    cat6.setToggleGroup(toggleGroup);
    cat5.setToggleGroup(toggleGroup);
    catn1.setToggleGroup(toggleGroupnb);
    catn2.setToggleGroup(toggleGroupnb);
    catn3.setToggleGroup(toggleGroupnb);
    triQuiz.getItems().add("default");
    triQuiz.getItems().add("titre");
    triQuiz.getItems().add("Question");
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playy_int.setVisible(false);
        ajouterquiz_btn.setOnMouseClicked(e -> {
            inputquizz_title.setText("");
            inputquizz_descrp.setText("");
            inputquizz_cat.getItems().clear();
            ajouterquiz_int.setVisible(true);
            quiz_int.setVisible(false);
            inputquizz_cat.getItems().add("General Knowledge");
            inputquizz_cat.getItems().add("Science & Nature");
            inputquizz_cat.getItems().add("Science: Computers");
            inputquizz_cat.getItems().add("Science: Mathematics");
            inputquizz_cat.getItems().add("History");
            inputquizz_cat.getItems().add("Art");
            questions =new ArrayList<>();
        });
        try {
            filterall();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cat1.setToggleGroup(toggleGroup);
        cat2.setToggleGroup(toggleGroup);
        cat3.setToggleGroup(toggleGroup);
        cat4.setToggleGroup(toggleGroup);
        cat6.setToggleGroup(toggleGroup);
        cat5.setToggleGroup(toggleGroup);
        catn1.setToggleGroup(toggleGroupnb);
        catn2.setToggleGroup(toggleGroupnb);
        catn3.setToggleGroup(toggleGroupnb);
        triQuiz.getItems().add("default");
        triQuiz.getItems().add("titre");
        triQuiz.getItems().add("Question");
    }

    public  void back_btn_clicked(){
       quiz_int.setVisible(true);
       ajouterquiz_int.setVisible(false);
       modquiz_int.setVisible(false);
        handleFilterAction();

}

    String imagepath,imagepath1;


    public void addquiz_btn_clicked() throws SQLException {
        currentIndex = 0;
        backIndex = 0;

        String descrp = inputquizz_descrp.getText();
        String title = inputquizz_title.getText();
        String cat = inputquizz_cat.getValue();
        String image = "/gui/Rresources/" + imagepath;

        // Check if any of the required fields are empty
        if (descrp.isEmpty() || title.isEmpty() || cat == null || questions.isEmpty() || imagepath.isEmpty()) {
            // Show a pop-up message for the violation
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            if (descrp.isEmpty()) {
                alert.setContentText("Description field is empty!");
            } else if (title.isEmpty()) {
                alert.setContentText("Title field is empty!");
            } else if (cat == null) {
                alert.setContentText("Category is not selected!");
            } else if (questions.isEmpty()) {
                alert.setContentText("No questions added!");
            } else if (imagepath.isEmpty()) {
                alert.setContentText("Image field is empty!");
            }
            alert.showAndWait();
            return; // Stop execution if any field is empty
        }

        // Proceed with quiz creation if all fields are filled

        ServiceQuiz serviceQuiz = new ServiceQuiz();
        int nb = questions.size();
        Quiz quiz = new Quiz(descrp, title, nb, cat, LoginController.user1.getUser_id(), image);
        serviceQuiz.ajouter(quiz);

        ServiceQuestion serviceQuestion = new ServiceQuestion();

        // Add questions to the MySQL table and associate them with the quiz
        for (Question question : questions) {
            serviceQuestion.ajouter(question, serviceQuiz.returnid(quiz.getTitre()));
        }

        back_btn_clicked();
    }
    @FXML
private CheckBox t1,t2,t3,t4,t1mod,t2mod,t3mod,t4mod;
    private int currentIndex = 0;
    private int backIndex = 0;
    public void nextquest_btn_clicked() {
        if (backIndex == currentIndex) {

                if (currentIndex >= questions.size()) {
                    if (t1.isSelected())

                    questions.add(new Question(questionfield.getText(), afield1.getText(), afield2.getText(), afield3.getText(), afield4.getText(),afield1.getText()));
                  else  if (t2.isSelected())

                        questions.add(new Question(questionfield.getText(), afield1.getText(), afield2.getText(), afield3.getText(), afield4.getText(),afield2.getText()));
                    else if (t3.isSelected())

                        questions.add(new Question(questionfield.getText(), afield1.getText(), afield2.getText(), afield3.getText(), afield4.getText(),afield3.getText()));
                    else if (t4.isSelected())

                        questions.add(new Question(questionfield.getText(), afield1.getText(), afield2.getText(), afield3.getText(), afield4.getText(),afield4.getText()));
                    else
                        showAlert("ereur","one answer correct or select one");
                }
                currentIndex++;
                backIndex = currentIndex;
clearFields();

        } else {
            if (t1.isSelected())
            questions.set(backIndex,new Question(questionfield.getText(), afield1.getText(), afield2.getText(), afield3.getText(), afield4.getText(), afield1.getText()));
            else if (t2.isSelected())
                questions.set(backIndex,new Question(questionfield.getText(), afield1.getText(), afield2.getText(), afield3.getText(), afield4.getText(), afield2.getText()));
            else if (t3.isSelected())
                questions.set(backIndex,new Question(questionfield.getText(), afield1.getText(), afield2.getText(), afield3.getText(), afield4.getText(), afield3.getText()));
            else if (t4.isSelected())
                questions.set(backIndex,new Question(questionfield.getText(), afield1.getText(), afield2.getText(), afield3.getText(), afield4.getText(), afield4.getText()));
            else
                showAlert("ereur","one answer correct or select one");
            backIndex++;
                clearFields();

            if (backIndex < questions.size()) {
                populateFields(questions.get(backIndex));
                    // Update the existing question at currentIndex in the questions array

            }

        }

    }

    public void prevques_btn_clicked()  {
        if (backIndex > 0) {
            backIndex--;
            if (backIndex < questions.size()) {
                populateFields(questions.get(backIndex));
            }
        }
    }

    public void delquest_btn_clicked() {
        if (backIndex >= 0 && backIndex < questions.size()) {
            questions.remove(backIndex);
            if (backIndex < questions.size()) {
                populateFields(questions.get(backIndex));
            } else if (backIndex > 0) {
                backIndex--;
                if (backIndex < questions.size()) {
                    populateFields(questions.get(backIndex));
                }
            }
        }
    }

    private void populateFields(Question question) {
        questionfield.setPrefWidth(1039); // Set preferred width to allow wrapping
        afield1.setWrapText(true); // Enable text wrapping
        afield2.setPrefWidth(223);
        afield2.setWrapText(true); // Enable text wrapping
        afield3.setPrefWidth(223);
        afield3.setWrapText(true); // Enable text wrapping
        afield4.setPrefWidth(223);
        afield4.setWrapText(true); // Enable text wrapping
        afield1.setPrefWidth(223);
        afield1.setText(question.getRep1());
        afield2.setText(question.getRep2());
        afield3.setText(question.getRep3());
        afield4.setText(question.getRep4());
        questionfield.setText(question.getQuest());
        if (Objects.equals(question.getRepc(), question.getRep1())){
            t1.setSelected(true);
            t2.setSelected(false);
            t3.setSelected(false);
            t4.setSelected(false);}
        if (Objects.equals(question.getRepc(), question.getRep2())){
            t2.setSelected(true);
        t1.setSelected(false);
        t3.setSelected(false);
        t4.setSelected(false);}
        if (Objects.equals(question.getRepc(), question.getRep3())){
            t3.setSelected(true);
        t2.setSelected(false);
        t1.setSelected(false);
        t4.setSelected(false);}
        if (Objects.equals(question.getRepc(), question.getRep4())){
            t4.setSelected(true);
        t2.setSelected(false);
        t3.setSelected(false);
        t1.setSelected(false);}
    }

    private void clearFields() {
        afield1.clear();
        afield2.clear();
        afield3.clear();
        afield4.clear();
        questionfield.clear();
        afieldmod1.clear();
        afieldmod2.clear();
        afieldmod3.clear();
        afieldmod4.clear();
        questionfieldmod.clear();
        t1.setSelected(false);
        t2.setSelected(false);
        t3.setSelected(false);
        t4.setSelected(false);
        t1mod.setSelected(false);
        t2mod.setSelected(false);
        t3mod.setSelected(false);
        t4mod.setSelected(false);
    }

    @FXML
    private void handlePictureBtn() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");

        // Set the initial directory
        fileChooser.setInitialDirectory(new File("src/main/resources/gui/Rresources"));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("*", "*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Set the selected image to the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            PictureChooser.setImage(image);
imageviewi.setVisible(false);
            // Get just the name of the file without the full path
            String fileName = selectedFile.getName();
            imagepath = fileName;
            // Use fileName as needed, such as storing it in your database
        }
    }



    @FXML
    private void handlePictureBtnmod() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.setInitialDirectory(new File("src/main/resources/gui/Rresources"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("*", "*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Set the selected image to the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            PictureChooser1.setImage(image);
            imageviewi1.setVisible(false);

            // Get just the name of the file without the full path
            String fileName = selectedFile.getName();
            imagepath1=fileName;
            // Use fileName as needed, such as storing it in your database
        }
    }

    @FXML
    private AnchorPane modquiz_int;
    @FXML
    private Label questionplay, a1play, a2play, a3play, a4play, correct, nice;
    @FXML
    private AnchorPane playy_int, count_int;
    @FXML
    private Label count;

    private int index = 0;
@FXML
private Label qcount;
private  int quiz_id;
    public void setUpPlayPage(Quiz quizz) throws IOException {
        play_int.setOpacity(0); // Set initial opacity to 0
        play_int.setVisible(false); // Initially hide the AnchorPane
        playy_int.setVisible(true); // Initially show the AnchorPane
        quiz_int.setVisible(false); // Make the AnchorPane invisible
        count_int.setOpacity(0); // Set initial opacity to 0
        count_int.setVisible(true); // Initially show the countdown AnchorPane

        // Initialize the list of questions
        try {
            questio = ServiceQuiz.retirer(quizz.getQuiz_id()); // Changed variable name to 'questio'
            quiz_id=quizz.getQuiz_id();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Create a timeline for the countdown animation
        Timeline timeline = new Timeline();

        // Add keyframes for countdown from 3 to 0
        for (int i = 3; i >= 0; i--) {
            final int countValue = i;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(3 - i), e -> {
                count.setText(Integer.toString(countValue));
                count.setOpacity(1.0); // Reset opacity for each step
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        // Add a keyframe to fade in the countdown AnchorPane
        KeyFrame fadeInCountKeyFrame = new KeyFrame(Duration.seconds(0.25), e -> {
            FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(0.25), count_int);
            fadeInTransition.setFromValue(0.0);
            fadeInTransition.setToValue(1.0);
            fadeInTransition.play();
        });
        timeline.getKeyFrames().add(fadeInCountKeyFrame);

        // Add a keyframe to fade out the countdown AnchorPane after the countdown ends
        KeyFrame fadeOutCountKeyFrame = new KeyFrame(Duration.seconds(3.25), e -> {
            FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(0.25), count_int);
            fadeOutTransition.setFromValue(1.0);
            fadeOutTransition.setToValue(0.0);
            fadeOutTransition.play();
        });
        timeline.getKeyFrames().add(fadeOutCountKeyFrame);

        // Add a keyframe to show the play_int AnchorPane after the countdown
        KeyFrame showPlayKeyFrame = new KeyFrame(Duration.seconds(3.25), e -> {
            bgintt.setVisible(true);
            play_int.setVisible(true);
            count_int.setVisible(false); // Hide the countdown AnchorPane with fade out

            // Show the play_int AnchorPane

            // Display the first question
            displayQuestion();
        });
        timeline.getKeyFrames().add(showPlayKeyFrame);

        timeline.play(); // Start the timeline animation
    }
    private int score;
    public void answerClicked(String chosenAnswer) {
        play_int.setDisable(true);

        // Show feedback
        if (chosenAnswer.equals(correct.getText())) {
            nice.setText("Nice");
            if (countdownTimeline != null) {

                score += (int) (10000 * countdownTimeline.getCurrentTime().toSeconds());
                countdownTimeline.stop(); // Stop the countdown timer
                System.out.println(score);
            }
        } else {
            nice.setText("Next time");
        }
        if (countdownTimeline != null) {
            countdownTimeline.stop();
        }
        nice_int.setVisible(true);

        // Smoothly fade in and out the feedback over a longer duration
        FadeTransition fadeInOutTransition = new FadeTransition(Duration.seconds(4), nice_int);
        fadeInOutTransition.setFromValue(0.0);
        fadeInOutTransition.setToValue(1.0);
        fadeInOutTransition.setAutoReverse(true); // Fade out after fading in
        fadeInOutTransition.play();

        // Move to the next question after a brief delay
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(4)); // Wait for 4 seconds
        pauseTransition.setOnFinished(e -> {
            nice_int.setVisible(false);
            index++; // Move to the next question
            displayQuestion(); // Display the next question

        });
        pauseTransition.play();
    }
@FXML
private AnchorPane nice_int;
    private   Timeline countdownTimeline;
    @FXML
    private VBox highscorevb;
@FXML
private AnchorPane bgintt,highscore_int;
    private void displayQuestion() {


        if (index < questio.size()) { // Changed variable name to 'questio'
            Question currentQuestion = questio.get(index); // Changed variable name to 'questio'
            questionplay.setWrapText(true); // Enable text wrapping
            questionplay.setPrefWidth(1023); // Set preferred width to allow wrapping
            a1play.setWrapText(true); // Enable text wrapping
            a2play.setPrefWidth(223);
            a2play.setWrapText(true); // Enable text wrapping
            a3play.setPrefWidth(223);
            a3play.setWrapText(true); // Enable text wrapping
            a4play.setPrefWidth(223);
            a4play.setWrapText(true); // Enable text wrapping
            a1play.setPrefWidth(223);
            // Set the text of question and answers
            questionplay.setText(currentQuestion.getQuest());
            a1play.setText(currentQuestion.getRep1());
            a2play.setText(currentQuestion.getRep2());
            a3play.setText(currentQuestion.getRep3());
            a4play.setText(currentQuestion.getRep4());
            correct.setText(currentQuestion.getRepc());

            // Ensure the play_int AnchorPane is invisible and disabled before the animation starts
            play_int.setOpacity(0.0);

            // Start the countdown timer for 30 seconds
            AtomicInteger remainingTime = new AtomicInteger(30); // Initial time is 30 seconds
            qcount.setText(Integer.toString(remainingTime.get())); // Display initial time
            countdownTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        int timeLeft = remainingTime.decrementAndGet();
                        qcount.setText(Integer.toString(timeLeft)); // Update countdown label
                        if (timeLeft == 0) { // Check if time is up
                            answerClicked("Time's up!"); // Handle if time is up
                            countdownTimeline.stop(); // Stop the timer
                        }
                    })
            );
            countdownTimeline.setCycleCount(30); // Repeat for 30 seconds
            countdownTimeline.play();

            // Smoothly fade in the question and answers over a longer duration
            FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(3), play_int);
            fadeInTransition.setFromValue(0.0);
            fadeInTransition.setToValue(1.0);
            fadeInTransition.setOnFinished(e -> play_int.setDisable(false)); // Enable the AnchorPane after animation
            fadeInTransition.play();
        } else {
            ServiceScore serviceScore=new ServiceScore();
            Score scoree =new Score(score,quiz_id,LoginController.user1.getUser_id());
            try {

                serviceScore.ajouter(scoree);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            playy_int.setVisible(false);
            quiz_int.setVisible(true);
            highscore_int.setVisible(true);
            highscorevb.getChildren().clear();

            List<Score>  s;
                ServiceQuiz serviceQuiz =new ServiceQuiz();
                try {
                 s =  serviceQuiz.afficherscore(quiz_id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            for (Score qzz : s) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/HighScoreItem.fxml"));
                    AnchorPane p = fxmlLoader.load();
                    HighScoreItem ctl = fxmlLoader.getController();
                    ctl.setDATA(qzz, this);
                    highscorevb.getChildren().add(p);
                } catch (IOException ex) {
                    ex.printStackTrace(); // Print the stack trace to identify the cause of the error
                    // Handle the exception accordingly, e.g., show an error message
                }
            }
        }
    }
    public void  out_clicked(){
        highscore_int.setVisible(false);
    }

    // Action handlers for answer buttons
    @FXML
    public void a1play_clicked() {
        answerClicked(a1play.getText());
    }

    @FXML
    public void a2play_clicked() {
        answerClicked(a2play.getText());
    }

    @FXML
    public void a3play_clicked() {
        answerClicked(a3play.getText());
    }

    @FXML
    public void a4play_clicked() {
        answerClicked(a4play.getText());
    }
    public void setUpHighScore(Quiz quiz) throws SQLException {
        highscorevb.getChildren().clear();
        highscore_int.setVisible(true);
        List<Score>  s;
        ServiceQuiz serviceQuiz =new ServiceQuiz();
        try {
            s =  serviceQuiz.afficherscore(quiz.getQuiz_id());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        for (Score qzz : s) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/HighScoreItem.fxml"));
                AnchorPane p = fxmlLoader.load();
                HighScoreItem ctl = fxmlLoader.getController();
                ctl.setDATA(qzz, this);
                highscorevb.getChildren().add(p);
            } catch (IOException ex) {
                ex.printStackTrace(); // Print the stack trace to identify the cause of the error
                // Handle the exception accordingly, e.g., show an error message
            }
        }
    }

    public void setUpEditPage(Quiz quiz) throws SQLException {
        inputquizz_cat1.getItems().clear();
        inputquizz_cat1.getItems().add("General Knowledge");
        inputquizz_cat1.getItems().add("Science & Nature");
        inputquizz_cat1.getItems().add("Science: Computers");
        inputquizz_cat1.getItems().add("Science: Mathematics");
        inputquizz_cat1.getItems().add("History");
        inputquizz_cat1.getItems().add("Art");
        quiz_int.setVisible(false);
        modquiz_int.setVisible(true);
        inputquizz_title1.setText(String.valueOf(quiz.getTitre()));
        inputquizz_descrp1.setText(String.valueOf(quiz.getDecrp()));
        inputquizz_cat1.setValue(String.valueOf(quiz.getCategorie()));
        inputquizz_id1.setText(String.valueOf(quiz.getQuiz_id()));
        InputStream inStream1 = getClass().getResourceAsStream(quiz.getImage_url());
        Image imageObject1 = new Image(inStream1);
        PictureChooser1.setImage(imageObject1);
        //List<Question> questionss = ServiceQuiz.retirer(quiz.getQuiz_id());
        currentIndexmod = quiz.getNb_quest();
        backIndexmod = 1;
        questionss=new ArrayList<>();
       questionss= ServiceQuiz.retirer(quiz.getQuiz_id());
        questionfieldmod.setWrapText(true); // Enable text wrapping
        questionfieldmod.setPrefWidth(1039); // Set preferred width to allow wrapping
        afieldmod1.setWrapText(true); // Enable text wrapping
        afieldmod2.setPrefWidth(223);
        afieldmod2.setWrapText(true); // Enable text wrapping
        afieldmod3.setPrefWidth(223);
        afieldmod3.setWrapText(true); // Enable text wrapping
        afieldmod4.setPrefWidth(223);
        afieldmod4.setWrapText(true); // Enable text wrapping
        afieldmod1.setPrefWidth(223);
        questionfieldmod.setText(questionss.get(0).getQuest());
        afieldmod1.setText(questionss.get(0).getRep1());
        afieldmod2.setText(questionss.get(0).getRep2());
        afieldmod3.setText(questionss.get(0).getRep3());
        afieldmod4.setText(questionss.get(0).getRep4());
        if(Objects.equals(questionss.get(0).getRepc(), questionss.get(0).getRep1())){
            t1mod.setSelected(true);
        }
        if(Objects.equals(questionss.get(0).getRepc(), questionss.get(0).getRep2())){
            t2mod.setSelected(true);
        }
        if(Objects.equals(questionss.get(0).getRepc(), questionss.get(0).getRep3())){
            t3mod.setSelected(true);
        }
        if(Objects.equals(questionss.get(0).getRepc(), questionss.get(0).getRep4())){
            t4mod.setSelected(true);
        }
    }



    public void modquiz_btn_clicked() throws SQLException {
        // Input validation for text fields
        if (!validateInputs()) {
            return; // Stop execution if inputs are invalid
        }

        ServiceQuiz serviceQuiz = new ServiceQuiz();
        int id = Integer.parseInt(inputquizz_id1.getText());
        String descrp = inputquizz_descrp1.getText();
        String title = inputquizz_title1.getText();
        int nb = questionss.size();
        String cat = inputquizz_cat1.getValue();
        String image = "/gui/Rresources/" + imagepath1;

        Quiz quiz = new Quiz(descrp, title, nb, cat, LoginController.user1.getUser_id(), image);
        serviceQuiz.modifier(quiz, id);

        ServiceQuestion serviceQuestion = new ServiceQuestion();
        for (Question question : questionss) {
            serviceQuiz.delete(serviceQuiz.returnid(quiz.getTitre()));
        }
        // Add questions to the MySQL table and associate them with the quiz
        for (Question question : questionss) {
            serviceQuestion.ajouter(question, serviceQuiz.returnid(quiz.getTitre()));
        }
        back_btn_clicked();
    }

    private boolean validateInputs() {
        // Validate input fields
        if (inputquizz_id1.getText().isEmpty()) {
            showAlert("Error", "ID field is empty!");
            return false;
        }

        if (inputquizz_descrp1.getText().isEmpty()) {
            showAlert("Error", "Description field is empty!");
            return false;
        }

        if (inputquizz_title1.getText().isEmpty()) {
            showAlert("Error", "Title field is empty!");
            return false;
        }

        if (inputquizz_cat1.getValue() == null) {
            showAlert("Error", "Category is not selected!");
            return false;
        }

        if (imagepath1.isEmpty()) {
            showAlert("Error", "Image field is empty!");
            return false;
        }

        return true; // All inputs are valid
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private int currentIndexmod = 0;
    private int backIndexmod = 0;
    public void nextmod() {
        if (backIndexmod == currentIndexmod) {
            if (!questionfieldmod.getText().isEmpty()) {
                if (currentIndexmod >= questionss.size()) {
                    if(t1mod.isSelected())
                    questionss.add(new Question( questionfieldmod.getText(), afieldmod1.getText(), afieldmod2.getText(), afieldmod3.getText(), afieldmod4.getText(), afieldmod1.getText()));
                    else if(t2mod.isSelected())
                        questionss.add(new Question( questionfieldmod.getText(), afieldmod1.getText(), afieldmod2.getText(), afieldmod3.getText(), afieldmod4.getText(), afieldmod2.getText()));
                    else  if(t3mod.isSelected())
                        questionss.add(new Question( questionfieldmod.getText(), afieldmod1.getText(), afieldmod2.getText(), afieldmod3.getText(), afieldmod4.getText(), afieldmod3.getText()));
                    else   if(t4mod.isSelected())
                        questionss.add(new Question( questionfieldmod.getText(), afieldmod1.getText(), afieldmod2.getText(), afieldmod3.getText(), afieldmod4.getText(), afieldmod4.getText()));
else
showAlert("ereur","one answer please");
                }
                currentIndexmod++;
                backIndexmod = currentIndexmod;
                    clearFields();

            }
        } else {
            if(t1mod.isSelected())
            questionss.set(backIndexmod,new Question(questionfieldmod.getText(), afieldmod1.getText(), afieldmod2.getText(), afieldmod3.getText(), afieldmod4.getText(), afieldmod1.getText()));
            else if(t2mod.isSelected())
                questionss.set(backIndexmod,new Question(questionfieldmod.getText(), afieldmod1.getText(), afieldmod2.getText(), afieldmod3.getText(), afieldmod4.getText(), afieldmod2.getText()));
            else  if(t3mod.isSelected())
                questionss.set(backIndexmod,new Question(questionfieldmod.getText(), afieldmod1.getText(), afieldmod2.getText(), afieldmod3.getText(), afieldmod4.getText(), afieldmod3.getText()));
            else  if(t4mod.isSelected())
                questionss.set(backIndexmod,new Question(questionfieldmod.getText(), afieldmod1.getText(), afieldmod2.getText(), afieldmod3.getText(), afieldmod4.getText(), afieldmod4.getText()));
else
                showAlert("ereur","one answer please");
            backIndexmod++;
            clearFields();

            if (backIndexmod < questionss.size()) {
                populateFieldsmod(questionss.get(backIndexmod));


            }

        }


    }

    public void prevmod()  {
        if (backIndexmod > 0) {
            backIndexmod--;

            if (backIndexmod < questionss.size()) {
                populateFieldsmod(questionss.get(backIndexmod));
            }
        }
    }

    public void deletemod()  {
        if (backIndexmod >= 0 && backIndexmod < questionss.size()) {
            questionss.remove(backIndexmod);
            if (backIndexmod < questionss.size()) {
                populateFieldsmod(questionss.get(backIndexmod));
            } else if (backIndexmod > 0) {
                backIndexmod--;
                if (backIndexmod < questionss.size()) {
                    populateFieldsmod(questionss.get(backIndexmod));
                }
            }
        }
    }

    private void populateFieldsmod(Question question) {
        questionfieldmod.setWrapText(true); // Enable text wrapping
        questionfieldmod.setPrefWidth(1039); // Set preferred width to allow wrapping
        afieldmod1.setWrapText(true); // Enable text wrapping
        afieldmod2.setPrefWidth(223);
        afieldmod2.setWrapText(true); // Enable text wrapping
        afieldmod3.setPrefWidth(223);
        afieldmod3.setWrapText(true); // Enable text wrapping
        afieldmod4.setPrefWidth(223);
        afieldmod4.setWrapText(true); // Enable text wrapping
        afieldmod1.setPrefWidth(223);
        afieldmod1.setText(question.getRep1());
        afieldmod2.setText(question.getRep2());
        afieldmod3.setText(question.getRep3());
        afieldmod4.setText(question.getRep4());
        questionfieldmod.setText(question.getQuest());
        if(Objects.equals(question.getRepc(), question.getRep1())){
            t1mod.setSelected(true);
            t2mod.setSelected(false);
            t3mod.setSelected(false);
            t4mod.setSelected(false);
        }
        if(Objects.equals(question.getRepc(), question.getRep2())){
            t2mod.setSelected(true);
            t1mod.setSelected(false);
            t3mod.setSelected(false);
            t4mod.setSelected(false);
        }
        if(Objects.equals(question.getRepc(), question.getRep3())){
            t3mod.setSelected(true);
            t2mod.setSelected(false);
            t1mod.setSelected(false);
            t4mod.setSelected(false);
        }
        if(Objects.equals(question.getRepc(), question.getRep4())){
            t4mod.setSelected(true);
            t2mod.setSelected(false);
            t3mod.setSelected(false);
            t1mod.setSelected(false);
        }
    }





    public void filterall() throws SQLException {
        itemlist.getChildren().clear();
        ServiceQuiz serviceQuiz = new ServiceQuiz();

        // Get selected toggle from category and number filters
        Toggle toggle = toggleGroup.getSelectedToggle();
        Toggle toggle1 = toggleGroupnb.getSelectedToggle();

        // Get search text and sorting criteria
        String sortCriteria = triQuiz.getValue();

        // Initialize list to hold filtered quizzes
        List<Quiz> filteredQuizzes = serviceQuiz.afficher();


        // Apply category filter
        if (toggle != null && toggle instanceof RadioButton) {
            String catFilter = ((RadioButton) toggle).getText();
            filteredQuizzes = filteredQuizzes.stream()
                    .filter(quiz -> quiz.getCategorie().equals(catFilter))
                    .collect(Collectors.toList());

        }

        // Apply number filter
        if (toggle1 != null && toggle1 instanceof RadioButton) {
            String numberFilter = ((RadioButton) toggle1).getText();
            if (numberFilter.equals("<10")) {
                filteredQuizzes = filteredQuizzes.stream()
                        .filter(quiz -> quiz.getNb_quest() < 10)
                        .collect(Collectors.toList());
            } else if (numberFilter.equals(">15")) {
                filteredQuizzes = filteredQuizzes.stream()
                        .filter(quiz -> quiz.getNb_quest() > 15)
                        .collect(Collectors.toList());
            } else {
                filteredQuizzes = filteredQuizzes.stream()
                        .filter(quiz -> quiz.getNb_quest() >= 10 && quiz.getNb_quest() <= 15)
                        .collect(Collectors.toList());
            }

        }

        // Apply sorting criteria
        if (sortCriteria != null) {
            if (sortCriteria.equals("titre")) {
                filteredQuizzes = filteredQuizzes.stream()
                        .sorted(Comparator.comparing(Quiz::getTitre))
                        .collect(Collectors.toList());
            } else if (sortCriteria.equals("question")) {
                filteredQuizzes = filteredQuizzes.stream()
                        .sorted(Comparator.comparing(Quiz::getNb_quest))
                        .collect(Collectors.toList());
            }

        }

        // Apply search filter
        if (!Objects.equals(quizrech_field.getText(), "")) {
            filteredQuizzes = filteredQuizzes.stream()
                    .filter(quiz -> quiz.getTitre().contains(quizrech_field.getText()))
                    .collect(Collectors.toList());

        }

        // Display filtered quizzes
        displayQuizzes(filteredQuizzes);
    }
    private void displayQuizzes(List<Quiz> quizzes) {
        int col = 0;
        int row = 1;
        if (LoginController.user1.getRole()== Role.ADMIN){
        for (Quiz qzz : quizzes) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/quizItem.fxml"));
            try {
                AnchorPane p = fxmlLoader.load();
                ItemQuizzController cntrll = fxmlLoader.getController();
                cntrll.setDATA(qzz, this);
                if (col == 3) {
                    col = 0;
                    row++;
                }
                itemlist.add(p, col++, row);
                GridPane.setMargin(p, new Insets(10));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }}}
        else{
            for (Quiz qzz : quizzes) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/User/quizItem.fxml"));
                try {
                    AnchorPane p = fxmlLoader.load();
                    ItemQuizzController cntrll = fxmlLoader.getController();
                    if(qzz.getUser_id()!=LoginController.user1.getUser_id()){
                    cntrll.mod_btn.setVisible(false);
                    cntrll.sup_btn.setVisible(false);}
                    cntrll.setDATA(qzz, this);
                    if (col == 3) {
                        col = 0;
                        row++;
                    }
                    itemlist.add(p, col++, row);
                    GridPane.setMargin(p, new Insets(10));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }}}
        }


    @FXML
    private void handleFilterAction() {
        try {
            filterall();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void generateButtonClicked() {
        int amount = 10; // Number of questions to fetch
        String cat = inputquizz_cat.getValue();
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        int x1 = 0;
        alert.setHeaderText(null);
        if (cat == null || cat.isEmpty()) {
            alert.setContentText("Category is not selected!");
            alert.showAndWait();
        } else {
            if (cat.equals("General Knowledge"))
                x1 = 9;
            else if (cat.equals("Science & Nature"))
                x1 = 17;
            else if (cat.equals("Science: Computers"))
                x1 = 18;
            else if (cat.equals("Science: Mathematics"))
                x1 = 19;
            else if (cat.equals("History"))
                x1 = 23;
            else if (cat.equals("Art"))
                x1 = 25;
            try {
                System.out.println(x1);
                URL url = new URL("https://opentdb.com/api.php?amount=" + amount + "&category=" + x1 + "&type=multiple");
                System.out.println("https://opentdb.com/api.php?amount=" + amount + "&category=" + x1 + "&type=multiple");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Process the JSON response
                    String jsonResponse = response.toString();
                    parseAndDisplayQuestions(jsonResponse);
                } else {
                    System.out.println("Error: Failed to fetch quiz questions. Response code: " + responseCode);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void parseAndDisplayQuestions(String jsonResponse) {
        if (questions == null)
            questions = new ArrayList<>();
        else {
            // Parse the JSON response and extract questions and answers
            try {
                System.out.println(jsonResponse);
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(jsonResponse).getAsJsonObject();
                JsonArray results = json.getAsJsonArray("results");

                for (int i = 0; i < results.size(); i++) {
                    JsonObject questionObj = results.get(i).getAsJsonObject();

                    // If the question has only true/false answers, skip it

                    String questionText = StringEscapeUtils.unescapeHtml4(questionObj.get("question").getAsString());
                    JsonArray incorrectAnswers = questionObj.getAsJsonArray("incorrect_answers");

                    // Randomize the order of all answers
                    List<String> allAnswers = new ArrayList<>();
                    allAnswers.add(StringEscapeUtils.unescapeHtml4(questionObj.get("correct_answer").getAsString()));
                    for (JsonElement incorrectAnswer : incorrectAnswers) {
                        allAnswers.add(StringEscapeUtils.unescapeHtml4(incorrectAnswer.getAsString()));
                    }
                    Collections.shuffle(allAnswers);

                    // Create a new Question object and add it to the list
                    Question question = new Question();
                    question.setQuest(questionText);

                    // Set answers
                    question.setRep1(allAnswers.get(0));
                    question.setRep2(allAnswers.get(1));
                    question.setRep3(allAnswers.get(2));
                    question.setRep4(allAnswers.get(3));
                    question.setRepc(StringEscapeUtils.unescapeHtml4(questionObj.get("correct_answer").getAsString())); // Set correct answer

                    questions.add(question);
                }

                currentIndex = questions.size() - 1;
                backIndex = questions.size() - 1;
                questionfield.setWrapText(true); // Enable text wrapping
                questionfield.setPrefWidth(1039); // Set preferred width to allow wrapping
                afield1.setWrapText(true); // Enable text wrapping
                afield2.setPrefWidth(223);
                afield2.setWrapText(true); // Enable text wrapping
                afield3.setPrefWidth(223);
                afield3.setWrapText(true); // Enable text wrapping
                afield4.setPrefWidth(223);
                afield4.setWrapText(true); // Enable text wrapping
                afield1.setPrefWidth(223);
                afield1.setText(questions.get(currentIndex).getRep1());
                afield2.setText(questions.get(currentIndex).getRep2());
                afield3.setText(questions.get(currentIndex).getRep3());
                afield4.setText(questions.get(currentIndex).getRep4());
                questionfield.setText(questions.get(currentIndex).getQuest());
                if (Objects.equals(questions.get(currentIndex).getRepc(), questions.get(currentIndex).getRep1())) {
                    t1.setSelected(true);
                    t2.setSelected(false);
                    t3.setSelected(false);
                    t4.setSelected(false);
                } else if (Objects.equals(questions.get(currentIndex).getRepc(), questions.get(currentIndex).getRep2())) {
                    t2.setSelected(true);
                    t1.setSelected(false);
                    t3.setSelected(false);
                    t4.setSelected(false);
                } else if (Objects.equals(questions.get(currentIndex).getRepc(), questions.get(currentIndex).getRep3())) {
                    t3.setSelected(true);
                    t2.setSelected(false);
                    t1.setSelected(false);
                    t4.setSelected(false);
                } else if (Objects.equals(questions.get(currentIndex).getRepc(), questions.get(currentIndex).getRep4())) {
                    t4.setSelected(true);
                    t2.setSelected(false);
                    t3.setSelected(false);
                    t1.setSelected(false);
                }
            } catch (JsonParseException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
private AnchorPane areyou_int;
public void backa_btn_clicked (){
    areyou_int.setVisible(true);
}
    public void sure_btn_clicked (){
    play_int.setVisible(false);
    playy_int.setVisible(false);
        areyou_int.setVisible(false);

        if (countdownTimeline != null) {
            countdownTimeline.stop();
        }
        back_btn_clicked();
    }
    public void no_btn_clicked (){
        areyou_int.setVisible(false);
    }
    }
