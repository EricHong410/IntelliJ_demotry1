package com.example.demotry.Products;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;

public class Product {
    private int productID;
    private StringProperty name;
    private DoubleProperty price;
    private String image;
    private IntegerProperty quantity; // New field for quantity

    public Product(int productID, String name, double price, String image) {
        this.productID = productID;
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.image = image;
        this.quantity = new SimpleIntegerProperty(1); // Default quantity to 1, adjust as needed
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    public String getImage() {
        return image;
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    // JavaFX property getters
    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    // Other methods and setters if necessary
}
