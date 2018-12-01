package com.succexa.shoppingbackend.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.succexa.shoppingbackend.dto.Address;
import com.succexa.shoppingbackend.dto.Cart;
import com.succexa.shoppingbackend.dto.User;
@Service
public interface UserDao {

	boolean add(User user);
	
	//get a user
	User getUserByEmail(String email) ;
	
	//Address
	boolean addAdress(Address address);
	//Alternative  //we can use insted of @ManytoOne annotaion
	//Address getBillingAddress(int userId);
	//List<Address> listShippingAddresses(int userId);
	
	Address getBillingAddress(User user);
	
	List<Address> listShippingAddresses(User user);
	
	//Update a cart** Add a cart
	//boolean updateCart(Cart cart);  shifted to the CartLineDao
}
