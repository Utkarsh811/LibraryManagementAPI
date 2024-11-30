package com.example.LibraryManagementApi.services;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementApi.models.library;
import com.example.LibraryManagementApi.repositories.libraryRepo;

@Service
public class libraryService {

    private libraryRepo lbRepo;

    @Autowired
    public libraryService(libraryRepo lbRepo) {
        this.lbRepo = lbRepo;
    }

    public ResponseEntity<Object> addBook(library book) {
        // check the db that existing book with same isbn exist in db
        // if not add otherwise send string already exist
        if (!lbRepo.existsByIsbn(book.getIsbn())) {
            System.out.println(lbRepo.existsByIsbn(book.getIsbn()));

            try {
                library savedbook = lbRepo.save(book);
                return new ResponseEntity<>(savedbook, HttpStatus.CREATED);

            } catch (Exception e) {
                // TODO: handle exception
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book already exists");

        }

    }

    public ResponseEntity<Object> updateebook(int id, library book) {
        if (lbRepo.existsById(id)) {
            if (!lbRepo.existsByIsbn(book.getIsbn())) {

                try {

                    library newbook = lbRepo.findById(id)
                            .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

                    newbook.setAuthor(book.getAuthor());
                    newbook.setCopavail(book.getCopavail());
                    newbook.setCoverimgurl(book.getCoverimgurl());
                    newbook.setDescription(book.getDescription());
                    newbook.setGenre(book.getGenre());
                    newbook.setTitle(book.getTitle());
                    newbook.setPublisher(book.getPublisher());
                    newbook.setPublyear(book.getPublyear());
                    newbook.setIsbn(book.getIsbn());

                    return new ResponseEntity<>(lbRepo.save(newbook), HttpStatus.OK);

                } catch (Exception e) {
                    // TODO: handle exception
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book with this ISSBN exists");

            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not exists");

        }
    }

    public ResponseEntity<Object> updatetileGenre(String isbn, String title, String genre) {
        if (lbRepo.existsByIsbn(isbn)) {

            try {
                int rowsaffected = lbRepo.updatebookbyTitleandGenre(isbn, title, genre);
                if (rowsaffected > 0) {

                    return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(" Not Updated successfully", HttpStatus.OK);

                }

            } catch (Exception e) {
                // TODO: handle exception
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }

        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not  exists");

        }
    }

    public ResponseEntity<ArrayList<Object>> getall() {
        ArrayList<Object> result = new ArrayList<>(lbRepo.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<String> deletebyid(int id) {
        if (lbRepo.existsById(id)) {

            try {
                lbRepo.deleteById(id);

                return new ResponseEntity<>("DELETED", HttpStatus.OK);

            } catch (Exception e) {
                // TODO: handle exception
                return new ResponseEntity<>("Error encountered while deleting", HttpStatus.BAD_REQUEST);

            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error encountered while deleting as book not exists already");

        }
    }

}
