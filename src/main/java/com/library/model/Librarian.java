package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;

@Entity
@SecondaryTable(name = "USER")
public class Librarian {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "USER_ID", table = "USER")
	private String userId;
	@Column(name = "PASSWORD", table = "USER")
	private String password;
	@Column(name = "ROLE", table = "USER")
	private String role;
	@Column(name = "FULL_NAME")
	private String name;

	public Librarian() {
	}

	public Librarian(String userId, String password, String role, String name) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
