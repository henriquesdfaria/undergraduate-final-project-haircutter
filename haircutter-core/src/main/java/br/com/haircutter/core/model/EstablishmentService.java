package br.com.haircutter.core.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "establishment_service")
public class EstablishmentService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "establishment_cnpj")
    private String establishmentCnpj;

    @Column(name = "service")
    private String service;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "deleted")
    private Boolean deleted;

    public EstablishmentService() {

    }

    public EstablishmentService(Long id, String establishmentCnpj, String service, String description, BigDecimal price, Date creationTime, Date lastModifiedDate, Boolean deleted) {
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
