package com.succexa.shoppingbackend.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.succexa.shoppingbackend.dto.Cart;
import com.succexa.shoppingbackend.dto.CartLine;

@Service
public interface CartLineDao {
	
	public CartLine get(int id);
	
	public boolean add(CartLine cartLine);
	
	public boolean update(CartLine cartLine);
	
	public boolean delete(CartLine cartLine);
	
	public List<CartLine> list(int cartId); 
	
	//Other Business method related to the cart lines
	public List<CartLine> listAvailable(int cartId);
	
	public CartLine getByCartAndProduct(int cartId,int productId);
	
	// shifted from UserDao
	//Update a cart** Add a cart
	 boolean updateCart(Cart cart); 

}
