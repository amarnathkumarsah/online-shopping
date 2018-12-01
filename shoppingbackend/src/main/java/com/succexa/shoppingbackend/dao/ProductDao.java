package com.succexa.shoppingbackend.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.succexa.shoppingbackend.dto.Product;
@Service
public interface ProductDao {

	List<Product> list();

	boolean add(Product product);
	boolean update(Product product);
	Product get(int productId);
	boolean delete(Product product);
	
	//business methods
	List<Product> listActiveProducts();
	
	List<Product> listActiveProductByCategory(int categoryId);
	
	List<Product> getLatestActiveProducts(int count);
	
	//List<Product> listActiveProductBySupplier();
}
