package com.group.project.natalife.dao;

import java.util.List;

import com.group.project.natalife.model.Recipe;

public interface RecipeDao {

	List<Recipe> findByProduct(int id) throws Exception;

	Recipe insertNewRecipe(int product_id, String name, String description) throws Exception;

	Recipe findById(int id) throws Exception;

}
