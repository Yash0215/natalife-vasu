package com.group.project.natalife.dao;

import com.group.project.natalife.model.Order;

public interface OrderDao {

	public Order insertNewOrder(String email, String address, String mobile, double totalAmount) throws Exception;

	public Order findById(int id);

}
