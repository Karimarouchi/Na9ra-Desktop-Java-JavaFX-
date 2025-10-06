package tn.TheInformants.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.TheInformants.entities.cours;
import tn.TheInformants.services.ServiceCours;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;


public class CoursController implements Initializable  {
    @FXML
    private Label DescriptionLabel;

    @FXML
    private TextField DescriptionTF;

    @FXML
    private Label IdLabel;

    @FXML
    private TextField IdTF;

    @FXML
    private Label TitleLabel;

    @FXML
    private Button addClaim;

    @FXML
    private Button addCourse_Btn;

    @FXML
    private Button addCourse_addBtn;

    @FXML
    private Button addCourse_clearBtn;

    @FXML
    private Button addCourse_deleteBtn;

    @FXML
    private Button addCourse_updateBtn;

    @FXML
    private Label descriptionLabel;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button homeBtn;

    @FXML
    private Label idLabel;

    @FXML
    private Label levelLabel;
    @FXML
    private TextField LinkTF;
    @FXML
    private Label LevelLabel;

    @FXML
    private ComboBox<String> niveauComboBox;

    @FXML
    private Label niveauLabel;

    @FXML
    private TextField search;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField titleTF;
    @FXML
    private ImageView imgb;

    @FXML
    private Button imgbb;
    private  String imagePath1;
    @FXML
    private TextField imgpathstring;

    private File selectedImageFile;
    private String imageName;

    @FXML
    private GridPane coursesListContainer;

    @FXML
    private AnchorPane content_area;

    @FXML
    private ImageView qrCodeImg;

    @FXML
    private HBox qrCodeImgModal;

    private String searchValue = null;

    @FXML
    void Select(ActionEvent event) {
        String s = niveauComboBox.getSelectionModel().getSelectedItem().toString();
        levelLabel.setText(s);


    }
    private ServiceCours serviceCours = new ServiceCours();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        qrCodeImgModal.setVisible(false);
        imgb.setOnMouseClicked(event -> {
            try {
                importImage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            this.setCourseGridPaneList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //imgpathstring.setVisible(false);


        ObservableList<String> listData = FXCollections.observableArrayList("Beginner", "Intermediate", "Advanced");
        niveauComboBox.setItems(listData);



    }
    private void setCourseGridPaneList() throws SQLException {
        // Instancier le service de cours
        ServiceCours coursService = new ServiceCours();
        // List<cours> listeCours = coursService.recuperer();

         List<cours> courses = null;

        // Récupérer  les cours
        if (searchValue == null) {
            courses = coursService.recuperer();
        }else {

            courses = coursService.search(searchValue);
        }


        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).toString());

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Admin/adminCourseItem.fxml"));
                HBox oneCourseCard = fxmlLoader.load();
                AdminCourseItemController courseCardController = fxmlLoader.getController();
                courseCardController.setCourseData(courses.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                coursesListContainer.add(oneCourseCard, column++, row);
                 GridPane.setMargin(oneCourseCard, new Insets(10));
                //GridPane.setMargin(oneCourseCard, new Insets(0, 10, 25, 10));
                oneCourseCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @FXML
    void AddBtn(ActionEvent event) {
        //String i = "/gui/resources/" + imagePath1;


            ServiceCours serviceCours = new ServiceCours();
            cours cours = new cours();

            String titre = titleTF.getText().trim(); // Récupérer le titre du cours en supprimant les espaces inutiles
            String description = DescriptionTF.getText().trim(); // Récupérer la description du cours en supprimant les espaces inutiles
        cours.setLink(LinkTF.getText());

            // Vérifier si le titre n'est pas vide et contient des lettres
            if (!titre.isEmpty() && titre.matches(".*[a-zA-Z].*")) {
                cours.setTitre(titre);

                // Vérifier si la description n'est pas vide et contient des lettres
                if (!description.isEmpty()) {
                    cours.setDescription(description);

                    // Récupérer la valeur sélectionnée du ComboBox niveau
                    String selectedLevel = niveauComboBox.getSelectionModel().getSelectedItem();

                    // Vérifier si une valeur est sélectionnée
                    if (selectedLevel != null) {
                        cours.setNiveau(selectedLevel); // Attribuer le niveau sélectionné
                        try {
                            cours.setImagePath(imageName);
                            serviceCours.ajouter(cours);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Succes");
                            alert.setContentText("Cours Ajoute");
                            alert.showAndWait();

                            // Rafraîchir la liste des cours après l'ajout réussi
                        //    coursesListContainer.getChildren().clear();
                        //    this.setCourseGridPaneList();

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Admin/CoursUI.fxml"));
                            try {
                                Parent root = loader.load();
                                // Accéder à la pane content_area depuis le controller de

                                AnchorPane contentArea = (AnchorPane ) ((Node) event.getSource()).getScene().lookup("#content_area");

                                // Vider la pane et afficher le contenu de ProductsList.fxml
                                contentArea.getChildren().clear();
                                contentArea.getChildren().add(root);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            //refreshCourseList();


                        } catch (SQLException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setContentText(e.getMessage());
                            alert.showAndWait();
                        }
                    } else {
                        // Afficher une alerte si aucun niveau n'est sélectionné
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setContentText("Veuillez sélectionner un niveau");
                        alert.showAndWait();
                    }
                } else {
                    // Afficher une alerte si la description est vide ou ne contient pas de lettres
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("La description du cours ne peut pas être vide et doit contenir des lettres.");
                    alert.showAndWait();
                }
            } else {
                // Afficher une alerte si le titre est vide ou ne contient pas de lettres
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Le titre du cours ne peut pas être vide et doit contenir des lettres.");
                alert.showAndWait();
            }
        }









   @FXML
    void UpdateBtn(ActionEvent event) {
        ServiceCours serviceCours = new ServiceCours();
        cours cours = new cours();
        // Récupérer les données du cours à partir de vos champs de texte ou d'autres contrôles
        cours.setId(cours.getIdCourse() );
        cours.setTitre(titleTF.getText());
        cours.setDescription(DescriptionTF.getText());
        cours.setNiveau(niveauComboBox.getSelectionModel().getSelectedItem());

        try {
            serviceCours.modifier(cours);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Admin/CoursUI.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de

                AnchorPane contentArea = (AnchorPane ) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Afficher un message de succès ou effectuer d'autres actions nécessaires après la mise à jour réussie
            System.out.println("Le cours a été modifié avec succès.");

            // Rafraîchir le contenu du GridPane après la modification réussie
      //      refreshGridPane(serviceCours.recuperer());
        } catch (SQLException e) {
            // Afficher un message d'erreur en cas d'échec de la mise à jour
            System.out.println("Erreur lors de la modification du cours : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void ClearBtn(ActionEvent actionEvent) {
    }


    public void addCourseAdmin(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/CoursUI.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Course");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addClaimAdmin(ActionEvent actionEvent) { try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/DiscussionUI.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Titre de votre fenêtre");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    @FXML
    private void importImage() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imgb.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imgb.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));

            imageName = uniqueID + extension;

            // Enregistrer l'image dans le dossier "uploads"

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "uploads",  imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);


        }
    }


    @FXML
    void close_QrCodeModal(MouseEvent event) {
        qrCodeImgModal.setVisible(false);
    }


    @FXML
    void searchCourse(KeyEvent event) throws IOException, SQLException {

        searchValue = ((TextField) event.getSource()).getText();
        coursesListContainer.getChildren().clear();
        this.setCourseGridPaneList();
    }
}



