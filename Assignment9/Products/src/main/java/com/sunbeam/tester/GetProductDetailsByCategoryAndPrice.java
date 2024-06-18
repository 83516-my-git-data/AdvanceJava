package com.sunbeam.tester;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.entities.Category;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.util.Iterator;
import java.util.Scanner;

public class GetProductDetailsByCategoryAndPrice {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory();
			Scanner sc = new Scanner(System.in)
			) {
			ProductDao dao = new ProductDaoImpl();
			System.out.println("Enter Product Category And Min, Max price range : ");
			dao.getProductDetailsByCategoryAndPrice(Category.valueOf(sc.next().toUpperCase()),
					sc.nextDouble(), sc.nextDouble()).forEach(s->System.out.println(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
