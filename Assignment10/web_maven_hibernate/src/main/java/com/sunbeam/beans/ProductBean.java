package com.sunbeam.beans;

import java.util.List;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.entities.Product;

public class ProductBean {
/*
 * Bind request parameter to java Beans properties
 */
	private String name;
	private double price;
	private int quantity;
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	private long categoryId;
	private long productId;
	// dependency - dao layer i/f
	private ProductDao productDao;
	
	public ProductBean() {
		//create dao instance
		productDao = new ProductDaoImpl();
		System.out.println("product bean created ...");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
	/*
	 * Add B. Logic method to add a product
	 */
	
	public String addNewProduct()
	{
		System.out.println("In add Product "+categoryId);
		// 1. create a transient product
		Product product = new Product(name, price,quantity);
		
		return productDao.addProduct(categoryId, product);
	}
	
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	
	public String purchaseProduct() {
		System.out.println("in purchase " + quantity + " " + productId);
		return productDao.purchaseProduct(productId, quantity);
	}
}
