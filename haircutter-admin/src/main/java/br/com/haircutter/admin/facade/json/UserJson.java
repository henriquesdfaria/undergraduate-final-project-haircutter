package br.com.haircutter.admin.facade.json;

import br.com.haircutter.admin.enums.UserRoleEnum;

import java.util.Date;


public class UserJson {

    private String username;

    private String name;

    private String password;

    private UserRoleEnum role;

    private Boolean enabled;

    private UserProfileJson profile;

    private String email;

    private Boolean emailVerified;

    private Date creationTime;

    private Date lastModifiedDate;

    public UserJson() {

    }

    public UserJson(String username, String name, String password, UserRoleEnum role, Boolean enabled, UserProfileJson profile, String email, Boolean emailVerified, Date creationTime, Date lastModifiedDate) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.profile = profile;
        this.email = email;
        this.emailVerified = emailVerified;
        this.creationTime = creationTime;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public UserProfileJson getProfile() {
        return profile;
    }

    public void setProfile(UserProfileJson profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
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
