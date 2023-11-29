package com.example.demotry;

import com.example.demotry.Products.Product;

import java.util.HashMap;
import java.util.Map;

public class Checkout {
    private Map<Product, Integer> items;
    private double totalPrice;

    public Checkout(CartData cartModel) {
        // Clone the items from the cart model to maintain separation of concerns
        this.items = new HashMap<>(cartModel.getItems());
        this.totalPrice = cartModel.calculateSubtotal();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
