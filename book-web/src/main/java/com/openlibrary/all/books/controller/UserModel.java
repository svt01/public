package com.openlibrary.all.books.controller;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class UserModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	@NotNull(message = "Firstname cannot be null")
	private String firstname;

	@NotNull(message = "Lastname cannot be null")
	private String lastname;

	@NotNull(message = "Email cannot be null")
	private String email;

	@NotNull(message = "Username cannot be null")
	private String username;

	@NotNull(message = "Password cannot be null")
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
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

	@Override
	public String toString() {
		return "UserModel [firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", username=" + username + ", password="
				+ password + "]";
	}

}
