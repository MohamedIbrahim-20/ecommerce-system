package com.fawry.ecommerce.model;

import java.util.*;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be positive");
            return;
        }
        if (product.getQuantity() < quantity) {
            System.out.println("Error: Not enough stock for " + product.getName());
            return;
        }
        if (product.isExpirable() && product.isExpired()) {
            System.out.println("Error: Product " + product.getName() + " is expired");
            return;
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }
}