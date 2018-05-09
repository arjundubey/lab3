package com.lab3.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="bids")
public class Bid {
    
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
	private Integer project_id;
	private Integer user_id;
	private String description;
	private String time;
	private Double price;
	
	@Column(
		    name = "created_at", 
		    nullable = false, 
		    updatable = false
		)
	private Timestamp created_at; 
	
	
	private Timestamp updated_at;
	
	@Transient
    private User userdata;
	
	@Transient
    private Project projectdata;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProject_id() {
		return project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
 
	
	public void setUserDetails(User user) {
		this.userdata = user;
	}
	
	public User getUserDetails() {
		return this.userdata;
	}
	
	public void setProjectDetails(Project project) {
		this.projectdata = project;
	}
	
	public Project getProjectDetails() {
		return this.projectdata;
	}

}