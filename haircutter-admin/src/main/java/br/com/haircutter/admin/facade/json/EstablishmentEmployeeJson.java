package br.com.haircutter.admin.facade.json;

import java.util.Date;

public class EstablishmentEmployeeJson  {


	private Long id;
	private String establishmentCnpj;

    private UserJson user;

    private Date creationTime;

    private Date lastModifiedDate;

    private Boolean deleted;

	public EstablishmentEmployeeJson() {

	}

    public EstablishmentEmployeeJson(Long id, String establishmentCnpj, UserJson user, Date creationTime, Date lastModifiedDate, Boolean deleted) {
        this.id = id;
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

    public UserJson getUser() {
        return user;
    }

    public void setUser(UserJson user) {
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
