package com.practice.blog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.blog.entity.Author;
import com.practice.blog.entity.AuthorDetail;
import com.practice.blog.entity.BlogEntry;
import com.practice.blog.entity.Comment;
import com.practice.blog.entity.Follower;


public class CreateFollowers {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Author.class)
								.addAnnotatedClass(AuthorDetail.class)
								.addAnnotatedClass(BlogEntry.class)
								.addAnnotatedClass(Comment.class)
								.addAnnotatedClass(Follower.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {				
			session.beginTransaction();
			
			
			// get Authors
			int theId = 1;
			int id2 = 2;
			Author author = session.get(Author.class, theId);
			Author author2 = session.get(Author.class, id2);
			
			// create follower
			Follower follower = new Follower("Al", "Alderman", "al@gmail.com");
			
			// add authors to follower
			follower.addAuthor(author);
			follower.addAuthor(author2);
			
			// save follower
			session.save(follower);
			
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





