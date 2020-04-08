package com.group.project.natalife.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.group.project.natalife.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private static final class ProductMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setDescription(rs.getString("description"));
			product.setImage(rs.getString("image"));
			return product;
		}
	}
	
	@Override
	public Product findById(int id) throws Exception {
		System.out.println("Inside findById");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String sql = "SELECT * FROM products WHERE id=:id";
		try {
			Product product = namedParameterJdbcTemplate.queryForObject(sql, params, new ProductMapper());
			System.out.println("Fetched Product : " + product);
			return product;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Getting EmptyResultDataAccessException " + ExceptionUtils.getStackTrace(e));
			throw new EmptyResultDataAccessException("No Product found.", 0);
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("Products can't be fetched.");
		}
	}

	@Override
	public void addProductToOrder(String orderId, String productId, int quantity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		params.put("productId", productId);
		params.put("quantity", quantity);
		try {
			String sqlInsertOrder = "INSERT INTO order_product VALUES(:orderId, :productId, :quantity)";
			namedParameterJdbcTemplate.update(sqlInsertOrder, params);
		} catch (DuplicateKeyException ex) {
			String sqlUpdateOrder = "UPDATE order_product SET quantity= :quantity WHERE order_id = :orderId AND product_id = :productId";
			namedParameterJdbcTemplate.update(sqlUpdateOrder, params);
		}

	}

	@Override
	public List<Product> getProducts(int limit) throws Exception {
		System.out.println("Getting products");
		String sql = "SELECT * FROM products";
		if(limit > 0) {
			sql += " LIMIT " + limit;
		}
		try {
			List<Product> products =  namedParameterJdbcTemplate.query(sql, new ProductMapper());
			System.out.println("Total products fetched : " + products.size());
			return products;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Getting EmptyResultDataAccessException " + ExceptionUtils.getStackTrace(e));
			throw new EmptyResultDataAccessException("Products not found.", 0);
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("Products can't be fetched.");
		}
	}

	@Override
	public Product insertNewProduct(Map<String, Object> formFields) throws Exception {
		System.out.println("Inserting product");
		
		String sql = "INSERT INTO products(name,description,price,image) "
				+ "VALUES(:name,:description,:price,:image)";
		int rowCount;
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", formFields.get("name"))
				.addValue("description", formFields.get("description"))
				.addValue("price", formFields.get("price"))
				.addValue("image", formFields.get("image"));
		try {
			rowCount = namedParameterJdbcTemplate.update(sql, param, generatedKeyHolder);
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("Product can't be inserted.");
		}
		if (rowCount == 1) {
			return findById(generatedKeyHolder.getKey().intValue());
		}
		return null;
	}

}
