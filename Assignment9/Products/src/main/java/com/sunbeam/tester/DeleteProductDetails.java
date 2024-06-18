package com.sunbeam.tester;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.util.Scanner;

public class DeleteProductDetails {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory();
			Scanner sc = new Scanner(System.in)
			) {			
			ProductDao dao = new ProductDaoImpl();
			System.out.println("Enter Product name : ");
			System.out.println(dao.deleteProductDetails(sc.next()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
