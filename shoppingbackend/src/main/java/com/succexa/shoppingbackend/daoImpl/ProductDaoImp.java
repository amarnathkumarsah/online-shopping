package com.succexa.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.succexa.shoppingbackend.dao.ProductDao;
import com.succexa.shoppingbackend.dto.Product;
@Repository
@Transactional
public class ProductDaoImp implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Product> list() {
		
		try {
			return sessionFactory
					.getCurrentSession()
					.createQuery("from Product",Product.class)
					.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean add(Product product)  {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}



	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			sessionFactory.getCurrentSession().update(product);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public Product get(int productId) {
		try {
			
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
						
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		try {
			return sessionFactory
					.getCurrentSession()
					.createQuery("from Product where active=:active",Product.class)
					.setParameter("active", true)
					.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		try {
			return sessionFactory
					.getCurrentSession()
					.createQuery("from Product where active=:active order by id",Product.class)
					.setParameter("active", true)
					.setFirstResult(0)
					.setMaxResults(count)
					.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> listActiveProductByCategory(int categoryId) {
		try {
			return sessionFactory
					.getCurrentSession()
					.createQuery("from Product where active=:active and categoryId=:categoryId",Product.class)
					.setParameter("active", true)
					.setParameter("categoryId", categoryId)
					.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
