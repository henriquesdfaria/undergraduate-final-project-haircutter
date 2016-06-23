package br.com.haircutter.admin.facade.json;

import java.util.Date;

/**
 * Created by hfaria on 6/22/16.
 */
public class EstablishmentEvaluationJson {

    private Long id;
    private String description;
    private String username;
    private String establishmentCnpj;
    private String status;
    private Integer rating;
    private Long establishmentServiceId;
    private Date creationTime;
    private Date lastModifiedDate;

    public EstablishmentEvaluationJson() {

    }

    public EstablishmentEvaluationJson(Long id, String description, String username, String establishmentCnpj, String status, Integer rating, Long establishmentServiceId, Date creationTime, Date lastModifiedDate) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
