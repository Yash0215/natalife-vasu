package com.group.project.natalife.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group.project.natalife.dao.ProductDao;
import com.group.project.natalife.dao.UserDao;
import com.group.project.natalife.model.Product;
import com.group.project.natalife.model.User;

@Controller
public class SignUpController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	
	/**
	 * Method to show the initial HTML form
	 * 
	 * @return
	 */
	@GetMapping("/signup")
	public String signup(HttpSession session,  HttpServletRequest request) {
		System.out.println("Inside signup");
		User user = (User) session.getAttribute("user");
		if (user != null) {
			System.out.println("Already login.");
			addProducts(request);
			return "home";
		}
		return "signup-form";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {
		System.out.println("Inside signup");
		
		String fname = user.getFname();
		String lname = user.getFname();
		String email = user.getEmail();
		String password = user.getPassword();
		String rpassword = user.getRpassword();

		if(password.equals(rpassword)) {
			User newUser;
			try {
				newUser = userDao.insertNewUser(fname, lname, email, password);
			} catch (DuplicateKeyException e) {
				model.addAttribute("message", "User already exist.");
				return "signup-form";
			} catch (Exception e) {
				model.addAttribute("message", "User can't be created.");
				return "signup-form";
			}
			if(newUser == null) {
				model.addAttribute("message", "Login Unsuccessfull.");
				return "signup-form";
			} else {
				request.getSession().setAttribute("user", user);
				model.addAttribute("message", "User registered.");
				addProducts(request);
				return "home";
			}
		} else {
			model.addAttribute("message", "Password does not match.");
			return "signup-form";
		}
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
