package com.group.project.natalife.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.group.project.natalife.dao.BlogDao;
import com.group.project.natalife.model.Blog;
import com.group.project.natalife.model.Cart;
import com.group.project.natalife.model.CartEntry;
import com.group.project.natalife.model.Order;
import com.group.project.natalife.model.User;

@Controller
public class BlogController {

	@Autowired
	private BlogDao blogDao;
	
	@GetMapping("/blogs")
	public String blogs(HttpSession session, HttpServletRequest request) {
		System.out.println("Inside blogs");
		try {
			List<Blog> blogs = blogDao.findByAll(0);
			if(blogs.size() > 0) {
				request.setAttribute("blogs", blogs);
				return "blogs";
			}
			
		} catch (Exception e) {
			return "error";
		}
		return "home";
	}
	
	@PostMapping("/postBlog")
	public String postBlog(HttpSession session, Model model, HttpServletRequest request) {
		System.out.println("Inside postBlog");
		String username = request.getParameter("name");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		try {
			blogDao.insertNewBlog(username, email, title, content);
		} catch (Exception e) {
			System.out.println("Getting Exception in inserting Order : "+ ExceptionUtils.getStackTrace(e));
		}
		return blogs(session, request);
	}
	
}
