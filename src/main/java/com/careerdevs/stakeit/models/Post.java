package com.careerdevs.stakeit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id" )
    @JsonIgnoreProperties("posts")
    private Profile profile;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("post")
    private Set<Comment> comments;



    public Post(){

    }

    public Post(Long id, String title, String body, Profile profile) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}

