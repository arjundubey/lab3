package com.lab3.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="users")
public class User {
    
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String first_name;
    private String last_name;
    private String avatar;
    private String skills;
    private String phone;
    private String about;
    private String email;
    private String password;
    private String type;
    
    /*
    public User(String first_name,String last_name,String avatar,String skills,String phone,String about,String email,String password,String type) {
    	
    	this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
        this.skills = skills;
        this.phone = phone;
        this.about = about;
        this.email = email;
        this.password = password;
        this.type = type;
        
    } */
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	

}