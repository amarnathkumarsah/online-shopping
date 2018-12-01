package com.succexa.onlineshopping.controller;

import javax.faces.annotation.RequestMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.succexa.onlineshopping.service.CartService;
import com.succexa.shoppingbackend.dao.CartLineDao;
import com.succexa.shoppingbackend.dto.CartLine;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService; 
	
	@Autowired
	private CartLineDao cartLineDao;
	/*Before updateCart controller
	@RequestMapping("/show")
	public ModelAndView showCart() {
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("title","User Cart");
		mv.addObject("userClickShowCart",true);
		mv.addObject("cartLines",cartService.getCartLines());
		return mv;
		
	}*/
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result",required = false) String result) {
		ModelAndView mv = new ModelAndView("page");
		
		//if result is present
		if (result!=null) {
			//now that would be multiple case here
			switch (result) {
			case "updated":
				mv.addObject("message","CartLine has been updated successfully!");
			break;
			
			case "added":
				mv.addObject("message","CartLine has been added successfully!");
			break;
			
			case "deleted":
				mv.addObject("message","CartLine has been removed successfully!");
			break;
			
			case "maximum":
				mv.addObject("message","CartLine reached maximum(3)!");
			break;
			
			case "unavailable":
				mv.addObject("message","No more product available!");
			break;
			
			
			case "error":
			mv.addObject("message","Something went wrong!");
			break;
			
			default:
				break;
			}
		}
		
		mv.addObject("title","User Cart");
		mv.addObject("userClickShowCart",true);
		mv.addObject("cartLines",cartService.getCartLines());
		return mv;
		
	}
	
	@RequestMapping("/{cartLineId}/udpate")
	public String updateCart(@PathVariable int cartLineId,@RequestParam(name="Quant") int newProductQuant) {
		
		/*ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","User Cart");
		mv.addObject("userClickShowCart",true); 
		mv.addObject("cartLines",cartService.getCartLines()); benefit of redirect: */
		
		String response = cartService.updateCartLine(cartLineId,newProductQuant);
		
		return "redirect:/cart/show/?"+response;
	}
	
	@RequestMapping("/{cartLineId}/delete")
	public String deleteCartLine(@PathVariable int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show/?"+response;
	}
	
	@RequestMapping("/add/{productId}/product")
	public String addToCart(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show/?"+response;
	}
	
}
