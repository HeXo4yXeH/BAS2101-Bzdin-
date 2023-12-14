package com.example.demo.repository;

import com.example.demo.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<ApplicationUser, UUID> {
    Optional<ApplicationUser> findByName(String name);
    Optional<ApplicationUser> findByEmail(String email);
}
