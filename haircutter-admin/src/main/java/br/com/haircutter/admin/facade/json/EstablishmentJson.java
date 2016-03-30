package br.com.haircutter.admin.facade.json;

import java.util.Date;

import br.com.haircutter.admin.enums.EstablishmentStatusEnum;

public class EstablishmentJson {

	private String cnpj;

	private String name;

	private String description;

	private AddressJson address;

	private String phone;

	private EstablishmentStatusEnum status;

	private String ownerName;

	private String ownerCpf;

	private String ownerEmail;

	private String ownerPhone;

	private Date creationTime;

	private Date lastModifiedDate;

	public EstablishmentJson() {

	}

	public EstablishmentJson(String cnpj, String name, String description, AddressJson address, String phone,
			EstablishmentStatusEnum status, String ownerName, String ownerCpf, String ownerEmail, String ownerPhone,
			Date creationTime, Date lastModifiedDate) {
		super();
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

	public AddressJson getAddress() {
		return address;
	}

	public void setAddress(AddressJson address) {
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
