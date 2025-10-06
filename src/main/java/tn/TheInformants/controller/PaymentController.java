package tn.TheInformants.controller;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.TheInformants.entities.Panier;
import tn.TheInformants.entities.Payment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class PaymentController{
    Panier panier;
    private Payment payment;


    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField expiryDateField;

    @FXML
    private TextField cvvField;
    // Regular expression pattern for validating credit card numbers
    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile("^\\d{16}$");

    // Other methods in your PaymentController class...

    // Validate credit card number
    private boolean isValidCreditCardNumber(String creditCardNumber) {
        return CREDIT_CARD_PATTERN.matcher(creditCardNumber).matches();}
    @FXML
    private Label totalPriceLabel;
    // Add this setter method
    public void setPanier(Panier panier) {
        this.panier = panier;
    }


    public void handlePayment() {
        // Set your secret key
        Stripe.apiKey = "";
        try {
            // Get amount from the text field
            double amount = panier.getTotal_price() * 100;
            long amountliv = (long) amount;

            System.out.println("hedhi l panier"+panier);

            // Create a PaymentIntent
            PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                    .setCurrency("usd")
                    .setAmount(amountliv)
                    .build();

//            PaymentIntent intent = PaymentIntent.create(createParams);

            String cardNumber = cardNumberField.getText();

            // Check if the credit card number is valid
            if (!isValidCreditCardNumber(cardNumber)) {
                showError("Invalid Credit Card Number", "Please enter a valid credit card number.");
                return;
            }else{ showconf("Payment Confirmation", "Thank you for buying our book !,your book will be downloaded shortly");
                if (panier != null && panier.getPdfPath() != null ) {
                    downloadPDF(panier.getPdfPath(), panier.getNom_liv());
                    Stage stage = (Stage) cardNumberField.getScene().getWindow();
                    stage.close();
                } else {
                    // Handle the case where no PDF path is available
                    System.out.println("No PDF available for download.");
                    Stage stage = (Stage) cardNumberField.getScene().getWindow();
                    stage.close();
                }}

        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Handle errors
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void downloadPDF(String pdfFilePath, String fileName) {
        // Extract the local file path from the URL-like path
        URI uri = URI.create(pdfFilePath);
        String localFilePath = Paths.get(uri).toString();

        // Specify the directory where you want to save the PDF
        String directory = "C:\\Users\\rannn\\Downloads";

        // Construct the full file path
        String filePath = directory + "\\" + fileName + ".pdf";

        try {
            // Read content from the PDF file
            byte[] pdfContent = Files.readAllBytes(Paths.get(localFilePath));

            // Write content to the specified file path
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(pdfContent);
            fos.close();

            System.out.println("PDF downloaded successfully to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error downloading PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void showconf(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }




}
