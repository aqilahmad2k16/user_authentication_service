package com.userservice.userserviceforauth.dtos;

import com.userservice.userserviceforauth.models.Token;
import lombok.Data;

@Data
public class LogoutRequestDto {
    private String token;
}
