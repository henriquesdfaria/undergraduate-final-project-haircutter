package br.com.haircutter.admin.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserRole> userRoles;

	public User() {

	}

	public User(String username, String name, String password,
				List<UserRole> userRoles) {
		this.username = username;
		this.name = name;
		this.password = password;
		this.userRoles = userRoles;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}
