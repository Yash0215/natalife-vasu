package com.group.project.natalife.dao;

import com.group.project.natalife.model.Order;
import com.group.project.natalife.model.Product;

public interface OrderProductDao {

	int insertNewOrderProductMapping(Order order, Product product, int quantity) throws Exception;

}
