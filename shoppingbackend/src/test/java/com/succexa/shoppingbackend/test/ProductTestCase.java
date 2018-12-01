package com.succexa.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.succexa.shoppingbackend.dao.ProductDao;
import com.succexa.shoppingbackend.dto.Category;
import com.succexa.shoppingbackend.dto.Product;

public class ProductTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	private static ProductDao productDao;
	
	private Product product;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("com.succexa.*");
		context.getBean(Category.class);
		context.refresh();
		productDao = (ProductDao) context.getBean("productDao");

	}
	/*
	@Test
	public void testCRUDCategory() {

		// add operation
		product = new Product();
	
		product.setName("Nokia 101");
		product.setBrand("Nokia");
		product.setDescription("This is description of Nokia 101");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(1);
		product.setSupplierId(2);
		product.setQuantity(10);

		assertEquals("something went wrong while adding a new product into table", true, productDao.add(product));

		product = new Product();

		product.setName("Samsung galaxy s7 ");
		product.setBrand("Samsung");
		product.setDescription("This is description of Samsung galaxy s7 ");
		product.setUnitPrice(35000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);

		assertEquals("something went wrong while adding a new product into table", true, productDao.add(product));

		// Fetching and Update product
		product = productDao.get(8);
		
		//confirming fetched product is correct or not
		assertEquals("unble to fetch corrct data","Samsung", product.getBrand());
		
		//updating a product
		product.setName("Samsung galaxy s7");
		
		assertEquals("something went wrong while updating a product into table", true, productDao.update(product));
		
		// Delete a product
		assertEquals("something went wrong while deleting a product into table", true, productDao.delete(product));
		
		//After Delete a product new list
		assertEquals("something went wrong while getting list of updated products into table", 8, productDao.list().size());

		// List of active products after delete
		assertEquals("something went wrong while getting list of active products  from table", 7, productDao.listActiveProducts().size());
		
		//List of products by categroy_id
		assertEquals("something went wrong while getting list of  products by category from table", 5, productDao.listActiveProductByCategory(3).size());
		
		//latest products
		assertEquals("something went wrong while getting list of lattest products a product from table", 5, productDao.getLatestActiveProducts(5).size());
	}*/
	
	 @Test
	public void addTest()
	{
		// add operation
				product = new Product();
			
				product.setName("Nokia 101");
				product.setBrand("Nokia");
				product.setDescription("This is description of Nokia 101");
				product.setUnitPrice(25000);
				product.setActive(true);
				product.setCategoryId(1);
				product.setSupplierId(3);
				product.setQuantity(10);

		assertEquals("somthing went worong while adding the product",true,productDao.add(product));
	}
	
	
	
}
