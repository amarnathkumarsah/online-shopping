package com.succexa.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.succexa.onlineshopping.model.UserModel;
import com.succexa.shoppingbackend.dao.CartLineDao;
import com.succexa.shoppingbackend.dao.ProductDao;
import com.succexa.shoppingbackend.dto.Cart;
import com.succexa.shoppingbackend.dto.CartLine;
import com.succexa.shoppingbackend.dto.Product;

@Transactional
@Service
public class CartService {

	@Autowired
	private CartLineDao cartLineDao;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ProductDao productDao;

	// returns the cart of the user who has logged in
	public Cart getCart() {
		return ((UserModel) httpSession.getAttribute("userModel")).getCart();
	}

	// returns the entire cart lines
	public List<CartLine> getCartLines() {
		Cart cart = this.getCart();
		  //return cartLineDao.list(cart.getId()); //sir work
		  return cartLineDao.listAvailable(cart.getId());//my work
	}

	public String updateCartLine(int cartLineId, int newProductQuant) {
		// fatch thar particulat cartLine based on the id
		System.out.println(".............new "+newProductQuant);
		CartLine cartLine = cartLineDao.get(cartLineId);
		// if we are not getting as we expect than, so to handle
		if (cartLine == null) {
			return "result=error";
		} else {
			/*
			 * because i am going to find new total based on the quant of product that we
			 * are trying to add
			 */
			double oldTotal = cartLine.getTotal();

			Product product = cartLine.getProduct();
			// there may be a posibilty that
			// the quant that i have sent is 3 but right now the product quant is only one.

			if (product.getQuantity() >= newProductQuant) {
				System.out.println("Oh 1 no "+product.getQuantity() +" "+newProductQuant);
				// than set the new product quant to max product qount
				
			

			// After preparing quant now set quant to cartLine
				
			cartLine.setProductCount(newProductQuant);

			cartLine.setBuyingPrice(product.getUnitPrice()); // beacuse price of the product may change thats why
																// product.getUnitPrice()

			// now set the total, we are setting everything new thats why
			cartLine.setTotal(newProductQuant * product.getUnitPrice());

			// its time to call the CartLineDao.update to update that particulat cartLine
			cartLineDao.update(cartLine);

			// now update the cart as well

			// now fatch the cart

			Cart cart = this.getCart();

			// for update in prices we need to make grant total as zero and then set the
			// last updated grandToatl so
			// all we need to update the GrandTotal, so first get the last grand total that
			// we have and then seprate the old total and add the newTotal by using
			// cartLine.setTotal() method
			double newTotal = cartLine.getProductCount() * product.getUnitPrice();

			// 18000 18000 3*18000
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());

			cartLineDao.updateCart(cart);

			

			// science we are having refrence to our session cart object only so anyting
			// that we change is going to be reflected
			// and also it would be persisted inside the database.

			// this is how our else part looks like where we are updating the cartLine with
			// the quant.

			// if the cartLine is not present it is going to result as error otherwise we
			// are going to update that particular cartLine
			
			
			
			return "result=updated";
			}
			else {
				System.out.println("Oh no "+product.getQuantity() +" "+newProductQuant);
				//if  porduct is less then
				return "result=unavailable";
			}
		}
		// so we have complited our cart service now we are going to handle the
		// cartController show method to handle the reponse by the cartService that is
		// our 3rd step
		// it means we need to handle this result in our controller. so we have to
		// modify showCart method to handle this result

	}

	public String deleteCartLine(int cartLineId) {
		CartLine cartLine = cartLineDao.get(cartLineId);
		if (cartLine == null) {
			return "result=error";
		} else {
			// before deleting cartLine form cart first we have subtract the cartLine price
			// form the cart
			// fetch the current cart
			Cart cart = this.getCart();

			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		    cart.setCartLines(cart.getCartLines() - 1);  
			 
			
			// now update the cart
			cartLineDao.updateCart(cart);

			// now delete the cartline form cart
			cartLineDao.delete(cartLine);
			return "result=deleted";

		}
	}

	// i am trying to be a developer

	public String addCartLine(int productId) {
		String response = null;
		System.out.println("1///////////////////////////////////////////");
		// how can we insure that the product that we are adding is not already present
		// inside the cart

		Cart cart = this.getCart();
		CartLine cartLine = cartLineDao.getByCartAndProduct(cart.getId(), productId);

		//if cartLine is not created or this product is not in user cart
		
		if (cartLine == null) {
			System.out.println("2///////////////////////////////////////////");
			// add a new cartLine
			cartLine = new CartLine();

			// fetch the product
			Product product = productDao.get(productId);

			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);

			cartLine.setTotal(product.getUnitPrice()); // this total will be same as product price since this is first
														// product of this cartLine

			cartLine.setAvailable(true);

			// now we will persist our cartLine into the cart
			cartLineDao.add(cartLine);

			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());

			// update the cart of the user
			cartLineDao.updateCart(cart);

			response = "result=added";

		} else { // if this product already in cartline then
			
			System.out.println("3///////////////////////////////////////////");
			// fisrt check the cartLine has reached the maximum count which is three in our
			// case

			if (cartLine.getProductCount() < 3) {
				// update the product quant for that cartLine

				response = this.manageAddCartline(cartLine, productId);
			
			} else {
				// prepare the response
				response = "result=maximum";
			}
			 
		}

		return response;

	}

	private String manageAddCartline(CartLine cartLine, int productId) {
		System.out.println("4///////////////////////////////////////////");
		Product product = productDao.get(productId);
		
		if (product.getQuantity()>cartLine.getProductCount()) {
			
			Cart cart = this.getCart();
			
			
			cartLine.setBuyingPrice(product.getUnitPrice());
			//cartLine.setCartId(cart.getId());
			//cartLine.setProduct(product);
			
			// increment the count by 1
			cartLine.setProductCount(cartLine.getProductCount() + 1);
			
			// update the total of the cartLine
			cartLine.setTotal(cartLine.getTotal() + cartLine.getBuyingPrice());

			//bussiness logic for adding cart if product is available == false in that cartLine  
			if (cartLine.isAvailable()==false) {
				cart.setCartLines(cart.getCartLines()+1);
			}else {
				//number of cartLine should be same if cartLine is already there
				cart.setCartLines(cart.getCartLines());
			}
			
			cartLine.setAvailable(true);
			
			// update the cartLine
			cartLineDao.update(cartLine);

			// prepare cart to update
			
			
			
			cart.setGrandTotal(cart.getGrandTotal() + product.getUnitPrice());

			// update the cart
			cartLineDao.updateCart(cart);

			return "result=updated";
			
			
		}
		else {
			//System.out.print(" i am unvaliable----------------------------------------------");
			return "result=unavailable";
		}
		 
		
	}

}
