package com.userservice.userserviceforauth.services.userserviceImpl;

import com.userservice.userserviceforauth.models.Token;
import com.userservice.userserviceforauth.models.User;
import com.userservice.userserviceforauth.repositories.UserRepository;
import com.userservice.userserviceforauth.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    //not using @Autowired, using constructor injection
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    UserServiceImplementation(UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User SignUp(String name,
                       String email,
                       String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        //to store password, we will use Bcryptpassword (which is internally using Hashing + Salting), thus, we will have to import this library and it's dependency
        //because we can store password as plane text (because of privacy or hacking)
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        user.setIsEmailVerified(true);
        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) {
        return null;
    }

    @Override
    public void logOut(String token) {

    }

    @Override
    public User validateToken(String token) {
        return null;
    }
}
