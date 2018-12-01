package com.succexa.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.succexa.onlineshopping.util.FileUploadUtility;
import com.succexa.onlineshopping.validator.UploadValidator;
import com.succexa.shoppingbackend.dao.CategoryDao;
import com.succexa.shoppingbackend.dao.ProductDao;
import com.succexa.shoppingbackend.dto.Category;
import com.succexa.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation){
		System.out.println("*************I am in get request in MangementController*******************");
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts", true);
		
		Product newProduct = new Product();
		
		//set few of the field
		newProduct.setActive(true);
		newProduct.setSupplierId(3);
		
		mv.addObject("product",newProduct);
		
		
		//mv.addObject("products", productDao.list());
		
		if (operation!=null) {
			
			if (operation.equals("added")) {
				mv.addObject("alertType", "success");
				mv.addObject("message","Product added successfully!");
			}

			if (operation.equals("updated")) {
				mv.addObject("alertType", "success");
				mv.addObject("message","Product updated successfully!");
			}
			
			if (operation.equals("categoryAdded")) {
				mv.addObject("alertType", "success");
				mv.addObject("message","Category Added successfully!");
			}
		}
		
		return mv;
	}
	

	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts", true);
		
		//get the product from id
		Product oldProduct = productDao.get(id);
		
		//set the product field fatched from database
		mv.addObject("product",oldProduct);
		
		return mv;
	}
	
	
	//Working on Activation/ Deactivation
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id ){
	
		//getting product by id
		Product product = productDao.get(id);
		
		//checking active status
		boolean isActive = product.isActive();
		
		product.setActive(!isActive);
		
		productDao.update(product);
		
		return isActive?"You have successfully Deactivited the product with id "+product.getId()
				:"You have successfully Activited the product with id "+product.getId();
	}
	
	//category list for all request mapping
	@ModelAttribute("categories")
	public List<Category> listCategory(){
		
		 return categoryDao.list();
	}
	
	//category for all request mapping specially for Modal
		@ModelAttribute("category")
		public Category getCategory(){
			
			 return  new Category();
		}
	
	
	//handling product submission
	@RequestMapping(value="/products", method=RequestMethod.POST,headers = "Content-Type= multipart/form-data")
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult bResults,Model model,HttpServletRequest request){
		System.out.println("*************I am in post request in MangementController*******************");
		
		if (mProduct.getId()==0) {
			//if id is zero than check file as new product
			new UploadValidator().validate(mProduct, bResults); 	
		}
		
		else if (!mProduct.getFile().getOriginalFilename().equals("")) {
			
			//if id is not zero than on validate the file
			
				new UploadValidator().validate(mProduct, bResults);
			}
		
		
		if (bResults.hasErrors()) {
			
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("alertType", "danger");
			model.addAttribute("message", "invalid details on submission");
			
			return "page";
		}
		
		String msg =null;//for getting correct message
		
		if (mProduct.getId()==0) {
			//for new product add
			productDao.add(mProduct);
			msg = "added";
		}else{
			//for existing product update
			productDao.update(mProduct);
			msg = "updated";
		}
		
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			
		/*	System.out.println(request.toString());
			System.out.println(mProduct.getFile().getName());
			System.out.println(mProduct.getCode());*/
			System.out.println("i am calling uploadFile");
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation="+msg;
	}
	
	//to handle category submission
	@RequestMapping(value="/new/category",method=RequestMethod.POST)
	public String addNewCategory(@ModelAttribute Category category){
	
		categoryDao.add(category);
		
		return "redirect:/manage/products?operation=categoryAdded";
	}
}