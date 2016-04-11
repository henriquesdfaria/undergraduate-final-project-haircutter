package br.com.haircutter.admin.facade.json;

import br.com.haircutter.admin.enums.UserRoleEnum;


public class UserRoleJson {

    private String username;

    private UserRoleEnum role;

    private UserJson user;

    public UserRoleJson() {

    }

    public UserRoleJson(String username, UserRoleEnum role) {
        super();
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

}
