package br.com.haircutter.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "establishment_employee")
public class EstablishmentEmployee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "establishment_cnpj")
	private String establishmentCnpj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "deleted")
    private Boolean deleted;

	public EstablishmentEmployee() {

	}

    public EstablishmentEmployee(String establishmentCnpj, User user, Date creationTime, Date lastModifiedDate,
                                 Boolean deleted) {
        this.establishmentCnpj = establishmentCnpj;
        this.user = user;
        this.creationTime = creationTime;
        this.lastModifiedDate = lastModifiedDate;
        this.deleted = deleted;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
