package tn.TheInformants.controller;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import tn.TheInformants.entities.user;
import tn.TheInformants.services.serviceuser;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardControllerADMIN implements Initializable {
    @javafx.fxml.FXML
    private GridPane grid;
    @javafx.fxml.FXML
    private ScrollPane scroll;

    private List<user> recDataList = FXCollections.observableArrayList();
    private serviceuser serviceuser = new serviceuser();
    private cardController.MyListener myListener;
    @javafx.fxml.FXML
    private Label HELLO;
    @javafx.fxml.FXML
    private TextField chercher;
    @javafx.fxml.FXML
    private ComboBox sort;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sort.getItems().add("date de nasance");
        sort.getItems().add("name");
        System.out.println("hello");
        recDataList.addAll(serviceuser.afficherUser());

        System.out.println("load data");
        HELLO.setText("Hello ,"+LoginController.user1.getNom());

        int column = 0;
        int row = 3;
        for (int i = 0; i < recDataList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/sharedInterface/card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                cardController item = fxmlLoader.getController();
                item.setData(recDataList.get(i).getUser_id(), recDataList.get(i).getNom(), recDataList.get(i).getPrenom(), recDataList.get(i).getDatenes(), recDataList.get(i).getMail(), recDataList.get(i).getPswd(), recDataList.get(i).getRole(), recDataList.get(i).getImage(),recDataList.get(i).getactif(), myListener);
                //item.setData(covDataList.get(i).getDepart(), covDataList.get(i).getDestination(), covDataList.get(i).getDate_dep());

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException e) {
                System.out.println("problem");
            }
        }
    }

    @javafx.fxml.FXML
    public void chercher_user(ActionEvent actionEvent) {
        String nomRecherche = chercher.getText().trim(); // Trim to remove leading/trailing spaces

        if (nomRecherche.isEmpty()) { // Si le champ de recherche est vide
            afficherTousUtilisateurs();
        } else { // Si un nom est saisi dans le champ de recherche
            for (user utilisateur : recDataList) {
                if (utilisateur.getNom().equalsIgnoreCase(nomRecherche)) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/sharedInterface/card.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();

                        cardController item = fxmlLoader.getController();
                        item.setData(utilisateur.getUser_id(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getDatenes(), utilisateur.getMail(), utilisateur.getPswd(), utilisateur.getRole(), utilisateur.getImage(), utilisateur.getactif(), myListener);
                        grid.getChildren().clear();

                        // Add the card to the grid
                        grid.add(anchorPane, 2, 1);
                        //grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid.setMaxWidth(Region.USE_PREF_SIZE);
                        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid.setMaxHeight(Region.USE_PREF_SIZE);
                        GridPane.setMargin(anchorPane, new Insets(10));
                        break; // Stop searching once the user is found
                        // Add the card to the grid



                    } catch (IOException e) {
                        System.out.println("Problème lors du chargement de la carte de l'utilisateur");
                    }
                }
            }
        }
    }
    private void afficherTousUtilisateurs() {
        grid.getChildren().clear(); // Clear the grid
        int column = 0;
        int row = 3;
        for (user utilisateur : recDataList) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/sharedInterface/card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                cardController item = fxmlLoader.getController();
                item.setData(utilisateur.getUser_id(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getDatenes(), utilisateur.getMail(), utilisateur.getPswd(), utilisateur.getRole(), utilisateur.getImage(), utilisateur.getactif(), myListener);

                grid.add(anchorPane, column++, row);
                if (column == 3) { // Reset column and move to next row after adding 3 cards
                    column =0;
                    row++;
                }
                // grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException e) {
                System.out.println("Problème lors du chargement de la carte de l'utilisateur");
            }
        }
    }

    @javafx.fxml.FXML
    public void SORT(ActionEvent actionEvent) {
        String selectedSort = (String) sort.getValue();
        if (selectedSort != null) {
            switch (selectedSort) {
                case "name":
                    // Trier par nom
                    recDataList.sort(Comparator.comparing(user::getNom));
                    break;
                case "date de naissance":
                    // Trier par date de naissance
                    recDataList.sort(Comparator.comparing(user::getDatenes));
                    break;
                default:
                    // Trier par défaut (par nom)
                    recDataList.sort(Comparator.comparingInt(user::getUser_id));
                    break;
            }
            // Une fois trié, réafficher les utilisateurs
            afficherTousUtilisateurs();
        }
    }
}
