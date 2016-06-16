package br.com.haircutter.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "establishment_evaluation")
public class EstablishmentEvaluation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="username")
	private String username;
	
	@Column(name="establishment_cnpj")
	private String establishmentCnpj;
	
	@Column(name="status")
	private String status;
	
	@Column(name="rating")
	private Integer rating;
	
	@Column(name="establishment_service_id")
	private Long establishmentServiceId;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

	public EstablishmentEvaluation() {
		super();
	}

	public EstablishmentEvaluation(Long id, String description, String username, String establishmentCnpj,
			String status, Integer rating, Long establishmentServiceId, Date creationTime, Date lastModifiedDate) {
		super();
		this.id = id;
		this.description = description;
		this.username = username;
		this.establishmentCnpj = establishmentCnpj;
		this.status = status;
		this.rating = rating;
		this.establishmentServiceId = establishmentServiceId;
		this.creationTime = creationTime;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEstablishmentCnpj() {
		return establishmentCnpj;
	}

	public void setEstablishmentCnpj(String establishmentCnpj) {
		this.establishmentCnpj = establishmentCnpj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Long getEstablishmentServiceId() {
		return establishmentServiceId;
	}

	public void setEstablishmentServiceId(Long establishmentServiceId) {
		this.establishmentServiceId = establishmentServiceId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
