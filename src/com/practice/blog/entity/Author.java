package com.practice.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="author")
public class Author {

	// annotate the class as an entity and map to db table
	
	// define the fields
	
	// annotate the fields with db column names
	
	// ** set up mapping to AuthorDetail entity
	
	// create constructors
	
	// generate getter/setter methods
	
	// generate toString() method

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="author_detail_id")
	private AuthorDetail authorDetail;
	
	// mappedBy refers to author property in course class, and the join column property on
	// that property, which is author_id to get the blogEntries
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="author", 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, 
					CascadeType.MERGE, CascadeType.DETACH})
	private List<BlogEntry> blogEntries;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE, 
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="blog_entry_follower",
			joinColumns=@JoinColumn(name="author_id"),
			inverseJoinColumns=@JoinColumn(name="follower_id"))
	private List<Follower> followers;
	
	
	public Author() {
		
	}

	public Author(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public void addBlogEntries(BlogEntry tempBlogEntry) {
		if (blogEntries == null) {
			blogEntries = new ArrayList<>();
		}
		blogEntries.add(tempBlogEntry);
		tempBlogEntry.setAuthor(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AuthorDetail getAuthorDetail() {
		return authorDetail;
	}

	public void setAuthorDetail(AuthorDetail authorDetail) {
		this.authorDetail = authorDetail;
	}
	

	public List<BlogEntry> getBlogEntries() {
		return blogEntries;
	}

	public void setBlogEntries(List<BlogEntry> blogEntries) {
		this.blogEntries = blogEntries;
	}

	
	public List<Follower> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Follower> followers) {
		this.followers = followers;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", AuthorDetail=" + authorDetail + "]";
	}
	
	
}






