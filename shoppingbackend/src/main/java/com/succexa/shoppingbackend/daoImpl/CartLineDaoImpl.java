package com.succexa.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.succexa.shoppingbackend.dao.CartLineDao;
import com.succexa.shoppingbackend.dto.Cart;
import com.succexa.shoppingbackend.dto.CartLine;
@Repository
@Transactional
public class CartLineDaoImpl implements CartLineDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public CartLine get(int id) {
	   try {
		return sessionFactory.getCurrentSession().get(CartLine.class, Integer.valueOf(id));
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	}

	
	@Override
	public boolean add(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartLine) {
		try {
			cartLine.setAvailable(false);
			cartLine.setProductCount(0);
			cartLine.setTotal(0.0);
			
			sessionFactory.getCurrentSession().update(cartLine);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> list(int cartId) {
		try {
			return sessionFactory
					.getCurrentSession()
					.createQuery("from CartLine where cartId=:cartId",CartLine.class)
					.setParameter("cartId", cartId)
					.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	// Extra Bussiness methods.
	@Override
	public List<CartLine> listAvailable(int cartId) {
		
			return sessionFactory
					.getCurrentSession()
					.createQuery("from CartLine where cartId=:cartId AND available = :available",CartLine.class)
					.setParameter("cartId", cartId)
					.setParameter("available", true)
					.getResultList();
		
		
		
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
	 try {
		 return sessionFactory
			.getCurrentSession()
			.createQuery("from CartLine where cartId=:cartId AND product.id = :productId",CartLine.class)
			.setParameter("cartId", cartId)
			.setParameter("productId", productId)
			.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	
	//shifted from UserDaoImpl
	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;	
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}	
		
	}
	
}
