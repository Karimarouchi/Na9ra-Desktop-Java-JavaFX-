package tn.TheInformants.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import tn.TheInformants.entities.Book;
import tn.TheInformants.services.ServiceBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    @FXML
    private TextField searchpanel;
    @FXML
    private Label importpdf;

    @FXML
    private TextField pdfpathstring;

    @FXML
    AnchorPane addanchor;
    private List<Book> itemObservableList;
    @FXML
    private ComboBox<String> AvaibilityCombo;
    @FXML
    private TextField imgpathstring;
    @FXML
    private Label AvaibilityLabel;
private String imagePath1;
    private String pdfPath1;
    @FXML
    private Label CategorieLabel;

    @FXML
    private ComboBox<String> CategoryCombo;
    @FXML
    private GridPane gridPane;

    @FXML
    private TextField IdL;

    @FXML
    private Label IdLabel;

    @FXML
    private TextField PriceL;

    @FXML
    private Label PriceLabel;

    @FXML
    private TextField TitleL;

    @FXML
    private Label TitleLabel;

    @FXML
    private Button addbook;

    @FXML
    private Button bookDashboard;

    @FXML
    private Button clearbook;

    @FXML
    private Button deletebook;

    @FXML
    private Button manageBook;

    @FXML
    private Button modifybook;

    @FXML
    private ScrollPane scroll;
    @FXML
    private ImageView imgb;
    private ServiceBook serviceBook = new ServiceBook();
    Book mainbook;
    public AnchorPane getAddAnchor() {
        return addanchor;
    }
    public AddBookController() throws SQLException {
        ServiceBook serviceBook=ServiceBook.getInstance();
        try {
            itemObservableList = serviceBook.recuperer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        gridPane = new GridPane();
    }
    private static AddBookController instance;

    public static AddBookController getInstance() throws SQLException {
            if(instance==null)
                instance=new AddBookController();
            return instance;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imgb.setOnMouseClicked(event -> importImage());

        int col = 0;
        int rows = 0;
        try {
            for (int i = 0; i < itemObservableList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Admin/itemadminbook.fxml"));
                System.out.println("dkhalnaaa");
                AnchorPane anchorPane = fxmlLoader.load();
                itemadminController ItemadminController = fxmlLoader.getController();
                ItemadminController.setData(itemObservableList.get(i));
                gridPane.add(anchorPane,col,rows++);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ObservableList<String> listData = FXCollections.observableArrayList("FICTION", "NON_FICTION", "SCIENCE_FICTION","MYSTERE","AUTRE");
        CategoryCombo.setItems(listData);
        ObservableList<String> listDataa = FXCollections.observableArrayList("DISPO", "NON_DISPO");
        AvaibilityCombo.setItems(listDataa);

    }






    @FXML
    void AddBookBtn(ActionEvent event) {

        String path;
        String path2;

        imgpathstring.setText(imagePath1.toString());
        pdfpathstring.setText(pdfPath1.toString());

        path=imgpathstring.getText();
        path2=pdfpathstring.getText();

        ServiceBook serviceBook = new ServiceBook();
        Book book = new Book();
        String bookTitle = TitleL.getText().trim();

        if (!bookTitle.isEmpty() && bookTitle.matches(".*[a-zA-Z].*")) {
            book.setNom_liv(bookTitle);
            book.setPrix_liv(Double.parseDouble(PriceL.getText())); // Assuming there's a TextField named priceTF



            String selectedCategoryString = CategoryCombo.getValue();
            String selectedAvailabilityString = AvaibilityCombo.getValue();

            // Check if a category is selected
            if (selectedCategoryString != null) {
                // Convert the String to Categorie enum
                try {
                    Book.Categorie selectedCategory = Book.Categorie.valueOf(selectedCategoryString);
                    book.setCategorie(selectedCategory);

                    // Check if an availability is selected
                    if (selectedAvailabilityString != null) {
                        // Convert the String to Disponibilite enum
                        try {
                            Book.Disponibilite selectedAvailability = Book.Disponibilite.valueOf(selectedAvailabilityString);
                            book.setDisponibilite(selectedAvailability);

                            try {
                               Book book2 =new Book(TitleL.getText(),selectedAvailability,selectedCategory,Double.parseDouble(PriceL.getText()),path,path2);
                                serviceBook.ajouter(book2);
                                refreshGridPane();
                                showconf("Success", "Book Added");

                                // Refresh the list of books after successful addition

                            } catch (SQLException e) {
                                showAlert("Error", e.getMessage());
                            }
                        } catch (IllegalArgumentException e) {
                            // Show an alert if the availability is not a valid enum constant
                            showAlert("Error", "Invalid availability: " + selectedAvailabilityString);
                        }
                    } else {
                        // Show an alert if no availability is selected
                        showAlert("Error", "Please select an availability");
                    }
                } catch (IllegalArgumentException e) {
                    // Show an alert if the category is not a valid enum constant
                    showAlert("Error", "Invalid category: " + selectedCategoryString);
                }
            } else {
                // Show an alert if no category is selected
                showAlert("Error", "Please select a category");
            }
        } else {
            // Show an alert if the book title is empty or doesn't contain letters
            showAlert("Error", "The book title cannot be empty and must contain letters.");
        }
    }


// Method to show alerts
        private void showAlert(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(content);
            alert.showAndWait();
        }
    private void showconf(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }



    @FXML
    void BookAdminDashboardBtn(ActionEvent event) {

    }

    @FXML
    void ClearBookBtn(ActionEvent event) {

    }

    @FXML
    void DeleteBookBtn(ActionEvent event) {


    }

    @FXML
    void ManageBooksBtn(ActionEvent event) {

    }

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



    @FXML

    public void importImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                Image image = new Image(fileInputStream);
                imgb.setImage(image);
                imagePath1 = selectedFile.toURI().toString();
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Failed to load image: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }


    public void updatebtnanchor(ActionEvent actionEvent) {

        ServiceBook serviceBook1 = new ServiceBook();
        Book book = new Book();

        // Récupérer les données du cours à partir de vos champs de texte ou d'autres contrôles
        book.setNom_liv(TitleL.getText());

        // Convertir la disponibilité sélectionnée en enum
        String selectedAvailabilityString = AvaibilityCombo.getSelectionModel().getSelectedItem();
        if (selectedAvailabilityString != null) {
            Book.Disponibilite selectedAvailability = Book.Disponibilite.valueOf(selectedAvailabilityString);
            book.setDisponibilite(selectedAvailability);
        }

        // Convertir la catégorie sélectionnée en enum
        String selectedCategoryString = CategoryCombo.getSelectionModel().getSelectedItem();
        if (selectedCategoryString != null) {
            Book.Categorie selectedCategory = Book.Categorie.valueOf(selectedCategoryString);
            book.setCategorie(selectedCategory);
        }
        book.setPrix_liv(Double.parseDouble(PriceL.getText()));

        try {
            serviceBook1.modifier(book);
            System.out.println("Le cours a été modifié avec succès.");

            // Print debug information about the updated book
            System.out.println("Updated Book: " + book);

            // Verify that the data is updated
            System.out.println("Retrieved Data: " + serviceBook1.recuperer());

            // Rafraîchir le contenu du GridPane après la modification réussie
            //refreshGridPane(serviceBook1.recuperer());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du cours : " + e.getMessage());
            e.printStackTrace();
        }



    }
    void refreshGridPane() throws SQLException {
        // Assuming your grid pane is named BookListView
        gridPane.getChildren().clear(); // Clear existing items

        // Reload the books and populate the grid pane
        List<Book> allBooks = serviceBook.recuperer(); // Replace this with your actual method to get all books
        int col = 0;
        int rows = 0;

        try {
            for (int i = 0; i < allBooks.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/itemadminbook.fxml"));
                System.out.println("Loading itembook.fxml");
                AnchorPane anchorPane = fxmlLoader.load();
                itemadminController ItemController = fxmlLoader.getController();
                ItemController.setData(allBooks.get(i));
                gridPane.add(anchorPane, col, rows++);


            }
        } catch (IOException e) {
            System.err.println("Error loading itembook.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Book book;

    @FXML
    public void importPDF(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);

               String pdfContent = selectedFile.toURI().toString();
                pdfPath1 = selectedFile.toURI().toString();

                Book book = new Book();
                book.setPdfPath(pdfContent);
                // You can store or use pdfContent as needed

                showAlert("Success", "PDF File Imported");
                // If you need to use the 'book' object further, you can do so here.
                // You may want to store it as a property in your controller.

            } catch (IOException e) {
                showAlert("Error", "Failed to read PDF file: " + e.getMessage());
            }
        }
    }


    public void searchbook_clicked(KeyEvent keyEvent) {
        // 1. Retrieve the text from the searchpanel TextField
        String searchText = searchpanel.getText().toLowerCase().trim();

        // 2. Clear the existing content in the gridPane
        gridPane.getChildren().clear();

        // 3. Iterate through the list of books to find matches
        int col = 0;
        int rows = 0;
        for (Book book : itemObservableList) {
            if (book.getNom_liv().toLowerCase().contains(searchText))  {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/Admin/itemadminbook.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    itemadminController itemAdminController = fxmlLoader.getController();
                    itemAdminController.setData(book);
                    gridPane.add(anchorPane, col, rows++);
                } catch (IOException e) {
                    System.out.println("Error loading FXML: " + e.getMessage());
                }
            }
        }
    }
}





