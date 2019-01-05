package com.practice.blog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.blog.entity.Author;
import com.practice.blog.entity.AuthorDetail;
import com.practice.blog.entity.BlogEntry;


public class CreateBlogEntries {

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
			session.beginTransaction();
			
			
			// get Author
			int theId = 2;
			Author author = session.get(Author.class, theId);
			
			// create blogEntries
			BlogEntry blogEntry1 = new BlogEntry("Today was a good day.");
			BlogEntry blogEntry2 = new BlogEntry("Today was a very good day.");
			
			// add blogEntries to author
			author.addBlogEntries(blogEntry1);
			author.addBlogEntries(blogEntry2);
			
			// save blogEntries
			session.save(blogEntry1);
			session.save(blogEntry2);
		
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}





