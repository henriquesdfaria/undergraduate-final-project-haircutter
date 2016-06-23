package br.com.haircutter.admin.facade.json;

import java.util.Date;

/**
 * Created by hfaria on 6/22/16.
 */
public class ComplaintJson {

    private Long id;
    private String description;
    private String username;
    private String establishmentCnpj;
    private String status;
    private Long establishmentEvaluationId;
    private Date creationTime;
    private Date lastModifiedDate;

    public ComplaintJson(Long id, String description, String username, String establishmentCnpj, String status, Long establishmentEvaluationId, Date creationTime, Date lastModifiedDate) {
        this.id = id;
        this.description = description;
        this.username = username;
        this.establishmentCnpj = establishmentCnpj;
        this.status = status;
        this.establishmentEvaluationId = establishmentEvaluationId;
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

    public Long getEstablishmentEvaluationId() {
        return establishmentEvaluationId;
    }

    public void setEstablishmentEvaluationId(Long establishmentEvaluationId) {
        this.establishmentEvaluationId = establishmentEvaluationId;
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
