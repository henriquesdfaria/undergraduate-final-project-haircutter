package br.com.haircutter.admin.facade.json;

import java.util.Date;
import java.util.List;


public class UserJson {

    private String username;

    private String name;

    private String password;

    private List<UserRoleJson> userRoles;

    private Boolean enabled;

    private Date creationTime;

    private Date lastModifiedDate;

    public UserJson() {

    }

    public UserJson(String username, String name, String password, List<UserRoleJson> userRoles, Boolean enabled, Date creationTime, Date lastModifiedDate) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.userRoles = userRoles;
        this.enabled = enabled;
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

    public List<UserRoleJson> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleJson> userRoles) {
        this.userRoles = userRoles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
