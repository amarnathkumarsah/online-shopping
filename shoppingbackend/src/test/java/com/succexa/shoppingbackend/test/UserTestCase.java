package com.succexa.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.succexa.shoppingbackend.config.HibernateConfig;
import com.succexa.shoppingbackend.dao.UserDao;
import com.succexa.shoppingbackend.dto.Address;
import com.succexa.shoppingbackend.dto.Cart;
import com.succexa.shoppingbackend.dto.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class, loader = AnnotationConfigContextLoader.class)
public class UserTestCase {
	
		//private static AnnotationConfigApplicationContext context ;
		
		
//		@BeforeClass
//		public static void init()
//		{
//			context = new AnnotationConfigApplicationContext();
//			context.scan("com.succexa.shoppingbackend");
//			context.refresh();
//			
//			userDao=(UserDao) context.getBean("userDao");
//		}
	@Autowired
	UserDao userDao;
		
	/*	@Test
		public void testAdd(){
			User user=new User();
			user.setFirstName("Ram");
			user.setLastName("Ratan Sahu");
			user.setEmail("ramratansahu@gmail.com");
			user.setContactNumber("8465215748");
			user.setPassword("ramratan");
			user.setRole("USER");
			
			assertEquals("Failed to add user",true, userDao.add(user));
			
			
			Address address = new Address();
			address.setAddressLineOne("near abc school");
			address.setAddressLineTwo("katihar,bihar");
			address.setBilling(true);
			address.setCity("katihar");
			address.setCountry("india");
			address.setPostalCode("855115");
			address.setState("bihar");
			//address.setShipping(false);
			address.setUserId(user.getId());
			
			assertEquals("Failed to add address",true, userDao.addAdress(address));
			
			if (user.getRole().equals("USER")) {
				//create a cart for this user 
				
				System.out.println("start card adding....................");
				Cart cart = new Cart();
				cart.setUser(user);
				System.out.println("end card adding....................");
				
				assertEquals("Failed to add Card",true, userDao.addCart(cart));
				
				//add a shipping address for this user
				address = new Address();
				address.setAddressLineOne("near abc school");
				address.setAddressLineTwo("katihar,bihar");
//				address.setBilling(true);
				address.setCity("katihar");
				address.setCountry("india");
				address.setPostalCode("855115");
				address.setState("bihar");
				address.setShipping(true);
				address.setUserId(user.getId());
			
				
				//add the shipping address

			assertEquals("Failed to add shiping address",true, userDao.addAdress(address));
				
			}
			
		}*/
	
	@Test
	public void testAdd(){
		User user=new User();
		user.setFirstName("Ram");
		user.setLastName("Ratan Sahu");
		user.setEmail("ramratansahu@gmail.com");
		user.setContactNumber("8465215748");
		user.setPassword("ramratan");
		user.setRole("USER");
		

		if (user.getRole().equals("USER")) {
			//create a cart for this user 
			
			System.out.println("start card adding....................");
			Cart cart = new Cart();
			cart.setUser(user);
			System.out.println("end card adding....................");
			
			user.setCart(cart);
		
			// i am not adding any card explicitly
		}
		
		assertEquals("Failed to add user",true, userDao.add(user));
		
	}
		
	
	/*@Test
	public void testUpdateCart(){
		//fetch a particular user
		User user = userDao.getUserByEmail("ramratansahu@gmail.com");
		
		//fetch cart of this particular user
		Cart cart = user.getCart();
		
		cart.setGrandTotal(8888);
		cart.setCartLines(4);
		assertEquals("failed to update cart",true,userDao.updateCart(cart));
	}*/
	
	/*@Test
	public void testShippingBillingAddresses(){
		User user=new User();
		user.setFirstName("Ram");
		user.setLastName("Ratan Sahu");
		user.setEmail("ramratansahu@gmail.com");
		user.setContactNumber("8465215748");
		user.setPassword("ramratan");
		user.setRole("USER");
		
		assertEquals("Failed to add user",true, userDao.add(user));
		
		Address address = new Address();
		address.setAddressLineOne("near patna school");
		address.setAddressLineTwo("patna,bihar");
     	address.setBilling(true);
		address.setCity("katihar");
		address.setCountry("india");
		address.setPostalCode("855115");
		address.setState("bihar");
 //		address.setShipping(true);
		address.setUser(user);
		

		assertEquals("Failed to add shiping address",true, userDao.addAdress(address));
		
		
		address = new Address();
		address.setAddressLineOne("near chapra school");
		address.setAddressLineTwo("chapra,bihar");
//		address.setBilling(true);
		address.setCity("katihar");
		address.setCountry("india");
		address.setPostalCode("855115");
		address.setState("bihar");
		address.setShipping(true);
		address.setUser(user);
		
		assertEquals("Failed to add shiping address",true, userDao.addAdress(address));
		
		
		
	}*/
	
	/*@Test
	public void testAddOneMoreAddress(){
		
		User user = userDao.getUserByEmail("ramratansahu@gmail.com");
		
		Address address = new Address();
		address.setAddressLineOne("near katihar school");
		address.setAddressLineTwo("katihar,bihar");
//		address.setBilling(true);
		address.setCity("katihar");
		address.setCountry("india");
		address.setPostalCode("855115");
		address.setState("bihar");
		address.setShipping(true);
		address.setUser(user);
		
		assertEquals("Failed to add shiping address",true, userDao.addAdress(address));
		
	}*/
	
	/*@Test
	public void testGetAddress()
	{
		User user = userDao.getUserByEmail("ramratansahu@gmail.com");
		
		assertEquals("faied to get billing address","patna", userDao.getBillingAddress(user).getCity());
		
		assertEquals("failed to get exect no. of shipping address",2, userDao.listShippingAddresses(user).size());
		
		user=new User();
		user.setId(0);
		user.setFirstName("Amarnath");
		user.setLastName("kumar");
		user.setEmail("amrnath@gmail.com");
		user.setContactNumber("8058751072");
		user.setPassword("amarnath");
		user.setRole("USER");
		
		if (user.getRole().equals("USER")) {
		Cart cart = new Cart();
		cart.setUser(user);
		user.setCart(cart);
	}
		assertEquals("Failed to add user",true, userDao.add(user));
		System.out.println(user);
	}*/
	
}

		