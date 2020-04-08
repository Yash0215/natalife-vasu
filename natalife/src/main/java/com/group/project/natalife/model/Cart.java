package com.group.project.natalife.model;

import java.util.Map;

public class Cart {

	private Map<Integer, CartEntry> entries;
	private double totalAmount;
	private int totalElements;

	
	public Map<Integer, CartEntry> getEntries() {
		return entries;
	}
	public void setEntries(Map<Integer, CartEntry> entries) {
		this.entries = entries;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}
	
}
