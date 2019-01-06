package com.practice.blog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.blog.entity.Author;
import com.practice.blog.entity.AuthorDetail;
import com.practice.blog.entity.BlogEntry;
import com.practice.blog.entity.Comment;


public class CreateComments {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Author.class)
								.addAnnotatedClass(AuthorDetail.class)
								.addAnnotatedClass(BlogEntry.class)
								.addAnnotatedClass(Comment.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {				
			session.beginTransaction();
			
			
			// get BlogEntry
			int blogId = 11;
			BlogEntry tempBlogEntry = session.get(BlogEntry.class, blogId);
			System.out.println("*** BlogEntry: " + tempBlogEntry);
			
			// create comments
			Comment comment1 = new Comment("Nice.  Very nice.");
			Comment comment2 = new Comment("Needs salt.");
			
			// add comments to blogEntry
			tempBlogEntry.addComment(comment1);
			tempBlogEntry.addComment(comment2);
			
			System.out.println("*** Comments to save: " + comment1.toString() + comment2.toString());
			
			// save blogEntry (update)
			session.save(tempBlogEntry);
			
		
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





