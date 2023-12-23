package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationUser {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;

    private String password;
    @Column(name = "SecretOauthCode")
    private String SecretOauthCode;

    public void setSecretOauthCode(String secretOauthCode) {
        SecretOauthCode = secretOauthCode;
    }
    //    private boolean block;
//    private Integer attempts;

    //    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> role;
    @Enumerated(value = EnumType.STRING)
    @Column(name ="role")
    private ApplicationUserRole role;

}
