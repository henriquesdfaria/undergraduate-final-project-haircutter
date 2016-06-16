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
@Table(name = "establishment_evaluation_comentary")
public class EstablishmentEvaluationComentary implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="establishment_evaluation_id")
	private Long establishmentEvaluationId;
	
	@Column(name="username")
	private String username;
	
    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

	public EstablishmentEvaluationComentary() {
		super();
	}

	public EstablishmentEvaluationComentary(Long id, String description, Long establishmentEvaluationId,
			String username, Date creationTime, Date lastModifiedDate) {
		super();
		this.id = id;
		this.description = description;
		this.establishmentEvaluationId = establishmentEvaluationId;
		this.username = username;
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

	public Long getEstablishmentEvaluationId() {
		return establishmentEvaluationId;
	}

	public void setEstablishmentEvaluationId(Long establishmentEvaluationId) {
		this.establishmentEvaluationId = establishmentEvaluationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
