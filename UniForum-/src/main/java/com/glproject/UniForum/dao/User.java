package com.glproject.UniForum.dao;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class represent a user who has an account
 * 
 * @author julia
 *
 */
@PersistenceCapable
public class User {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected Long id = null;

	String username;
	String email;
	String password;
	Boolean admin;
	

	public User() {
		super();
		admin = false;
	}

	public User(String username) {
		super();
		this.username = username;
		admin = false;
		
	}
	
	public User(String username, String password) {
		this(username);
		this.password = password;
		admin = false;
	}

	public Long getId() {
		return id;
	}

	public String getUserame() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	/**
	 * return true if the user has this specific password
	 * @param password: the password tested
	 * @return
	 */
	public boolean hasPassword(String password) {
		return this.password.equals(password);
	}

	
	@Override
	public String toString() {
		return "User [ID=" + id + ", username=" + username + ", email=" + email +
				"password=" + password + "]";
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		return getId().equals(user.getId());
	}

}



