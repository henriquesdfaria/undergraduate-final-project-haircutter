package br.com.haircutter.admin.facade.json;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hfaria on 4/24/16.
 */
public class EstablishmentServiceJson {

    private Long id;

    private String establishmentCnpj;

    private String service;

    private String description;

    private BigDecimal price;

    private Integer duration;

    private Date creationTime;

    private Date lastModifiedDate;

    private Boolean deleted;

    public EstablishmentServiceJson() {

    }

    public EstablishmentServiceJson(String establishmentCnpj, Long id, String service, String description, BigDecimal price, Integer duration, Date creationTime, Date lastModifiedDate, Boolean deleted) {
        this.establishmentCnpj = establishmentCnpj;
        this.id = id;
        this.service = service;
        this.description = description;
        this.price = price;
        this.duration = duration;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
