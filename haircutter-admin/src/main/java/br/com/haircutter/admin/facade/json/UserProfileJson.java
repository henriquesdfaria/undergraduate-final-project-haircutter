package br.com.haircutter.admin.facade.json;

import java.util.Date;

public class UserProfileJson {

    private String username;

    private String cpf;

    private String phone;

    private AddressJson address;

    private Date creationTime;

    private Date lastModifiedDate;

    public UserProfileJson() {

    }

    public UserProfileJson(String username, String cpf, String phone, AddressJson address, Date creationTime, Date lastModifiedDate) {
        this.username = username;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
        this.creationTime = creationTime;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressJson getAddress() {
        return address;
    }

    public void setAddress(AddressJson address) {
        this.address = address;
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
