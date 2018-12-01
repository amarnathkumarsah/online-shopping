package com.succexa.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.succexa.onlineshopping.exception.ProductNotFoundException;
import com.succexa.shoppingbackend.dao.CategoryDao;
import com.succexa.shoppingbackend.dao.ProductDao;
import com.succexa.shoppingbackend.dto.Category;
import com.succexa.shoppingbackend.dto.Product;
@Controller
public class PageController {

	private final static Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	
	@RequestMapping(value={"/","/home","index"})
	public ModelAndView index(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
		
		logger.info("Inside amarnath PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		//passing the list of category
		mv.addObject("categories",categoryDao.list());
		
		mv.addObject("userClickHome",true);
		return mv;
 		
	}
	
	@RequestMapping(value={"/about"})
	public ModelAndView about(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout",true);
		return mv;
 		
	}
	
	@RequestMapping(value={"/contact"})
	public ModelAndView contact(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact",true);
		return mv;
 		
	}

	//Methods to load all the products based on category
	@RequestMapping(value={"/show/all/products"})
	public ModelAndView showAllProducts(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","All Products");
		
		//passing the list of category
		mv.addObject("categories",categoryDao.list());
		
		mv.addObject("userClickAllProducts",true);
		return mv;
 		
	}
	
	@RequestMapping(value={"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id")int id){
		
		//CategoryDao to fetch a single category
		Category category = null;
		category = categoryDao.getCategory(id);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title",category.getName());
		
		//passing the list of categories
		mv.addObject("categories",categoryDao.list());
		
		//passing the single category
		mv.addObject("category",category);
		
		
		mv.addObject("userClickCategoryProducts",true);
		return mv;
 		
	}
/*	@RequestMapping(value="/test")
	public ModelAndView test(@RequestParam(name="greeting",required=false, defaultValue="HelloSpring") String greeting){
		if (greeting==null) {
			greeting="Hello there";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv;	
	}*/
	
	/*@RequestMapping(value="/test/{greeting}")
	public ModelAndView test(@PathVariable(name="greeting",required=false) String greeting){
		if (greeting==null) {
			greeting="Hello there";
		}	
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv;
		
	}*/
	
	/*viewing a single product*/
	@RequestMapping(value="/show/{productId}/product")
	public ModelAndView showSingleProduct(@PathVariable int productId) throws ProductNotFoundException{
		//get the product
		Product product = productDao.get(productId);
		
		if (product==null) {
			throw new ProductNotFoundException();
		}
		
		int categoryId = product.getCategoryId();
		Category category = categoryDao.getCategory(categoryId);
		//increment view
		product.setViews(product.getViews()+1);
		
		//Update the view Product
		productDao.update(product);
		
		//prepare modelandview
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("product",product);
		
		mv.addObject("category",category);
	
		
		//set title
		mv.addObject("title",product.getName());
		mv.addObject("singleProduct",true);
		
		
		return mv;
		
	}
	
	//it will not work because we have used webflow of spring
	/*having similar mapping to our flow id*/
	@RequestMapping(value={"/register"})
	public ModelAndView register(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Register");
		return mv;
 		
	}
	
	@RequestMapping(value={"/login"})
	public ModelAndView login(@RequestParam(name="error" ,required=false)String error,
			@RequestParam(name="logout" ,required=false)String logout){
		
		
		ModelAndView mv = new ModelAndView("login");
		
		if (error!=null) {
			mv.addObject("message", "Invalid Username and Password!");
		}
		if (logout!=null) {
			mv.addObject("message", "User has successfully logged out!");
		}

		mv.addObject("title","Login");
		return mv;
 		
	}
	
	//access denied handler
	@RequestMapping(value={"/access-denied"})
	public ModelAndView accessDenied(){
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title","403 - Access Denied ");
		mv.addObject("errorTitle","Aha! Cought You.");
		mv.addObject("errorDescription","You are not authorized to view this page!");
		return mv;
 		
	}
	
	@RequestMapping(value= {"/perform-logout"})
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		
		//now here we will perform logout operation
		//first we are going to fatch authentication object  which will give the details about the user authentication
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//if user is already authenticated  
		if (authentication!=null) {
			//clear session , unbound all the session object and remove the authentication form security context
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			System.out.println("i am clearing session");
		}
		return "redirect:/login?logout";
	}
	
}
