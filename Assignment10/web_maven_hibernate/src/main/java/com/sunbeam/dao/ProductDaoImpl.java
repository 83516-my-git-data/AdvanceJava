package com.sunbeam.dao;

import com.sunbeam.entities.Product;
import org.hibernate.*;
import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeam.entities.Category;
import com.sunbeam.entities.Product;

public class ProductDaoImpl implements ProductDao {

	@Override
	public String addProduct(Long categoryId, Product newProduct) {
		String msg = "added failed";
		
		Session session=getFactory().getCurrentSession();
		// 2. begin tx
		Transaction tx=session.beginTransaction();
		try {
			// 3. get category from it's id -
			Category category = session.get(Category.class, categoryId);
			if(category != null) 
			{
				//valid category - persistent
				// 4. establish bi-directinal association between category and Product
				category.addProduct(newProduct); // state of the category (persistent
				session.persist(newProduct);//Is this required ???? NO
				msg = "added the product";
			}
			tx.commit();
			/*
			 * session.flush( -> dirty checking
			 */
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			// re throw the exc to the caller
			throw e;
		}
		return msg;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> product = null;
		String jpql = "select p from Product p";
		
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			product = session.createQuery(jpql, Product.class).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return product;
	}

	@Override
	public Product getProductDetails(Long productId) {
		Product product = null;
		
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			product = session.get(Product.class, productId);
			
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return product;
	}

	@Override
	public String purchaseProduct(Long productId, int qty) {
		String msg = "Product purchase failed";
		
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			Product product = session.get(Product.class, productId);
			if(product != null && product.getAvailableQty() >= qty)
			{
				product.setAvailableQty(product.getAvailableQty() - qty);
				msg = "Product purchased successfully...";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}		
		return msg;
	}

}
