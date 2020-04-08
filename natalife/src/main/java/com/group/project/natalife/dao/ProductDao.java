package com.group.project.natalife.dao;

import java.util.List;
import java.util.Map;

import com.group.project.natalife.model.Product;

public interface ProductDao {

	Product findById(int id) throws Exception;

	void addProductToOrder(String orderId, String productId, int quantity);

	List<Product> getProducts(int limit) throws Exception;

	Product insertNewProduct(Map<String, Object> formFields) throws Exception;

}
