package tn.TheInformants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.TheInformants.entities.Book;
import tn.TheInformants.services.ServiceBook;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    @FXML
    private Label HomeTotalBooks;
    @FXML
    private Label totalIncomeLabel;
    @FXML
    private Button AdddBookDashboard;

    @FXML
    private Label TotalBooks;

    @FXML
    private Button bookDashboard;

    @FXML
    private Label totalIncome;

    @FXML
    private Label totalUsers;
    Book book;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Your existing initialization code...

        // Call the method to update the total books count
        updateTotalBooksCount();
    }
    private ServiceBook serviceBook = new ServiceBook();
    // Add this method to your controller
    private void updateTotalBooksCount() {
        try {
            // Call the service method to get the count of books from the database
            int totalBooksCount = serviceBook.getTotalBooksCount();

            // Set the count to the HomeTotalBooks label
            HomeTotalBooks.setText("" + totalBooksCount);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }


    private void updateTotalIncome() {
        try {
            double totalIncome = serviceBook.getTotalIncome();
            totalIncomeLabel.setText("Total Income: " + totalIncome + " TND");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
        }
    }


    @FXML
    void AddAdminDashboardBtn(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Admin/Book.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Book");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    @FXML
    void BookAdminDashboardBtn(ActionEvent event) {


    }



}
