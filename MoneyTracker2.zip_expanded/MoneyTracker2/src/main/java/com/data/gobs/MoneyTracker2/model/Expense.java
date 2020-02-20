package com.data.gobs.MoneyTracker2.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="expense")
public class Expense {
	
	@Id
	private Long expId;
	
	private Instant dateTime;
	
	private String descrp;
	
	private String location;
	
	@ManyToOne
	private Category category;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	


	public Long getExpId() {
		return expId;
	}
	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Expense [expId=" + expId + ", descrp=" + descrp + ", location=" + location + ", dateTime=" + dateTime
				+ ", user=" + user + ", category=" + category + "]";
	}

	public Expense(Long expId, String descrp, String location, Instant dateTime, User user, Category category) {
		super();
		this.expId = expId;
		this.descrp = descrp;
		this.location = location;
		this.dateTime = dateTime;
		this.user = user;
		this.category = category;
	}

	public void setExpId(Long expId) {
		this.expId = expId;
	}

	public String getDescrp() {
		return descrp;
	}

	public void setDescrp(String descrp) {
		this.descrp = descrp;
	}

	public Instant getDateTime() {
		return dateTime;
	}

	public void setDateTime(Instant dateTime) {
		this.dateTime = dateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
