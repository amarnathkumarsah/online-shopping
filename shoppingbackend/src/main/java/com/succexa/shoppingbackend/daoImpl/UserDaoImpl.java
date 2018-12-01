package com.succexa.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.succexa.shoppingbackend.dao.UserDao;
import com.succexa.shoppingbackend.dto.Address;
import com.succexa.shoppingbackend.dto.Cart;
import com.succexa.shoppingbackend.dto.Product;
import com.succexa.shoppingbackend.dto.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean add(User user) {
		try {

			sessionFactory.getCurrentSession().persist(user);
			return true;	
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAdress(Address address) {
		try {

			sessionFactory.getCurrentSession().persist(address);
			return true;	
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}	
		}

	

	@Override
	public User getUserByEmail(String email) {
		try {
			return sessionFactory
					.getCurrentSession()
					.createQuery("from User where email=:email",User.class)
					.setParameter("email", email)
					//.getSingleResult();
					.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getBillingAddress(User user) {
		try {
			return sessionFactory
					.getCurrentSession()
					.createQuery("from Address where user_id=:user and billing=:billing ",Address.class)
					.setParameter("user", user)
					.setParameter("billing",true)
					.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddresses(User user) {
		try {
			return sessionFactory
					.getCurrentSession()
					.createQuery("from Address where user=:user and Shipping=:shipping",Address.class)
					.setParameter("user", user)
					.setParameter("shipping",true)
					.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	

}
