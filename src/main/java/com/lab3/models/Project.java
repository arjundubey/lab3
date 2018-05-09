package com.lab3.models;


import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity // This tells Hibernate to make a table out of this class
@Table(name="projects")
public class Project {
    
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
	private String name;
	private Integer  user_id;
	private Integer win_bid_id ;
	private String  slug;
	private String  description;
	private String   file;
	private Double   min_budget;
	private Double  max_budget;
	private String   skills;
	private String  status;
	private Integer  bid_winner;
	
	@Column(
		    name = "created_at", 
		    nullable = false, 
		    updatable = false
		)
	private Timestamp created_at; 
	
	
	private Timestamp updated_at;
	
	@Transient
    private User bid_winner_details;
	
	@Transient
	private Bid win_bid_details;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getBid_winner() {
		return bid_winner;
	}

	public void setBid_winner(Integer bid_winner) {
		this.bid_winner = bid_winner;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Double getMin_budget() {
		return min_budget;
	}
	
	

	public void setMin_budget(Double min_budget) {
		this.min_budget = (Double)min_budget;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getMax_budget() {
		return max_budget;
	}

	public void setMax_budget(Double max_budget) {
		this.max_budget = (Double)max_budget;
	}
	

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getWin_bid_id() {
		return win_bid_id;
	}

	public void setWin_bid_id(Integer win_bid_id) {
		this.win_bid_id = win_bid_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public User getBid_winner_details() {
		return bid_winner_details;
	}

	public void setBid_winner_details(User bid_winner_details) {
		this.bid_winner_details = bid_winner_details;
	}

	public Bid getWin_bid_details() {
		return win_bid_details;
	}

	public void setWin_bid_details(Bid win_bid_details) {
		this.win_bid_details = win_bid_details;
	}
	
	

}