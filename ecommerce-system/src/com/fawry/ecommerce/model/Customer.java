package com.fawry.ecommerce.model;

public class Customer {
    private double balance;

    public Customer(double balance) {
        this.balance = balance;
    }

    public double getBalance() { return balance; }
    public void deduct(double amount) { this.balance -= amount; }
}
