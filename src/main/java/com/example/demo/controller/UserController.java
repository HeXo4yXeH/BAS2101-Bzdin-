package com.example.demo.controller;

import com.example.demo.model.ApplicationUser;
import com.example.demo.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
//@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository UserRepository;


    @GetMapping("/user")
    public String hello(@CurrentSecurityContext(expression="authentication?.name") String username) {
        return "Hello, " + username + "!";
    }

    @GetMapping("/users")
    public ResponseEntity<List<ApplicationUser>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<ApplicationUser> Users = new ArrayList<ApplicationUser>();

            if (title == null)
                UserRepository.findAll().forEach(Users::add);
            else
                UserRepository.findByEmail(title).ifPresent(Users::add);

            if (Users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}