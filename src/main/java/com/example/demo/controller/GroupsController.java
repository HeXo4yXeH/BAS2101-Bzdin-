package com.example.demo.controller;

import com.example.demo.model.Groups;
import com.example.demo.repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class GroupsController {

    @Autowired
    GroupsRepository GroupsRepository;

    private final GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Groups>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Groups> Group = new ArrayList<Groups>();

            if (title == null)
                GroupsRepository.findAll().forEach(Group::add);
            else
                GroupsRepository.findByTitleContaining(title).forEach(Group::add);

            if (Group.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Group, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @Cacheable(key = "#id",value = "Group")
    @GetMapping("/groups/{id}")
    public ResponseEntity<Groups> getTutorialById(@PathVariable("id") long id) {
//        Optional<Groups> tutorialData = GroupsRepository.findById(id);
        Groups tutorialData = groupsService.getGroupsForId(id);

        if (tutorialData.toString() !="") {
            return new ResponseEntity<>(tutorialData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/groups")
    public ResponseEntity<Groups> createTutorial(@RequestBody Groups group) {
    //public ResponseEntity<Groups> createTutorial(@PathVariable("title") String title1) {
        try {
            //Groups group1 = new Groups();
            //group1.setTitle(title1);
            Groups _tutorial = GroupsRepository
                    .save(group);
                    //.save(new Groups(title1));
                    //.save(new Groups(group.getTitle()));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            //return new ResponseEntity<>(new Groups(title1), HttpStatus.OK);
        }
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<Groups> updateGroup(@PathVariable("id") long id, @RequestBody Groups group) {
        Optional<Groups> tutorialData = GroupsRepository.findById(id);

        if (tutorialData.isPresent()) {
            Groups _tutorial = tutorialData.get();
            _tutorial.setTitle(group.getTitle());
            return new ResponseEntity<>(GroupsRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<HttpStatus> deleteGroup(@PathVariable("id") long id) {
        try {
            GroupsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/groups")
    public ResponseEntity<HttpStatus> deleteAllGroups() {
        try {
            GroupsRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}