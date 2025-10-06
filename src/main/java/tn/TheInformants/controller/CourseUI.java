package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.TheInformants.entities.cours;
import tn.TheInformants.services.ServiceCours;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class CourseUI implements Initializable {



    @FXML
    private GridPane courseListContainer;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ImageView qrCodeImg;

    @FXML
    private HBox qrCodeImgModal;


    private List<cours> coursObservableList ;
    public CourseUI() throws SQLException {
        ServiceCours serviceCours=ServiceCours.getInstance();
        try {
            coursObservableList = serviceCours.recuperer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //grid = new GridPane();
    }

    private ServiceCours serviceCours = new ServiceCours();

    public void initialize(URL location, ResourceBundle resources) {
       qrCodeImgModal.setVisible(true);

        int col = 0;
        int rows = 1;
        try {

            for (int i = 0; i < coursObservableList.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Admin/CourseItem.fxml"));
                System.out.println("dkhalnaaa");
                VBox courseCard = fxmlLoader.load();
                itemcourseController ItemController = fxmlLoader.getController();
                ItemController.setData(coursObservableList.get(i));

                if (col == 3) {
                    col = 0;
                    ++rows;
                }
                courseListContainer.add(courseCard,col++,rows);
                GridPane.setMargin(courseCard, new Insets(0, 20, 20, 10));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void close_QrCodeModal(MouseEvent event) {
        qrCodeImgModal.setVisible(false);
    }


    public void refresh(ActionEvent actionEvent) {
    }

    public void onChangeProp(ActionEvent actionEvent) {
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

    public void addAnnounce(ActionEvent actionEvent) {
    }

    public void addCourseAdmin(ActionEvent actionEvent) {
    }

  /*  public void addAnnounce(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/Windows/AddAnnounceWindow.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}