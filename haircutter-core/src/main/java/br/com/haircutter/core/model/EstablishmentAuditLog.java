package br.com.haircutter.core.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "establishment_audit_log")
public class EstablishmentAuditLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "establishment_cnpj")
	private String establishmentCnpj;

	@Column(name = "author")
	private String author;

	@Column(name = "action")
	private String action;

	@Column(name = "date")
	private Date date;

	public EstablishmentAuditLog() {

	}

	public EstablishmentAuditLog(Long id, String establishmentCnpj, String author, String action, Date date) {
		super();
		this.id = id;
		this.establishmentCnpj = establishmentCnpj;
		this.author = author;
		this.action = action;
		this.date = date;
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

	public void setEstablishment(String establishmentCnpj) {
		this.establishmentCnpj = establishmentCnpj;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
