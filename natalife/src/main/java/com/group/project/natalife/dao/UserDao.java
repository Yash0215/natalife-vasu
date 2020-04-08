package com.group.project.natalife.dao;

import com.group.project.natalife.model.User;

public interface UserDao {

	/**
	 * Find a user by email
	 * 
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

	/**
	 * Find an ordered product of a user
	 * 
	 * @param email
	 * @param productId
	 * @return int
	 */
	int orderedProductByProductId(String email, int productId);


	/**
	 * 
	 * @param email
	 * @return orderId of user
	 */
	String getUserOrderId(String email);

	/**
	 * 
	 * @param email
	 * @param orderId
	 */
	void createUserOrderId(String email, String orderId);

	User insertNewUser(String fname, String lname, String email, String password) throws Exception;
}
