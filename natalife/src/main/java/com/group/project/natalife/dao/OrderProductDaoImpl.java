package com.group.project.natalife.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.group.project.natalife.model.Order;
import com.group.project.natalife.model.Product;

@Repository
public class OrderProductDaoImpl implements OrderProductDao {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public int insertNewOrderProductMapping(Order order, Product product, int quantity) throws Exception {
		System.out.println("Inserting order and product mapping");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order_id", order.getId());
		params.put("product_id", product.getId());
		params.put("quantity", quantity);
		String sql = "INSERT INTO order_product(order_id, product_id, quantity) "
				+ "VALUES(:order_id,:product_id,:quantity)";
		int rowCount;
		try {
			rowCount = namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("Order-Product can't be created.");
		}
		return rowCount;
	}
	
}
