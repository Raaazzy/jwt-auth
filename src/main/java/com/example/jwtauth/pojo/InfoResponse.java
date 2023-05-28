package com.example.jwtauth.pojo;

import com.example.jwtauth.models.ERole;
import com.example.jwtauth.models.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InfoResponse {
    private String username;
    private String email;
    private List<String> roles;

    public InfoResponse(String username, String email, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.roles = new ArrayList<>();
        for (var role : roles) {
            switch (role.getName()) {
                case ROLE_ADMIN:
                    this.roles.add("admin");
                    break;
                case ROLE_CHEF:
                    this.roles.add("chef");
                    break;
                case ROLE_CUSTOMER:
                default:
                    this.roles.add("customer");
                    break;
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
