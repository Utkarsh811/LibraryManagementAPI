package com.example.LibraryManagementApi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(unique = true)
    private String isbn;
    private String title;
    private String author;
    private String description;
    private String genre;
    private Integer publyear;
    private Integer copavail;
    private String publisher;
    private String coverimgurl;

    // constructor

    public library() {
    }

    public library(int id, String isbn, String title, String author, String description, String genre, Integer publyear,
            Integer copavail, String publisher, String coverimgurl) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.publyear = publyear;
        this.copavail = copavail;
        this.publisher = publisher;
        this.coverimgurl = coverimgurl;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for isbn
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for genre
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Getter and Setter for publyear
    public int getPublyear() {
        return publyear;
    }

    public void setPublyear(int publyear) {
        this.publyear = publyear;
    }

    // Getter and Setter for copavail
    public int getCopavail() {
        return copavail;
    }

    public void setCopavail(int copavail) {
        this.copavail = copavail;
    }

    // Getter and Setter for publisher
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // Getter and Setter for coverimgurl
    public String getCoverimgurl() {
        return coverimgurl;
    }

    public void setCoverimgurl(String coverimgurl) {
        this.coverimgurl = coverimgurl;
    }

}
