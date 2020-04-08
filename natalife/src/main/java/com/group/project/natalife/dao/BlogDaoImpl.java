package com.group.project.natalife.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.group.project.natalife.model.Blog;
import com.group.project.natalife.model.Order;

@Repository
public class BlogDaoImpl implements BlogDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	private static final class BlogMapper implements RowMapper<Blog> {
		public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
			Blog blog = new Blog();
			blog.setId(rs.getInt("id"));
			blog.setTitle(rs.getString("title"));
			blog.setContent(rs.getString("content"));
			blog.setUsername(rs.getString("username"));
			blog.setEmail(rs.getString("email"));
			return blog;
		}
	}
	
	@Override
	public List<Blog> findByAll(int limit) throws Exception {
		System.out.println("Getting blogs");
		String sql = "SELECT * FROM blogs";
		if(limit > 0) {
			sql += " LIMIT " + limit;
		}
		try {
			List<Blog> blogs =  namedParameterJdbcTemplate.query(sql, new BlogMapper());
			System.out.println("Total blogs fetched : " + blogs.size());
			return blogs;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Getting EmptyResultDataAccessException " + ExceptionUtils.getStackTrace(e));
			throw new EmptyResultDataAccessException("Products not found.", 0);
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("Blogs can't be fetched.");
		}
	}

	@Override
	public int insertNewBlog(String username, String email, String title, String content) throws Exception {
		String sql = "INSERT INTO blogs(username,email,title,content) "
				+ "VALUES(:username,:email,:title,:content)";
		int rowCount;
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email)
				.addValue("username", username).addValue("title", title).addValue("content", content);
		try {
			rowCount = namedParameterJdbcTemplate.update(sql, param);
		} catch (Exception e) {
			System.out.println("Getting Exception " + ExceptionUtils.getStackTrace(e));
			throw new Exception("Blog can't be created.");
		}
		return rowCount;
	}
}
