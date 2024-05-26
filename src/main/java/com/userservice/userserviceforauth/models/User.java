package com.userservice.userserviceforauth.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class User extends BaseModel{
    private String name;
    private String hashedPassword;
    private String email;
    /*
    Cardinality between user and roles
    User        Role
    1           M
    M           1
    M:M
    * */
    @ManyToMany
    private List<Role> roles;
    private Boolean isEmailVerified;
}
