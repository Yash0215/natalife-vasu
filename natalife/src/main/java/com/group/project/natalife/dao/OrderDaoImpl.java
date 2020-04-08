package com.group.project.natalife.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.group.project.natalife.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	private static final class OrderMapper implements RowMapper<Order> {
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setId(rs.getInt("id"));
			order.setEmail(rs.getString("email"));
			order.setAddress(rs.getString("address"));
			order.setMobile(rs.getString("mobile"));
			order.setTotalAmount(rs.getDouble("totalamount"));
			return order;
		}
	}

	@Override
	public Order insertNewOrder(String email, String address, String mobile, double totalAmount) throws Exception {
		System.out.println("Inserting order");
		
		String sql = "INSERT INTO orders(email,address,mobile,totalAmount) "
				+ "VALUES(:email,:address,:mobile,:totalAmount)";
		int rowCount;
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email)
				.addValue("address", address).addValue("mobile", mobile).addValue("totalAmount", totalAmount);
		try {
			rowCount = namedParameterJdbcTemplate.update(sql, param, generatedKeyHolder);
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("Order can't be created.");
		}
		if (rowCount == 1) {
			return findById(generatedKeyHolder.getKey().intValue());
		}
		return null;
	}
	
	@Override
	public Order findById(int id) {
		System.out.println("Inside findById, id : " + id);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String sql = "SELECT * FROM orders WHERE id=:id";
		Order order = null;
		try {
			order = namedParameterJdbcTemplate.queryForObject(sql, params, new OrderMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Getting EmptyResultDataAccessException " + ExceptionUtils.getStackTrace(e));
			throw new EmptyResultDataAccessException("Order not found.", 0);
		}
		System.out.println("Order : " + order);
		return order;
	}
	
}
