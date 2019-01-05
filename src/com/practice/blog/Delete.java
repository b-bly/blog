package com.practice.blog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.blog.entity.Author;
import com.practice.blog.entity.AuthorDetail;

public class Delete {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Author.class)
					.addAnnotatedClass(AuthorDetail.class)
					.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			
			
			session.beginTransaction();
			
			int theId = 3;
			Author tempAuthor = session.get(Author.class, theId);
			System.out.println("Found author: " + tempAuthor);
			
			
			if (tempAuthor != null) {
				// cascades to delete instructorDetail too!
				session.delete(tempAuthor);
			}
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
