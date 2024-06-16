package com.userservice.userserviceforauth.services.userserviceImpl;

import com.userservice.userserviceforauth.exception.ExceptionMessage;
import com.userservice.userserviceforauth.exception.customException.NotAValidUser;
import com.userservice.userserviceforauth.exception.customException.UserNotFoundException;
import com.userservice.userserviceforauth.models.Token;
import com.userservice.userserviceforauth.models.User;
import com.userservice.userserviceforauth.repositories.TokenRepository;
import com.userservice.userserviceforauth.repositories.UserRepository;
import com.userservice.userserviceforauth.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    //not using @Autowired, using constructor injection
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    UserServiceImplementation(UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository){
        this.userRepository = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
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
        Optional<User> optionalUser = userRepository.findByEmail(email);
        //handle exception
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND + " with email "+ email);
        }

        // if user exist valid user password with store password at the time of authentication
        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            //handle exception, user is not authencated and send httpstatus code and well defined message to end user
            throw new NotAValidUser(ExceptionMessage.NOT_A_VALID_USER);
        }
        //login successfull, generate token
        //generate token and save in the repository
        Token token = generateToken(user); //this token without id
        Token savedToken = tokenRepository.save(token);//this token with id, because we got it after save it in the database
        return savedToken;
    }

    private Token generateToken(User user){
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //generte random 128 characters alphanumeric string
        Token token = new Token();
        token.setExpireAt(expiryDate);
        token.setValue(RandomStringUtils.randomAlphabetic(128));
        token.setUser(user);
        return token;
    }

    @Override
    public void logOut(String token) {

    }

    @Override
    public User validateToken(String token) {
        return null;
    }
}
