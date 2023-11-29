package com.example.demotry;

import com.example.demotry.Products.Product;
import java.util.HashMap;
import java.util.Map;

public class CartData {
    private Map<Product, Integer> items;

    public CartData() {
        this.items = new HashMap<>();
    }

    public void addItem(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public double calculateSubtotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}
