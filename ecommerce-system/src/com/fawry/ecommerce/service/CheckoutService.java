package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.*;
import java.util.*;

public class CheckoutService {
    private final ShippingService shippingService;
    private static final double BASE_SHIPPING_RATE = 10.0; // $10 per kg
    private static final double MINIMUM_SHIPPING_FEE = 5.0; // Minimum fee for any shipment

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    // Calculate dynamic shipping fee based on total weight in kilograms
    private double calculateShippingFee(List<ShippableItem> shippableItems) {
        if (shippableItems.isEmpty()) {
            return 0.0;
        }
        double totalWeightKg = 0;
        for (ShippableItem item : shippableItems) {
            totalWeightKg += item.getWeight() / 1000; // Convert grams to kilograms
        }
        double calculatedFee = totalWeightKg * BASE_SHIPPING_RATE;
        return Math.max(calculatedFee, MINIMUM_SHIPPING_FEE); // Ensure minimum fee
    }

    public void checkout(Customer customer, Cart cart) {
        Map<Product, Integer> items = cart.getItems();
        if (items.isEmpty()) {
            System.out.println("Error: Cart is empty");
            return;
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            if (p.isExpirable() && p.isExpired()) {
                System.out.println("Error: Product " + p.getName() + " is expired");
                return;
            }
            if (p.getQuantity() < qty) {
                System.out.println("Error: Product " + p.getName() + " is out of stock");
                return;
            }
        }

        double subtotal = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            subtotal += p.getPrice() * qty;
        }

        // Prepare shippable items and calculate dynamic shipping fee
        List<ShippableItem> shippableItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            if (p.isShippable()) {
                int qty = entry.getValue();
                for (int i = 0; i < qty; i++) {
                    shippableItems.add(new ShippedItem(p.getName(), p.getWeight()));
                }
            }
        }
        double shippingFee = calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            System.out.println("Error: Insufficient balance");
            return;
        }

        customer.deduct(totalAmount);
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceQuantity(entry.getValue());
        }

        if (!shippableItems.isEmpty()) {
            shippingService.ship(shippableItems);
        }

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            System.out.println(qty + "x " + p.getName() + " " + (p.getPrice() * qty));
        }
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + Math.ceil(shippingFee));
        System.out.println("Amount " + totalAmount);
        System.out.println("Customer balance: " + customer.getBalance());
    }
}