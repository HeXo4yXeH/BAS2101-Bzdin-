package com.example.demo.repository;

import com.example.demo.Exceptions.InvalidTokenException;
import com.example.demo.Exceptions.UnknownIdentifierException;
import com.example.demo.Exceptions.UserAlreadyExistException;
import com.example.demo.model.ApplicationUser;
import com.example.demo.model.UserData;


public interface UserService {

    void register(final UserData user) throws UserAlreadyExistException;
    void ChangePassword(ApplicationUser user,String newPassworrd);
    boolean checkIfUserExist(final String email);
    void sendRegistrationConfirmationEmail(final ApplicationUser user);
    boolean verifyUser(final String token) throws InvalidTokenException;
    ApplicationUser getUserById(final String id) throws UnknownIdentifierException;

}
