package com.data.gobs.MoneyTracker2.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@Entity
@Table(name="user_table")
public class User {

	@Id
	private Long userId;
	
	private String name;
	
	private String email;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String name, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", name=" + name + ", email=" + email + "]";
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
