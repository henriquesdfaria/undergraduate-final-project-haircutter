package br.com.haircutter.core.model;


import br.com.haircutter.core.enums.EstablishmentStatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "establishment")
public class Establishment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EstablishmentStatusEnum status;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_cpf")
    private String ownerCpf;

    @Column(name = "owner_email")
    private String ownerEmail;

    @Column(name = "owner_phone")
    private String ownerPhone;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public Establishment() {

    }

    public Establishment(String cnpj, String name, String description, Address address, String phone,
                         EstablishmentStatusEnum status, String ownerName, String ownerCpf, String ownerEmail,
                         String ownerPhone, Date creationTime, Date lastModifiedDate) {
        this.cnpj = cnpj;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.ownerName = ownerName;
        this.ownerCpf = ownerCpf;
        this.ownerEmail = ownerEmail;
        this.ownerPhone = ownerPhone;
        this.creationTime = creationTime;
        this.lastModifiedDate = lastModifiedDate;
    }

    public EstablishmentStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EstablishmentStatusEnum status) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerCpf() {
        return ownerCpf;
    }

    public void setOwnerCpf(String ownerCpf) {
        this.ownerCpf = ownerCpf;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
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
