package com.group.project.natalife.model;

public class Order {

	private int id;
	private String email;
	private String address;
	private String mobile;
	private double totalAmount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", email=" + email + ", address=" + address + ", mobile=" + mobile + ", totalAmount="
				+ totalAmount + "]";
	}
	
}
