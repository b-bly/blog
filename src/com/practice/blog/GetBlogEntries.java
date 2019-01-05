package com.practice.blog;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.blog.entity.Author;
import com.practice.blog.entity.AuthorDetail;
import com.practice.blog.entity.BlogEntry;


public class GetBlogEntries {

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
			System.out.println("Author: " + author);
			// get blogEntries
			List<BlogEntry> blogs = author.getBlogEntries();
			System.out.println("Blog entries: " + blogs);
			
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





