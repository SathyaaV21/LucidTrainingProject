/**
 * @author Sathyaa
 *
 */
package com.example.demo.model;

import java.util.HashSet;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;


import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="users")
public class User {

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", phonenumber=" + phonenumber + ", isUserStatusActive=" + isUserStatusActive + ", roles=" + roles
				+ "]";
	}

	@Id
	private String id;

	@NotBlank
	private String username;

	@Email
	private String email;

	@NotBlank
	@Size(min = 2)
	private String password;

	@Size(min = 10, max = 10)
	private String phonenumber;
	
	private boolean isUserStatusActive;
	
	@NotBlank
	private Set<Role> roles = new HashSet<>();

	public User() {
	}
//hi checking source tree push
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(String id, @NotBlank String username, @NotBlank @Size(min = 2) String password, @Email String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public boolean getIsUserStatusActive() {
		return isUserStatusActive;
	}
	
	public void setIsuserStatusActive(boolean isUserStatusActive) {
		this.isUserStatusActive = isUserStatusActive;
	}

	public User(String username, String email,String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		
	}

	
}
	