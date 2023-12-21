package com.example.demo.controller;

import com.example.demo.Exceptions.InvalidOldPasswordException;
import com.example.demo.Exceptions.UserAlreadyExistException;
import com.example.demo.interfaces.PasswordService;
import com.example.demo.model.ApplicationUser;
import com.example.demo.model.Groups;
import com.example.demo.model.UserData;
import com.example.demo.repository.UserRepository;
import com.example.demo.interfaces.UserDetailsServiceImpl;
import com.example.demo.interfaces.UserRegistrationService;
import com.example.demo.repository.UserService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    //    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @GetMapping("/registration")
//    public String registration() {
//        return "registration";
//    }
//    @PostMapping("/registration")
//    public ApplicationUser registerUser(@RequestBody ApplicationUser user) {
//        return userDetailsService.registerUser(user);
//    }
    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/registration")
    public String register(final @NotNull Model model){
        model.addAttribute("userData", new UserData());
        return "/registration";
    }

    @PostMapping("/registration")
    public String userRegistration(final @Valid UserData userData, final @NotNull BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", userData);
            return "/registration";
        }
        try {
            if(passwordService.IsPasswordValid(userData.getPassword())){
                userRegistrationService.register(userData);
            }else{
                return "/registration";
            }
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
            model.addAttribute("registrationForm", userData);
            return "/registration";
        }
        return "redirect:/groups";
    }

    @PostMapping("/reset-password")
    public String ResetPassword(@RequestBody String password, @CurrentSecurityContext(expression="authentication?.name") String username) {
        if(userRepository.findByEmail(username).isPresent()){
            if(passwordService.IsPasswordValid(password)){
                userRegistrationService.ChangePassword(userRepository.findByEmail(username).get(),password);
                return "/registration";
            }else{
                return "/registration";
            }
        }
        return "/groups";
    }
}
