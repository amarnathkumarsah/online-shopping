package com.succexa.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.succexa.shoppingbackend.config.HibernateConfig;
import com.succexa.shoppingbackend.dao.CartLineDao;
import com.succexa.shoppingbackend.dao.ProductDao;
import com.succexa.shoppingbackend.dao.UserDao;
import com.succexa.shoppingbackend.dto.Cart;
import com.succexa.shoppingbackend.dto.CartLine;
import com.succexa.shoppingbackend.dto.Product;
import com.succexa.shoppingbackend.dto.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class, loader = AnnotationConfigContextLoader.class)
public class CartLineTestCase {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CartLineDao cartLineDao;
	
	private Product product;
	private User user;
	private Cart cart;
	private CartLine cartLine;
	 
	@Test
	public void testAddNewCartLine() {
		
		//get the user
		user = userDao.getUserByEmail("amarnathsah@gmail.com");
		
		//fetch the cart of this user
		cart = user.getCart();
		System.out.println("this is the cart of the currnt user"+cart);
		//get the product 
		product = productDao.get(1);
		if (product==null) {
			System.out.println("unable to fetch the product");
		}
		else {
			System.out.println("this the user i am getting in test"+user);
			
		}
		//now create a new cartLine
		CartLine cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitPrice());
				
		cartLine.setProductCount(cartLine.getProductCount() + 1 );
		
		cartLine.setTotal(cartLine.getProductCount()*product.getUnitPrice());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("failed to add cartLine", true,cartLineDao.add(cartLine));
	
		//Update the cart
		
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		
		cart.setCartLines(cart.getCartLines()+1);
		
		assertEquals("failed to update the cart ",true ,cartLineDao.updateCart(cart));
	}
}
