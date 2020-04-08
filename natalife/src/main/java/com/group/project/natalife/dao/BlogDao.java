package com.group.project.natalife.dao;

import java.util.List;

import com.group.project.natalife.model.Blog;
import com.group.project.natalife.model.Order;

public interface BlogDao {

	public List<Blog> findByAll(int limit) throws Exception;

	public int insertNewBlog(String username, String email, String title, String content) throws Exception;

}
