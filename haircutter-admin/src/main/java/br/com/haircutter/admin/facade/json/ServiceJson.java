package br.com.haircutter.admin.facade.json;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hfaria on 4/24/16.
 */
public class ServiceJson {

    private Long id;

    private String establishmentCnpj;

    private String service;

    private String description;

    private BigDecimal price;

    private Date creationTime;

    private Date lastModifiedDate;

    private Boolean deleted;

    public ServiceJson() {

    }

    public ServiceJson(Long id, String establishmentCnpj, String service, String description, BigDecimal price, Date creationTime, Date lastModifiedDate, Boolean deleted) {
        this.id = id;
        this.establishmentCnpj = establishmentCnpj;
        this.service = service;
        this.description = description;
        this.price = price;
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
