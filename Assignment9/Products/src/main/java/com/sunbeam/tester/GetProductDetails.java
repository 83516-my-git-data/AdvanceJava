package com.sunbeam.tester;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.util.Scanner;

public class GetProductDetails {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory();
			Scanner sc = new Scanner(System.in)
			) {
			
			ProductDao dao = new ProductDaoImpl();
			System.out.println("Enter Product Id : ");
			System.out.println(dao.getProductDetailsById(sc.nextLong()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
