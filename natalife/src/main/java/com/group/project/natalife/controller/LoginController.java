package com.group.project.natalife.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group.project.natalife.dao.ProductDao;
import com.group.project.natalife.dao.UserDao;
import com.group.project.natalife.model.LoginInfo;
import com.group.project.natalife.model.Product;
import com.group.project.natalife.model.User;

@Controller
public class LoginController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	/**
	 * Method to show the initial HTML form
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String login(HttpSession session, HttpServletRequest request) {
		System.out.println("Inside login");
		User user = (User) session.getAttribute("user");
		if (user != null) {
			System.out.println("Already login.");
			addProducts(request);
			return "home";
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			System.out.println("Logging out. User : " + user);
			session.removeAttribute("user");
			addProducts(request);
			return "home";
		}
		addProducts(request);
		return "home";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginInfo") LoginInfo loginInfo, Model model, HttpServletRequest request) {
		System.out.println("Inside login");
		User user = null;
		try {
			user = userDao.findByEmail(loginInfo.getEmail());
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("message", "User not found.");
			return "login";
		}
		if (user != null) {
			if(user.getPassword().equals(loginInfo.getPassword())) {
//				model.addAttribute("user", user);
				request.getSession().setAttribute("user", user);
				model.addAttribute("message", "Login Successfull.");
				addProducts(request);
				return "home";
			} else {
				model.addAttribute("message", "Invalid Password.");
				return "login";
			}
		} else {
			model.addAttribute("message", "Unable to login.");
			return "login";
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
