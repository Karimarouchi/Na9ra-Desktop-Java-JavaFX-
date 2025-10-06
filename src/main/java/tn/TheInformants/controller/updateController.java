package tn.TheInformants.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.TheInformants.entities.Book;
import tn.TheInformants.services.ServiceBook;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class updateController implements Initializable {
    @FXML
    private ComboBox<String> AvaibilityCombo;
    @FXML
    private Button okButton;
    @FXML
    private ComboBox<String> CategoryCombo;

    @FXML
    private TextField PriceL;

    @FXML
    private TextField TitleL;

    @FXML
    private AnchorPane addanchor;

    @FXML
    private ImageView imgb1;

    @FXML
    private Button imgbbu;

    @FXML
    private TextField imgpathstring1;
    @FXML
    private Label AvaibilityLabel;

    @FXML
    private Label CategorieLabel;
    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }



    public void okbtn_clicked(ActionEvent actionEvent) {
        ServiceBook serviceBook1 = new ServiceBook();
        try {
            // Mettre à jour les propriétés de l'objet book avec les valeurs actuelles des champs de texte et des ComboBox
            book.setNom_liv(TitleL.getText());

            String selectedAvailabilityString = AvaibilityCombo.getSelectionModel().getSelectedItem();
            if (selectedAvailabilityString != null) {
                Book.Disponibilite selectedAvailability = Book.Disponibilite.valueOf(selectedAvailabilityString);
                book.setDisponibilite(selectedAvailability);
            }

            String selectedCategoryString = CategoryCombo.getSelectionModel().getSelectedItem();
            if (selectedCategoryString != null) {
                Book.Categorie selectedCategory = Book.Categorie.valueOf(selectedCategoryString);
                book.setCategorie(selectedCategory);
            }

            book.setPrix_liv(Double.parseDouble(PriceL.getText()));

            // Utiliser l'objet book existant pour effectuer la modification
            serviceBook1.modifier(book);
            System.out.println("Le livre a été modifié avec succès.");

            // Afficher les informations sur le livre mis à jour
            System.out.println("Updated Book: " + book);

            // Afficher les données récupérées après la modification
            System.out.println("Retrieved Data: " + serviceBook1.recuperer());

            // Rafraîchir l'interface utilisateur ou d'autres éléments si nécessaire
            // refreshGridPane(serviceBook1.recuperer());

            // Close the window after successful modification
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du livre : " + e.getMessage());
            e.printStackTrace();
        }}




    @FXML
    void Select(ActionEvent event) {
        String s = AvaibilityCombo.getSelectionModel().getSelectedItem().toString();
        AvaibilityLabel.setText(s);

    }

    @FXML
    void Select_Category(ActionEvent event) {
        String s1 = CategoryCombo.getSelectionModel().getSelectedItem().toString();
        CategorieLabel.setText(s1);

    }
    public void importImageu(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listData = FXCollections.observableArrayList("FICTION", "NON_FICTION", "SCIENCE_FICTION","MYSTERE","AUTRE");
        CategoryCombo.setItems(listData);
        ObservableList<String> listDataa = FXCollections.observableArrayList("DISPO", "NON_DISPO");
        AvaibilityCombo.setItems(listDataa);
    }
}