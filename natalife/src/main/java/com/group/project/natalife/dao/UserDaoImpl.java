package com.group.project.natalife.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.group.project.natalife.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	
	
	@Override
	public User insertNewUser(String fname, String lname, String email, String password) throws Exception {
		System.out.println("Inserting user");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fname", fname);
		params.put("lname", lname);		
		params.put("email", email);
		params.put("password", password);

		String sql = "INSERT INTO users(fname,lname,email,password) VALUES(:fname,:lname,:email,:password)";

		int rowCount;
		try {
			rowCount = namedParameterJdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			System.out.println("Getting DuplicateKeyException " + ExceptionUtils.getStackTrace(e));
			throw new DuplicateKeyException("User Already Exist.");
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("User cant be created.");
		}
		if (rowCount == 1) {
			return findByEmail(email);
		}
		return null;
	}

	@Override
	public User findByEmail(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		String sql = "SELECT * FROM users WHERE email=:email";
		User user = null;
		try {
			user = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Getting EmptyResultDataAccessException " + ExceptionUtils.getStackTrace(e));
			throw new EmptyResultDataAccessException("User not found.", 0);
		}
		System.out.println("User : " + user);
		return user;
	}

	@Override
	public int orderedProductByProductId(String email, int productId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		params.put("productId", productId);

		String sql = "INSERT INTO orders VALUES(:email, :productId)";

		return namedParameterJdbcTemplate.update(sql, params);
	}

//	@Override
//	public List<Product> findOrderedProducts(String email) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("email", email);
//
//		String sql = "SELECT * FROM orders o, products p WHERE o.email=:email AND o.product_id=p.product_id;";
//
//		List<Product> productResults = namedParameterJdbcTemplate.query(sql, params, new ProductMapper());
//
//		return productResults;
//	}

	@Override
	public String getUserOrderId(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);

		String sql = "SELECT order_id FROM user_order WHERE email=:email";
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, params, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void createUserOrderId(String email, String orderId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		params.put("email", email);

		String sqlInsertOrder = "INSERT INTO orders VALUES(:orderId, :email)";
		namedParameterJdbcTemplate.update(sqlInsertOrder, params);

		String sqlInsertUserOrder = "INSERT INTO user_order VALUES(:email, :orderId)";
		namedParameterJdbcTemplate.update(sqlInsertUserOrder, params);
	}

	/**
	 * UserMapper implements org.springframework.jdbc.core.RowMapper<T> interface is
	 * used by JdbcTemplate for mapping rows of a ResultSet on a per-row basis
	 * Reference: https://www.tutorialspoint.com/springjdbc/springjdbc_rowmapper.htm
	 * 
	 * @author chau
	 *
	 */
	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setFname(rs.getString("fname"));
			user.setLname(rs.getString("lname"));
			user.setIsadmin(rs.getBoolean("isadmin"));
			return user;
		}
	}
}
