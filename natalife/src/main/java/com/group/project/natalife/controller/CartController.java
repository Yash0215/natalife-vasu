package com.group.project.natalife.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.group.project.natalife.dao.ProductDao;
import com.group.project.natalife.model.Cart;
import com.group.project.natalife.model.CartEntry;
import com.group.project.natalife.model.Product;

@Controller
public class CartController {
	
	@Autowired
	private ProductDao productDao;
	
	@GetMapping("/cart")
	public String cart(HttpSession session, HttpServletRequest request) {
		System.out.println("Inside cart");
		if(session.getAttribute("cart") == null) {
			addProducts(request);
			return "products";
		}
		return "cart";
	}
	
	@GetMapping("/addToCart")
	public String addToCart(HttpServletRequest request) {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e1) {
			System.out.print("Invalid product id.");
			return "error";
		}
		System.out.println("Inside addToCart, id : " + id);
		try {
			Product product = productDao.findById(id);
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if(cart == null) {
				System.out.println("null cart");
				cart = initializeCart();
			}
			Map<Integer, CartEntry> entries = cart.getEntries();
			CartEntry entry = null;
			if(entries.containsKey(id)) {
				entry = entries.get(id);
				entry.setQuantity(entry.getQuantity()+1);
			} else {
				entry = new CartEntry(product, 1);
			}
			entries.put(id, entry);
			cart.setEntries(entries);
			cart.setTotalAmount(cart.getTotalAmount() + product.getPrice());
			cart.setTotalElements(cart.getTotalElements() + 1);
			request.getSession().setAttribute("cart", cart);
			addProducts(request);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("No products found.");
		} catch (Exception e) {
			System.out.println("Error in getting products.");
		}
		return "products";
	}

	private void addProducts(HttpServletRequest request) {
		try {
			List<Product> products = productDao.getProducts(0);
			if (products != null) {
				System.out.println("Adding products.");
				request.setAttribute("products", products);
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("No products found.");
		} catch (Exception e) {
			System.out.println("Error in getting products.");
		}
	}

	private Cart initializeCart() {
		Cart cart = new Cart();
		cart.setTotalAmount(0.0);
		cart.setEntries(new HashMap<Integer, CartEntry>());
		cart.setTotalElements(0);
		return cart;
	}
	
}
