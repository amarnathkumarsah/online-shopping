package com.succexa.shoppingbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_line")
public class CartLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne //uni directional one to one ralationship because as soon as get the cartLine i want entire detail for the product .
	
	private Product product;
	
	@Column(name="cart_id") //but i am not using any relation for cart and cartline just to have faster execution and avoid using joins by my hibernate 
	private int cartId;	    //because i will be having the cartId through the session which i am going to use and that will help me to complite this entire cart module
							//	so i am avoiding to write onetoone annotation (only faster the execution of my project), thats why directly i am using here cartId here .
	
	
	@Column(name="product_count")
	private int productCount;
	
	@Column(name="total")
	private double total;
	
	@Column(name="buying_price")
	private double buyingPrice;
	
	@Column(name="is_available")
	private boolean available;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "CartLine [id=" + id + ", cartId=" + cartId + ", productCount=" + productCount + ", total=" + total
				+ ", buyingPrice=" + buyingPrice + ", available=" + available + "]";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
