package com.userservice.userserviceforauth.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class Token extends BaseModel{
    private String value;
    /*
    Cardinality between Token and user
    User    Token
    1       M
    1        1
    1:M
    * */
    @ManyToOne
    private User user;
    private Date expireAt;

}
