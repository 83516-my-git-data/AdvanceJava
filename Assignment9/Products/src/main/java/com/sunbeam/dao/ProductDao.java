package com.sunbeam.dao;

import java.util.List;

import com.sunbeam.entities.Category;
import com.sunbeam.entities.Product;

public interface ProductDao {

	String addProduct(Product product); 
	
	Product getProductDetailsById(Long productId);
	
	List<Product> getProductDetailsByCategoryAndPrice(Category cat, Double min, Double max);
	
	String applyDiscount(Category cat, Double discount);
	
	String purchaseProduct(Long id, int quantity);
	
	String deleteProductDetails(String name);
}
