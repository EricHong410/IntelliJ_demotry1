package com.example.demotry;

import com.example.demotry.Products.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {
    @FXML
    private TableView<Product> cartTableView; // Updated to TableView
    @FXML
    private TableColumn<Product, String> cart_col_item; // Assuming this is for item names
    @FXML
    private TableColumn<Product, Integer> cart_col_amount; // Assuming this is for quantities
    @FXML
    private TableColumn<Product, Double> cart_col_price; // Assuming this is for item prices
    @FXML
    private Label subtotalLabel;

    private CartData cartData;

    public CartController() {
        this.cartData = new CartData();
    }

    public void addItemToCart(Product product) {
        cartData.addItem(product);
        updateCartView();
    }


    private void updateCartView() {
        cartTableView.setItems(FXCollections.observableArrayList(cartData.getItems().keySet()));

        // Assuming Product class has a method getName() for item names
        cart_col_item.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        // Assuming Product class has a method getQuantity() that returns Integer for quantities
        cart_col_amount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQuantity()));

        // Assuming Product class has a method getPrice() that returns Double for prices
        cart_col_price.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrice()));

        updateSubtotal();
    }


    private void updateSubtotal() {
        double subtotal = cartData.calculateSubtotal();
        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
    }

    @FXML
    public void initialize() {

    }

    public void switchToCheckoutScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Checkout.fxml"));
            Parent checkoutRoot = loader.load();

            CheckoutController checkoutController = loader.getController();
            // Assuming you have a mechanism to create or obtain a Checkout object
            Checkout checkout = new Checkout(cartData);
            checkoutController.setCheckoutData(checkout); // Set the checkout data here

            Scene checkoutScene = new Scene(checkoutRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(checkoutScene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
