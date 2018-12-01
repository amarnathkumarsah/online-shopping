package com.succexa.shoppingbackend.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.succexa.shoppingbackend.dto.Category;
@Service
public interface CategoryDao {


	Category getCategory(int id);
	List<Category> list();
	boolean add(Category catetory);
	boolean update(Category catetory);
	boolean delete(Category catetory);
	//Category getCategoryByProductId(int productId);
	
}
