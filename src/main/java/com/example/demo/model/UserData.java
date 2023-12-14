package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.io.Serializable;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserData implements Serializable {

    // Геттеры и сеттеры для поля Name
    @NotEmpty(message = "First name can not be empty")
    private String name;

    // Геттеры и сеттеры для поля email
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Please provide a valid email id")
    private String email;

    // Геттеры и сеттеры для поля password
    @NotEmpty(message = "Password can not be empty")
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}