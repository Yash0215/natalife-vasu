package com.group.project.natalife.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.group.project.natalife.dao.ProductDao;
import com.group.project.natalife.dao.RecipeDao;
import com.group.project.natalife.model.Product;
import com.group.project.natalife.model.Recipe;
import com.group.project.natalife.model.User;

@Controller
public class ProductController {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private RecipeDao recipeDao;

	/**
	 * Method to show the initial HTML form
	 * 
	 * @return
	 */
	@GetMapping("/products")
	public String showProducts(HttpServletRequest request) {
		System.out.println("Inside showProducts");
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
		return "products";
	}
	
	@GetMapping("/product-details")
	public String productsDetails(HttpServletRequest request) {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e1) {
			System.out.print("Invalid product id.");
			return "error";
		}
		System.out.println("Inside productsDetails, id : " + id);
		try {
			Product product = productDao.findById(id);
			if (product != null) {
				System.out.println("Adding product.");
				request.setAttribute("product", product);
				setRecipes(request, id);
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Product Not found.");
			return "error";
		} catch (Exception e) {
			System.out.println("Error in getting product.");
			return "error";
		}
		return "product-details";
	}
	
	@GetMapping("insertProduct")
	public String insertProduct(HttpSession session, HttpServletRequest request) {
		System.out.println("Inside insertProduct");
		User user = (User) session.getAttribute("user");
		if (user == null || !user.isIsadmin()) {
			System.out.println("User not found or not Admin.");
			return "error";
		} 
		return "product-insert";
	}

	@PostMapping("insertProduct")
	public String insertNewProduct(HttpSession session, HttpServletRequest request, Model model) {
		System.out.println("Inside insertNewProduct");
		User user = (User) session.getAttribute("user");
		if (user == null || !user.isIsadmin()) {
			System.out.println("User not found or not Admin.");
			return "error";
		} 
		Map<String, Object> formFields = new HashMap<>();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletContext servletContext = request.getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			
			System.out.println("c: " + System.getProperty("user.dir"));
			
			while (iter.hasNext()) {
			    FileItem item = iter.next();
			    if (item.isFormField()) {
			    	formFields.put(item.getFieldName(), item.getString());
			    } else {
			    	String fileName = item.getName();
			    	File uploadedFile = new File(System.getProperty("user.dir") + "/src/main/webapp/resources/img/" + fileName);
			        item.write(uploadedFile);
			        formFields.put("image", fileName);
			    }
			}
		} catch (Exception e) {
			System.out.println("Getting Exception while getting multipart parameters : " + ExceptionUtils.getStackTrace(e));
			model.addAttribute("message", "Product can't be inserted.");
		}
		try {
			Product product = productDao.insertNewProduct(formFields);
			model.addAttribute("message", "Product insered successfully. Product id: " + product.getId());
		} catch (Exception e) {
			model.addAttribute("message", "Product can't be inserted.");
		}
		return "product-insert";
	}

	private void setRecipes(HttpServletRequest request, int id) {
		try {
			List<Recipe> recipes = recipeDao.findByProduct(id);
			if (recipes != null) {
				System.out.println("Adding recipes.");
				request.setAttribute("recipes", recipes);
			}
		}  catch (Exception e) {
			System.out.println("No Recipes Found.");
		}
	}

	@GetMapping("insertRecipe")
	public String insertRecipe(HttpSession session, HttpServletRequest request,Model model) {
		System.out.println("Inside insertRecipe");
		User user = (User) session.getAttribute("user");
		if (user == null || !user.isIsadmin()) {
			System.out.println("User not found or not Admin.");
			return "error";
		} 
		try {
			List<Product> products = productDao.getProducts(0);
			if(products.size()==0) {
				model.addAttribute("message", "No products found for recipes.");
			} else {
				request.setAttribute("products", products);
			}
		} catch (Exception e) {
			System.out.println("Product can't be fetched.");
			return "error";
		}
		return "recipe-insert";
	}

	@PostMapping("insertRecipe")
	public String insertNewRecipe(HttpSession session, HttpServletRequest request, Model model) {
		System.out.println("Inside insertNewRecipe");
		User user = (User) session.getAttribute("user");
		if (user == null || !user.isIsadmin()) {
			System.out.println("User not found or not Admin.");
			return "error";
		} 
		try {
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			Recipe recipe = recipeDao.insertNewRecipe(product_id, name, description);
			model.addAttribute("message", "Recipe insered successfully. Recipe id: " + recipe.getId());
		} catch (Exception e) {
			System.out.println("Getting Exception while inserting recipes : " + ExceptionUtils.getStackTrace(e));
			model.addAttribute("message", "Recipe can't be inserted.");
		}
		try {
			List<Product> products = productDao.getProducts(0);
			if(products.size()==0) {
				model.addAttribute("message", "No products found for recipes.");
			} else {
				request.setAttribute("products", products);
			}
		} catch (Exception e) {
			System.out.println("Product can't be fetched.");
			return "error";
		}
		return "recipe-insert";
	}

}
