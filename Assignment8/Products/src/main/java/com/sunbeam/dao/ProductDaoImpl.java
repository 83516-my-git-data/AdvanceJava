package com.sunbeam.dao;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeam.entities.Product;

public class ProductDaoImpl implements ProductDao {

	@Override
	public String addProduct(Product product) {
		String msg="Product not Added...";
		Session session=getFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		try {
			Serializable productId = session.save(product);
			tx.commit();
			msg="Product added successfully , with ID"+productId;
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}	
		return msg;
	}

	
	@Override
	public Product getProductDetailsById(Long productId) {
		Product product=null;
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			product=session.get(Product.class, productId);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return product;
	}
}
