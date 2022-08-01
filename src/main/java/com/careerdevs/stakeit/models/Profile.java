package com.careerdevs.stakeit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private String jobTitle;
    private Integer karma = 0;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY )
    @JsonIgnoreProperties("profile")
    private Set<Post> posts;


    public Profile(){

    }

    public Profile(Long id, String name, Integer age, String jobTitle, Integer karma, Set<Post> posts) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.jobTitle = jobTitle;
        this.karma = karma;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getKarma() {
        return karma;
    }

    public void setKarma(Integer karma) {
        this.karma = karma;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}

