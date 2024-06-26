package com.userservice.userserviceforauth.controllers;

import com.userservice.userserviceforauth.dtos.LoginRequestDto;
import com.userservice.userserviceforauth.dtos.LogoutRequestDto;
import com.userservice.userserviceforauth.dtos.SignupRequestDto;
import com.userservice.userserviceforauth.dtos.UserDto;
import com.userservice.userserviceforauth.models.Token;
import com.userservice.userserviceforauth.models.User;
import com.userservice.userserviceforauth.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Now injecting depencdency using construction injection
     */
    private UserService userService;
    UserController(UserService userService){
        this.userService = userService;
    }
    //Important:- here we are not integrating with jwt token, we will creating our own token of string type
    //and eventually we will be integrating jwt token
    /**
     * because we creating new user to the DB, will use POST Mapping
     */
    @PostMapping("/signup")
    public UserDto singUp(@RequestBody SignupRequestDto signupRequestDto){
        User user = userService.SignUp(signupRequestDto.getName(), signupRequestDto.getEmail(), signupRequestDto.getPassword());
        return UserDto.from(user);//this is consider as good practice to convert user to userDTo in dto's class itself
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto loginRequestDto){
        Token token = userService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );
        /*
        //token that we get from this method
        {
    "id": 2,
    "deleted": null,
    "value": "eWbnzZvwwWLzJVgQMJerCtaXqztQbPBexYgEPcxFlyRwjVoHgiABVmbkIZYTBCUNGBuueKEqatfHkIrgvcSpZTyUNSlHfEaKYgwholuUVRajchunQQjwbgTivHDnlFBC",
    "user": {
        "id": 1,
        "deleted": null,
        "name": "Mohammad Ayan",
        "hashedPassword": "$2a$10$tAF2GzrF4erbaq3dcpzhYu9BnGEuOCdjqonWkVuGtm01fL9Eu0UsS",
        "email": "ayanBro@gmail.com",
        "roles": [],
        "isEmailVerified": true
    },
    "expireAt": "2024-07-15T18:30:00.000+00:00"
}
        * */

        return token;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogoutRequestDto logoutRequestDto){
        return null;
    }

    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable String token){
        return null;
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return null;
    }


}
