package com.group.project.natalife.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

import com.group.project.natalife.model.Recipe;

@Repository
public class RecipeDaoImpl implements RecipeDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	private static final class RecipeMapper implements RowMapper<Recipe> {
		public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
			Recipe recipe = new Recipe();
			recipe.setId(rs.getInt("id"));
			recipe.setName(rs.getString("name"));
			recipe.setDescription(rs.getString("description"));
			recipe.setProduct_id(rs.getString("product_id"));
			return recipe;
		}
	}
	
	@Override
	public List<Recipe> findByProduct(int product_id) throws Exception {
		System.out.println("Inside findByProduct");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("product_id", product_id);
		String sql = "SELECT * FROM recipes WHERE product_id=:product_id";
		try {
			List<Recipe> recipes = namedParameterJdbcTemplate.query(sql, params, new RecipeMapper());
			System.out.println("Fetched Recipes : " + recipes.size());
			return recipes;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Getting EmptyResultDataAccessException " + ExceptionUtils.getStackTrace(e));
			throw new EmptyResultDataAccessException("No Product found.", 0);
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("Products can't be fetched.");
		}
	}

	@Override
	public Recipe insertNewRecipe(int product_id, String name, String description) throws Exception {
		System.out.println("Inserting recipe");
		
		String sql = "INSERT INTO recipes(name, description, product_id) "
				+ "VALUES(:name,:description,:product_id)";
		int rowCount;
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", name)
				.addValue("description", description)
				.addValue("product_id", product_id);
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

	@Override
	public Recipe findById(int id) throws Exception {
		System.out.println("Inside findById");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String sql = "SELECT * FROM recipes WHERE id=:id";
		try {
			Recipe recipe = namedParameterJdbcTemplate.queryForObject(sql, params, new RecipeMapper());
			System.out.println("Fetched Recipe : " + recipe);
			return recipe;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Getting EmptyResultDataAccessException " + ExceptionUtils.getStackTrace(e));
			throw new EmptyResultDataAccessException("No Recipe found.", 0);
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("Recipe can't be fetched.");
		}
	}
	
}
