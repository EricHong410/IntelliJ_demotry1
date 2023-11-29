package com.example.demotry;

import com.example.demotry.Products.Product;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.Map;

public class CheckoutController {
    @FXML
    private TextArea itemsTextArea;
    @FXML
    private TextField firstNameField, lastNameField, addressField, apartmentField, cityField, countryRegionField, stateField, zipCodeField, emailAddressField;
    @FXML
    private TextField cardNumberField, cardholderNameField, expiryDateField, cvcField;
    @FXML
    private Text totalText;
    @FXML
    private Button completeCheckoutButton;

    private Checkout checkout;

    public CheckoutController() {
        // Initialize Checkout with data from Cart
        // this.checkout = new Checkout(cart);
    }

    @FXML
    public void initialize() {

        setupCheckoutButton();
    }

    private void displayCheckoutDetails() {
        StringBuilder itemsText = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : checkout.getItems().entrySet()) {
            itemsText.append(entry.getKey().getName())
                    .append(" - Quantity: ")
                    .append(entry.getValue())
                    .append("\n");
        }
        itemsTextArea.setText(itemsText.toString());
        totalText.setText(String.format("$%.2f", checkout.getTotalPrice()));
    }

    private void setupCheckoutButton() {
        completeCheckoutButton.setOnAction(event -> {
            if (allFieldsFilled() && isValidPaymentDetails()) {
                showAlert("Checkout Completed", "Checkout is completed, thank you for shopping with us!");
                sendEmailReceipt();
            } else {
                showAlert("Error", "Please fill in all fields correctly.");
            }
        });
    }

    private boolean allFieldsFilled() {
        return !isEmpty(firstNameField) &&
                !isEmpty(lastNameField) &&
                !isEmpty(addressField) &&
                !isEmpty(apartmentField) &&
                !isEmpty(cityField) &&
                !isEmpty(countryRegionField) &&
                !isEmpty(stateField) &&
                !isEmpty(zipCodeField) &&
                !isEmpty(emailAddressField) &&
                !isEmpty(cardNumberField) &&
                !isEmpty(cardholderNameField) &&
                !isEmpty(expiryDateField) &&
                !isEmpty(cvcField);
    }

    private boolean isEmpty(TextField field) {
        return field.getText().trim().isEmpty();
    }

    private boolean isValidPaymentDetails() {
        return cardNumberField.getText().equals("0000000000000000") &&
                cardholderNameField.getText().equals("CSUN") &&
                expiryDateField.getText().equals("0000") &&
                cvcField.getText().equals("000");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void sendEmailReceipt() {
        String email = emailAddressField.getText();
        String receipt = itemsTextArea.getText() + "\nTotal: " + totalText.getText();
        // Email sending logic here, e.g., using JavaMail API
    }

    public void setCheckoutData(Checkout checkout) {
        this.checkout = checkout;
        displayCheckoutDetails(); // Update the UI with the new data
    }


}
