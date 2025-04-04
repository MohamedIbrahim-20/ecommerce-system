package com.fawry.ecommerce.model;

import java.util.Date;

public class Product {
    private final String name;
    private final double price;
    private int quantity;
    private final boolean isShippable;
    private final double weight; // in grams, relevant if isShippable
    private final boolean isExpirable;
    private final Date expirationDate; // Field for expiration date

    // Constructor
    public Product(String name, double price, int quantity, boolean isShippable, double weight,
                   boolean isExpirable, Date expirationDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isShippable = isShippable;
        this.weight = isShippable ? weight : 0; // Set weight to 0 if not shippable
        this.isExpirable = isExpirable;
        this.expirationDate = isExpirable ? expirationDate : null; // Null if not expirable
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isShippable() { return isShippable; }
    public double getWeight() { return weight; }
    public boolean isExpirable() { return isExpirable; }

    // Method to check if the product is expired
    public boolean isExpired() {
        if (!isExpirable) {
            return false; // Non-expirable products never expire
        }
        Date currentDate = new Date(); // Get the current date
        return currentDate.after(expirationDate); // True if current date is after expiration date
    }

    // Method to reduce quantity (e.g., when sold)
    public void reduceQuantity(int qty) { this.quantity -= qty; }
}
