package com.sunbeam.tester;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;
import com.sunbeam.entities.Category;

public class ApplyDiscountByCategory {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); 
				Scanner sc = new Scanner(System.in)) {
			ProductDao dao = new ProductDaoImpl();
			System.out.println("Enter Category And Discount amount : ");
			System.out.println(dao.applyDiscount(Category.valueOf(sc.next().toUpperCase()), sc.nextDouble()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
