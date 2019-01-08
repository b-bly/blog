package com.practice.blog;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.practice.blog.entity.Author;
import com.practice.blog.entity.AuthorDetail;
import com.practice.blog.entity.BlogEntry;
import com.practice.blog.entity.Comment;
import com.practice.blog.entity.Follower;
import com.practice.blog.entity.Role;
import com.practice.blog.entity.RoleName;
import com.practice.blog.entity.User;


public class CreateUsers {


	public static void main(String[] args) {
		
	

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Author.class)
								.addAnnotatedClass(AuthorDetail.class)
								.addAnnotatedClass(BlogEntry.class)
								.addAnnotatedClass(Comment.class)
								.addAnnotatedClass(Follower.class)
								.addAnnotatedClass(User.class)
								.addAnnotatedClass(Role.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {				
			session.beginTransaction();
			
			// Instantiate password Encoder
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			// create user
			User user = new User("alTheMan", "al@gmail.com");
			user.setPassword(passwordEncoder.encode("test123"));
			
			// Get role
			int roleId = 1;
			Role userRole = session.get(Role.class, roleId);
			System.out.println("Role: " + userRole);
			
			// Define roles

			List<Role> roles = new ArrayList<>();
			roles.add(userRole);
			user.setRoles(roles);
	
			// save user
			session.save(user);
			
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





