package com.practice.blog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.blog.entity.Author;
import com.practice.blog.entity.AuthorDetail;
import com.practice.blog.entity.BlogEntry;


public class CreateInstructor {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Author.class)
								.addAnnotatedClass(AuthorDetail.class)
								.addAnnotatedClass(BlogEntry.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// create the objects
		
//			
//			Author tempAuthor = 
//					new Author("Jack", "Jackson", "jack@gmail.com");
//			
//			AuthorDetail tempAuthorDetail =
//					new AuthorDetail(
//							
//							"I grew up in the mountains of Nebraska.");	
			
			Author tempAuthor = 
					new Author("Pat", "Patson", "pat@gmail.com");
			
			AuthorDetail tempAuthorDetail =
					new AuthorDetail("I'm a crackerjack juggler.");	
			
			// associate the objects
			
			tempAuthor.setAuthorDetail(tempAuthorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving author: " + tempAuthor);
			session.save(tempAuthor);					
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}





