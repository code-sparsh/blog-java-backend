package com.example.welcome.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "blogs")
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

    public Blog(String title,String data, String author) {
        this.title = title;
        this.data = data;
        this.author = author;
    }

    public Blog() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
