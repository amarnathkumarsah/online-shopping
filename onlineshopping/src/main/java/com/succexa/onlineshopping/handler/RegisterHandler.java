package com.succexa.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.succexa.onlineshopping.model.RegisterModel;
import com.succexa.shoppingbackend.dao.UserDao;
import com.succexa.shoppingbackend.dto.Address;
import com.succexa.shoppingbackend.dto.Cart;
import com.succexa.shoppingbackend.dto.User;

//to create a bean we need to add Component annotation
@Component
public class RegisterHandler {

	@Autowired
	public UserDao userDao;
	
	//@Autowired
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	 
	
	public RegisterModel init(){
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user){
		
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address address){
		registerModel.setAddress(address);
	
	}
	
	public String saveAll(RegisterModel registerModel){
		
		
		String transitionValue = "success";
		
		//fatch the user from flow
		User user = registerModel.getUser(); 
		
		//check user role
		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//encoding password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		System.out.println("i am encoding password");
		//save the user
		userDao.add(user);
		
		
		//Get the Address
		Address address = registerModel.getAddress();
		
		address.setUser(user);
		
		address.setBilling(true);
		
		//save the address
		userDao.addAdress(address);
		
		return transitionValue;
		
	}
	
	public String validateUser(User user,MessageContext error){
		
		String transitionValue = "success";
		
	if (!(user.getPassword().equals(user.getConfirmPassword()))) {
		
		error.addMessage(new MessageBuilder()
				.error()
				.source("confirmPassword")
				.defaultText("Password does not match the confirm Password").build()
				);
				
		transitionValue = "failure";
		
	}
		
	//check the uniqueness of the email id 
		if (userDao.getUserByEmail(user.getEmail())!=null) {
			
			error.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("Email address is already used!").build()
					);
			
			
			transitionValue = "failure";
		}
		
	
	
	
		return transitionValue;
	}
	
	
}
