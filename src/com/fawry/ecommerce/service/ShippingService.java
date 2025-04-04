package com.fawry.ecommerce.service;

import com.fawry.ecommerce.model.ShippableItem;
import java.util.*;

public class ShippingService {
    public void ship(List<ShippableItem> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");
        Map<String, List<ShippableItem>> grouped = new HashMap<>();
        for (ShippableItem item : items) {
            grouped.computeIfAbsent(item.getName(), k -> new ArrayList<>()).add(item);
        }
        double totalWeightKg = 0;
        for (Map.Entry<String, List<ShippableItem>> entry : grouped.entrySet()) {
            String name = entry.getKey();
            List<ShippableItem> itemList = entry.getValue();
            int qty = itemList.size();
            double totalWeight = 0;
            for (ShippableItem item : itemList) {
                totalWeight += item.getWeight();
            }
            System.out.println(qty + "x " + name + " " + totalWeight + "g");
            totalWeightKg += totalWeight / 1000;
        }
        System.out.println("Total package weight " + totalWeightKg + "kg");
    }
}