package com.example.demo.controller;

import com.example.demo.model.Groups;
import com.example.demo.repository.GroupsRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupsService {

    private final GroupsRepository groupsRepository;

    @Cacheable(value = "GroupCache")
    public Groups getGroupsForId(Long id) {
        return groupsRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

}