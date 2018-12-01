package com.succexa.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.succexa.shoppingbackend.dao.CategoryDao;
import com.succexa.shoppingbackend.dto.Category;


@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	/*static List<Category> categories = new ArrayList<>();
	
	static{
		
		 * adding first category
		 
		Category category = new Category(); 
		category.setId(1);
		category.setName("Mobile");
		category.setDescription("very powerfull mobile");
		category.setImageURL("/baby.png");
		category.setAvtive(true);
		categories.add(category);
		
	
		 * adding second category
		 
		category = new Category(); 
		category.setId(2);
		category.setName("Cloth");
		category.setDescription("very shiny cloths");
		category.setImageURL("/cloths.png");
		category.setAvtive(true);
		categories.add(category);
		
	}
	*/
	@Override
	public List<Category> list() {
		try {
			String selectActiveCategory= "FROM Category WHERE is_active = :active ";
			@SuppressWarnings("unchecked")
			Query<Category> query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
			query.setParameter("active", true);
			
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
		
		
	}

	//To get a Single category based on id
	@Override
	public Category getCategory(int categoryId) {
		
	return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(categoryId));
	}

	@Override
	//@Transactional   removed because every method shoud follow transaction, set at class level
	public boolean add(Category catetory) {
	
		try {
			sessionFactory.getCurrentSession().persist(catetory);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean update(Category catetory) {
		try {
			sessionFactory.getCurrentSession().update(catetory);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category catetory) {
		try {
			catetory.setAvtive(false);
			
			sessionFactory.getCurrentSession().update(catetory);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

/*	@Override
	public Category getCategoryByProductId(int productId) {
		return  sessionFactory.getCurrentSession().createQuery("FROM Category where productId = :productId").setParameter("productId", productId).getClass(Category.class);
	}
*/
}