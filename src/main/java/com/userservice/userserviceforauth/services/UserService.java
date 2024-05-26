package com.userservice.userserviceforauth.services;

import com.userservice.userserviceforauth.models.Token;
import com.userservice.userserviceforauth.models.User;

public interface UserService {
    public User SignUp(String name, String email, String password);
    public Token login(String email, String password);

    public void logOut(String token);
    public User validateToken(String token);



}
