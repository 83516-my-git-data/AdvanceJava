package com.sunbeam.dao;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeam.entities.Category;
import com.sunbeam.entities.Product;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> getProductDetailsByCategoryAndPrice(Category cat, Double min, Double max) {
		String jpql = "select p from Product p where p.category=:ct and p.price between :min and :max";
		List<Product> products = null;
		
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			products = session.createQuery(jpql, Product.class)
					.setParameter("ct", cat).setParameter("min", min).setParameter("max", max)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		
		return products;
	}


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


	@Override
	public String applyDiscount(Category cat, Double discount) {
		String msg = "Applying discount failed !";
		String jpql = "update Product p set p.price=p.price-:disc where p.category=:cat";
		
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			int rows = session.createQuery(jpql).setParameter("disc", discount)
					.setParameter("cat", cat).executeUpdate();
			tx.commit();
			msg = rows+" "+"Product discounted...";
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}		return msg;
	}


	@Override
	public String purchaseProduct(Long id, int quantity) {
		String msg ="Product is not available..";
		
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			Product product = session.get(Product.class, id);
			if(product.getQuantity() >= quantity)
			{
				product.setQuantity(product.getQuantity() - quantity);
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


	@Override
	public String deleteProductDetails(String name) {
		String msg = "Product details not deleted !";
		String jpql = "select p from Product p where p.name=:nm";
		
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			Product product = session.createQuery(jpql, Product.class)
					.setParameter("nm", name)
					.getSingleResult();
			session.delete(product);
			tx.commit();
			msg = "Product details deleted successfully...";
			
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return msg;
	}
}
