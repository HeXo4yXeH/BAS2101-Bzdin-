package com.example.demo.interfaces;

import com.example.demo.Exceptions.InvalidTokenException;
import com.example.demo.Exceptions.UnknownIdentifierException;
import com.example.demo.Exceptions.UserAlreadyExistException;
import com.example.demo.model.ApplicationUser;
import com.example.demo.model.ApplicationUserRole;
import com.example.demo.model.UserData;
import com.example.demo.repository.UserRepository;

import com.example.demo.repository.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.demo.security.GoogleAuthenticator.generateSecretKey;

@Service("userService")
@RequiredArgsConstructor
public class UserRegistrationService  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void register(UserData user) throws UserAlreadyExistException {

        //Let's check if user already registered with us
        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        ApplicationUser applicationUser = new ApplicationUser();
        BeanUtils.copyProperties(user, applicationUser);
        applicationUser.setSecretOauthCode(generateSecretKey());
        encodePassword(applicationUser, user);
        applicationUser.setRole(ApplicationUserRole.USER);
        userRepository.save(applicationUser);
    }
    @Override
    public void ChangePassword(ApplicationUser user,String newPassworrd){
        user.setPassword(passwordEncoder.encode(newPassworrd));
        //!!!
        userRepository.save(user);
    }


    @Override
    public boolean checkIfUserExist(String email) {
        var user = userRepository.findByEmail(email).isPresent();
        return user;
    }

    @Override
    public void sendRegistrationConfirmationEmail(ApplicationUser user) {

    }

    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        return false;
    }

    @Override
    public ApplicationUser getUserById(String id) throws UnknownIdentifierException {
        return null;
    }

    private void encodePassword( ApplicationUser applicationUser, UserData user){
        applicationUser.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}