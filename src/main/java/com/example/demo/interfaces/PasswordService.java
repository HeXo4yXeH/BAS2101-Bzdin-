package com.example.demo.interfaces;

import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    public boolean IsPasswordValid(String password){
        int minLength = 7;

        boolean ContainsLowerCase = password.matches(".*[a-z].*");

        boolean ContainsUpperCase = password.matches(".*[A-Z].*");

        boolean ContainsDigit = password.matches(".*\\d.*");

        boolean ContainsSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        return password.length() >= minLength && ContainsLowerCase && ContainsUpperCase && ContainsDigit && ContainsSpecialChar;
    }
}
