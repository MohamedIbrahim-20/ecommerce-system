package com.fawry.ecommerce.model;

public class ShippedItem implements ShippableItem {
	private final String name;
	private final double weight;

	public ShippedItem(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getWeight() {
		return weight;
	}
}