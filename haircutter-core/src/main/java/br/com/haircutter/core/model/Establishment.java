package br.com.haircutter.core.model;


import br.com.haircutter.core.enums.EstablishmentStatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Column(name = "deny_cause")
    private String denyCause;

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

    @Transient
    private List<EstablishmentEmployee> establishmentEmployees;

    @Transient
    private List<ProfessionalService> professionalServices;

	@Transient
	private List<EstablishmentService> establishmentServices;

    @Transient
    private List<Schedule> establishmentSchedules;

    @Transient
    private Integer ratingMedium;

    @Transient
    private List<EstablishmentEvaluation> evaluations;

	public Establishment() {
		super();
	}

    public Establishment(String cnpj, String name, String description, Address address, String phone, EstablishmentStatusEnum status, String denyCause, String ownerName, String ownerCpf, String ownerEmail, String ownerPhone, Date creationTime, Date lastModifiedDate, List<EstablishmentEmployee> establishmentEmployees, List<ProfessionalService> professionalServices, List<EstablishmentService> establishmentServices, List<Schedule> establishmentSchedules) {
        this.cnpj = cnpj;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.denyCause = denyCause;
        this.ownerName = ownerName;
        this.ownerCpf = ownerCpf;
        this.ownerEmail = ownerEmail;
        this.ownerPhone = ownerPhone;
        this.creationTime = creationTime;
        this.lastModifiedDate = lastModifiedDate;
        this.establishmentEmployees = establishmentEmployees;
        this.professionalServices = professionalServices;
        this.establishmentServices = establishmentServices;
        this.establishmentSchedules = establishmentSchedules;
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

    public Integer getRatingMedium() {
        return ratingMedium;
    }

    public void setRatingMedium(Integer ratingMedium) {
        this.ratingMedium = ratingMedium;
    }

    public List<EstablishmentEvaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<EstablishmentEvaluation> evaluations) {
        this.evaluations = evaluations;
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

    public EstablishmentStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EstablishmentStatusEnum status) {
        this.status = status;
    }

    public String getDenyCause() {
        return denyCause;
    }

    public void setDenyCause(String denyCause) {
        this.denyCause = denyCause;
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

    public List<EstablishmentEmployee> getEstablishmentEmployees() {
        return establishmentEmployees;
    }

    public void setEstablishmentEmployees(List<EstablishmentEmployee> establishmentEmployees) {
        this.establishmentEmployees = establishmentEmployees;
    }

    public List<ProfessionalService> getProfessionalServices() {
        return professionalServices;
    }

    public void setProfessionalServices(List<ProfessionalService> professionalServices) {
        this.professionalServices = professionalServices;
    }

    public List<EstablishmentService> getEstablishmentServices() {
        return establishmentServices;
    }

    public void setEstablishmentServices(List<EstablishmentService> establishmentServices) {
        this.establishmentServices = establishmentServices;
    }

    public List<Schedule> getEstablishmentSchedules() {
        return establishmentSchedules;
    }

    public void setEstablishmentSchedules(List<Schedule> establishmentSchedules) {
        this.establishmentSchedules = establishmentSchedules;
    }
}
