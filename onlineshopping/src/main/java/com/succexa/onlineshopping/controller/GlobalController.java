package com.succexa.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.succexa.onlineshopping.model.UserModel;
import com.succexa.shoppingbackend.dao.UserDao;
import com.succexa.shoppingbackend.dto.User;
@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDao userDao;

	private UserModel userModel = null;

	@ModelAttribute(name="userModel")
	public UserModel getUserModel() {
		if (session.getAttribute("userModel")==null) {
			
			Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
			System.out.println("this is the what i am getting form the security context " +authentication.getName());
			User user = userDao.getUserByEmail(authentication.getName());
			
			// i have added a new condition in this line
			System.out.println("i have added a new condition in this line anonymousUser");
			if (user!=null&&!authentication.getName().equals("anonymousUser")) {
				
				//create userModel object and assign it to session
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setFullName(user.getFirstName()+" "+user.getLastName());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				System.out.println("i am going to check user Role");
				if (user.getRole().equals("USER")) {
					userModel.setCart(user.getCart());
	
				}
   
				//set new userModel in session
				session.setAttribute("userModel", userModel);
				return userModel; 
			}
		}
		return (UserModel)session.getAttribute("userModel");
	}
}
