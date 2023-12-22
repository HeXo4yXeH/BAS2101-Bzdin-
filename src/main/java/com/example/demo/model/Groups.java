package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

//@RedisHash("Group")
@Entity
@Table(name = "Groups")
public class Groups implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "title")
    private String title;

    public Groups() {

    }
    public Groups(String title) {
        this.title = title;
    }
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + title + "]";
    }

}
