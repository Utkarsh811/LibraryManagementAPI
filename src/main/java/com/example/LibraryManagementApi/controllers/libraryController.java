package com.example.LibraryManagementApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagementApi.models.library;
import com.example.LibraryManagementApi.services.libraryService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

@RestController
@RequestMapping("/book")
public class libraryController {

    private libraryService ls;

    @Autowired
    public libraryController(libraryService ls) {
        this.ls = ls;
    }

    @GetMapping("/getcsrf-token")
    public CsrfToken getcsrf(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/add")
    public ResponseEntity<Object> postbook(@RequestBody library book) {
        return ls.addBook(book);
    }

    @PutMapping("/updatebody/{id}")
    public ResponseEntity<Object> updatebook(@PathVariable("id") int id, @RequestBody library book) {
        return ls.updateebook(id, book);
    }

    // updating only title where id =3
    // update/{isbn}?title="welcome to my Journey"&genre="message to life"
    @PutMapping("/update/{isbn}")
    public ResponseEntity<Object> updatebookbyTitleandGenre(@PathVariable("isbn") String isbn,
            @RequestParam(required = false) String title, @RequestParam(required = false) String genre) {
        return ls.updatetileGenre(isbn, title, genre);
    }

    @GetMapping("/getallbooks")
    public ResponseEntity<ArrayList<Object>> getallbooks() {
        return ls.getall();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletebyid(@PathVariable("id") int id) {
        return ls.deletebyid(id);
    }

}
