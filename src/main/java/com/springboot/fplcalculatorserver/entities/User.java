package com.springboot.fplcalculatorserver.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String lastName;
	
	@Column(nullable=false)
	private boolean active;
	
	@Column(nullable=false)
	private long fplManagerId;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
	private List<Role> roles;
	
	public User() {}

	public User(long fplManagerId, String email, String password, String name, String lastName) {
		this.fplManagerId = fplManagerId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = true;
	}

	public User(long id, String email, String password, String name, String lastName, boolean active,
			List<Role> roles) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRole(List<Role> roles) {
		this.roles = roles;
	}

	public long getFplManagerId() {
		return fplManagerId;
	}

	public void setFplManagerId(long fplManagerId) {
		this.fplManagerId = fplManagerId;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", lastName="
				+ lastName + ", active=" + active + ", roles=" + roles + "]";
	}

	

}
