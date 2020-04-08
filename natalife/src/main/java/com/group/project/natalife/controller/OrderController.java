package com.group.project.natalife.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.group.project.natalife.dao.OrderDao;
import com.group.project.natalife.dao.OrderProductDao;
import com.group.project.natalife.dao.ProductDao;
import com.group.project.natalife.model.Cart;
import com.group.project.natalife.model.CartEntry;
import com.group.project.natalife.model.Order;
import com.group.project.natalife.model.Product;
import com.group.project.natalife.model.User;

@Controller
public class OrderController {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderProductDao orderProductDao;
	
	@Autowired
	private ProductDao productDao;
	
	@GetMapping("/order")
	public String order(HttpSession session, HttpServletRequest request, Model model) {
		System.out.println("Inside order");
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		if (user == null) {
			System.out.println("User not found.");
			model.addAttribute("message", "Please first login, to place order.");
			return "login";
		} else if (cart == null) {
			System.out.println("Cart not found.");
			addProducts(request, 0);
			return "products";
		} else if (user != null && cart != null) {
			return "place-order";
		}
		addProducts(request, 4);
		return "home";
	}

	@PostMapping("/placeOrder")
	public String placeOrder(HttpSession session, Model model, HttpServletRequest request) {
		System.out.println("Inside placeOrder");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		User user = (User) session.getAttribute("user");
		if (user == null) {
			System.out.println("User not found.");
			model.addAttribute("message", "Please first login, to place order.");
			return "login";
		}
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			System.out.println("Cart not found.");
			return "error";
		}
		try {
			Order order = orderDao.insertNewOrder(user.getEmail(), address, mobile, cart.getTotalAmount());
			for(CartEntry entry : cart.getEntries().values()) {
				orderProductDao.insertNewOrderProductMapping(order, entry.getProduct(), entry.getQuantity());
			}
			request.setAttribute("orderid", leftPad(order.getId()));
			session.removeAttribute("cart");
			return "order-success";
		} catch (Exception e) {
			System.out.println("Getting Exception in inserting Order : "+ ExceptionUtils.getStackTrace(e));
			return "error";
		}
	}
	
	private Object leftPad(int id) {
		String id_str = String.valueOf(id);
		int length = id_str.length();
		for(int i = length; i < 5; i++) {
			id_str = "0" + id_str; 
		}
		return "NTO" + id_str;
	}

	private void addProducts(HttpServletRequest request, int limit) {
		try {
			List<Product> products = productDao.getProducts(limit);
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