package br.com.haircutter.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "establishment_admin")
public class EstablishmentAdmin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "establishment_cnpj")
	private String establishmentCnpj;

	@Column(name = "username")
	private String username;

	public EstablishmentAdmin() {

	}

	public EstablishmentAdmin(Long id, String establishmentCnpj, String username) {
		super();
		this.id = id;
		this.establishmentCnpj = establishmentCnpj;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstablishmentCnpj() {
		return establishmentCnpj;
	}

	public void setEstablishmentCnpj(String establishmentCnpj) {
		this.establishmentCnpj = establishmentCnpj;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
