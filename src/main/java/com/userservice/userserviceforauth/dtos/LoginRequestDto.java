package com.userservice.userserviceforauth.dtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;

}
