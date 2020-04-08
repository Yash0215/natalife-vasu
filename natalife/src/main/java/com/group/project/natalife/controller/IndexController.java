package com.group.project.natalife.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.group.project.natalife.dao.ProductDao;
import com.group.project.natalife.model.Product;
import com.group.project.natalife.model.User;

@Controller
public class IndexController {

	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/")
	public String homepage(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Inside homepage");
		addProducts(request);
	    return "home";
	}
	
	@GetMapping("index")
	public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Inside index");
		addProducts(request);
		return "home";
	}
	
	@GetMapping("admin")
	public String admin(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Inside admin");
		User user = (User) session.getAttribute("user");
		if (user == null || !user.isIsadmin()) {
			System.out.println("User not found or not Admin.");
			return "error";
		} 
		return "admin";
	}
	
	@GetMapping("error")
	public String error(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Inside error");
		return "error";
	}
	
	private void addProducts(HttpServletRequest request) {
		try {
			List<Product> products = productDao.getProducts(4);
			if(products != null) {
				System.out.println("Adding products.");
				request.setAttribute("products", products);
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("No products found.");
		} catch (Exception e) {
			System.out.println("Error in getting products.");
		}
	}

}
