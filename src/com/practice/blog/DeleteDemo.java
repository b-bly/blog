package com.practice.blog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.blog.entity.Author;
import com.practice.blog.entity.AuthorDetail;
import com.practice.blog.entity.Student;


public class DeleteDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Author.class)
					.addAnnotatedClass(AuthorDetail.class)
					.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			
			
			session.beginTransaction();
			
			int theId = 1;
			Author tempInstructor = session.get(Author.class, theId);
			System.out.println("Found instructor: " + tempInstructor);
			
			
			if (tempInstructor != null) {
				// cascades to delete instructorDetail too!
				session.delete(tempInstructor);
			}
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
	}

}
