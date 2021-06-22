package com.christian.productsandcategories.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.christian.productsandcategories.models.*;
import com.christian.productsandcategories.services.*;

@Controller
public class HomeController {
	private final MainService mainServ;
	
	public HomeController(MainService mainServ) {
		this.mainServ = mainServ;
	}
	//Homepage
	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}
	//New Product Form
	@GetMapping("/products/new")
	public String newProduct(@ModelAttribute("product")Product product) {
		return "newProduct.jsp";
	}
	//New Category Form
	@GetMapping("/categories/new")
	public String newCategory(@ModelAttribute("category")Category category) {
		return "newCategory.jsp";
	}
	//On Submit Product Form Post
	@PostMapping("/products/new")
	public String createProduct(@Valid @ModelAttribute("product")Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "newProduct.jsp";
		} else {
			mainServ.createProduct(product);
			Long product_id = product.getId();
			return "redirect:/products/" + product_id;
		}
	}
	//On Submit Category Form Post
	@PostMapping("/categories/new")
	public String createCategory(@Valid @ModelAttribute("category")Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "newCategory.jsp";
		} else {
			mainServ.createCategory(category);
			Long category_id = category.getId();
			return "redirect:/categories/" + category_id;
		}
	}
	//Show Products page bring over both lists, one for dropdown and one to list out
	@GetMapping("/products/{id}")
	public String showProduct(@PathVariable("id")Long id, Model model) {
		Product product = mainServ.findProduct(id);
		model.addAttribute("product", product);
		List<Category> added = product.getCategories();
		model.addAttribute("added", added);
		
		List<Category> categories = mainServ.allCategories();
		List<Category> menu = new ArrayList<Category>();
		
		menu.addAll(categories);
		menu.removeAll(added);
		model.addAttribute("menu", menu);
		return "product.jsp";
	}
	//Show Category Page same deal backwards...
	@GetMapping("/categories/{id}")
	public String showCategory (@PathVariable("id") Long id, Model model) {
		Category category = mainServ.findCategory(id);
		model.addAttribute("category", category);
		List<Product> added = category.getProducts();
		model.addAttribute("added", added);
		
		List<Product> products = mainServ.allProducts();
		List<Product> menu = new ArrayList<Product>();
		
		menu.addAll(products);
		menu.removeAll(added);
		model.addAttribute("menu", menu);
		return "category.jsp";
	}
	@PostMapping("/products/{id}/add")
	public String addCategoryToProduct (@PathVariable("id") Long id, HttpServletRequest request) {
		Product product = mainServ.findProduct(id);
		String c = request.getParameter("name");
		Category category = mainServ.findCategoryName(c);
		product.getCategories().add(category);
		mainServ.createProduct(product);
		return "redirect:/products/" + id;
	}
	
	@PostMapping("/categories/{id}/add")
	public String addProductToCategory (@PathVariable("id") Long id, HttpServletRequest request) {
		Category category = mainServ.findCategory(id);
		String p = request.getParameter("name");
		Product product = mainServ.findProductName(p);
		category.getProducts().add(product);
		mainServ.createCategory(category);
		return "redirect:/categories/" + id;
	}
	

}
