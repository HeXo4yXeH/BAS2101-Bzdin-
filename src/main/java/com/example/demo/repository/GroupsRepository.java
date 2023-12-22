package com.example.demo.repository;

import com.example.demo.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
    List<Groups> findByTitleContaining(String title);

}
