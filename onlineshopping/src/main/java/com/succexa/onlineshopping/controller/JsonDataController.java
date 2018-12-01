package com.succexa.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.succexa.shoppingbackend.dao.ProductDao;
import com.succexa.shoppingbackend.dto.Product;

@RestController
/*@ResponseBody
@Controller*/
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDao productDao;
	
	/*@ResponseBody*/
	@RequestMapping("/all/products")
	public List<Product> getAllProducts(){
		return productDao.listActiveProducts();
		
	}
	
	/*@ResponseBody*/
	@RequestMapping("/category/{id}/products")
	public List<Product> getCategoryProducts(@PathVariable int id){
		return productDao.listActiveProductByCategory(id);
		
	}
	
	@RequestMapping("/admin/all/products")
	public List<Product> getAllProductsForAdmin(){
		return productDao.list();
		
	}
	
}
