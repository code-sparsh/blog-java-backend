package com.example.welcome.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "blogs")
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "blogID")
    private UUID id;
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String data;
    private String author;
    private String imageURL;

    public Blog(String title,String data, String author) {
        this.title = title;
        this.data = data;
        this.author = author;
    }

    public Blog(String title,String data, String author, String imageURL) {
        this.title = title;
        this.data = data;
        this.author = author;
        this.imageURL = imageURL;
    }

    public Blog() {

    }
}
