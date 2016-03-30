package br.com.haircutter.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.haircutter.core.enums.UserRoleEnum;

@Entity
@Table(name = "user_role")
public class UserRole {

	@Id
	@Column(name = "username")
	private String username;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserRoleEnum role;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
	private User user;

	public UserRole() {

	}

	public UserRole(String username, UserRoleEnum role) {
		super();
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserRoleEnum getRole() {
		return role;
	}

	public void setRole(UserRoleEnum role) {
		this.role = role;
	}

}
