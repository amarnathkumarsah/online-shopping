/*package com.succexa.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.succexa.shoppingbackend.dao.CategoryDao;
import com.succexa.shoppingbackend.dto.Category;

public class CategoryTestCase {

	
	private static AnnotationConfigApplicationContext context;

	
	private static CategoryDao categoryDao;
	
	private Category category;

	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.succexa.shoppingbackend");
		context.refresh();	
		categoryDao = (CategoryDao)context.getBean("categoryDao");
		
	}
	
	@Test
	public void testAddCategory(){
		
		Category category = new Category();
		
		category.setName("Television");
		category.setDescription("This is description of television");
		category.setImageURL("Television.jpeg");
		category.setAvtive(true);
		
		assertEquals("Successfully added Television category inside the table!", true, categoryDao.add(category));
		
		category = new Category();
		
		category.setName("Mobile");
		category.setDescription("This is description of Mobile");
		category.setImageURL("Mobile.jpeg");
		category.setAvtive(true);
		
		assertEquals("Successfully added Mobile category inside the table!", true, categoryDao.add(category));
		
	}*/
	
/*	
	@Test
	public void testGetCategory(){
		Category category = categoryDao.getCategory(5);
		
		
		assertEquals("Successfully added a category inside the table!", "Cloths", category.getName());
		
	}
	*/
	
/*	
	@Test
	public void testUpdateCategory(){
		
		Category category  = categoryDao.getCategory(8);
		category.setName("Unknown");
		boolean b = categoryDao.update(category);
		
		
		
		assertEquals("Successfully added a category inside the table!", true, b);
		
	}
	*/
	
/*	@Test
	public void testDeleteCategory(){
		
		Category category  = categoryDao.getCategory(3);
	
		boolean b = categoryDao.delete(category);
		
		
		
		assertEquals("Successfully added a category inside the table!", true, b);
		
	}
	*/
	
/*	@Test
	public void testListCategory(){
		

	assertEquals("Successfully added a category inside the table!", 4, categoryDao.list().size());
	
		
	}*/
	
	/*@Test
	public void testCRUDCategory(){
		
		//add operation
		Category category = new Category();
		
		category.setName("Television");
		category.setDescription("This is description of television");
		category.setImageURL("tv.jpeg");
		category.setAvtive(true);
		
		
		assertEquals("Successfully added Television category inside the table!", true, categoryDao.add(category));
		
		category = new Category();
		
		category.setName("Mobile");
		category.setDescription("This is description of mobile");
		category.setImageURL("tv.jpeg");
		category.setAvtive(true);
		
		
		assertEquals("Successfully added Mobile category inside the table!", true, categoryDao.add(category));
		
		
		//fetching and update category
		category  = categoryDao.getCategory(21);
		category.setName("TV");
	
		
		assertEquals("Successfully fetched and update category inside the table!", true, categoryDao.update(category));
		
		
		assertEquals("Successfully deleted a category inside the table!", true, categoryDao.delete(category));
		
		assertEquals("Successfully added a category inside the table!", 1, categoryDao.list().size());
		
	}*/
/*
}*/
