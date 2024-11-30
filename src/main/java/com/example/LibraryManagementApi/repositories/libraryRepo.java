package com.example.LibraryManagementApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.LibraryManagementApi.models.library;

import jakarta.transaction.Transactional;

@Repository
public interface libraryRepo extends JpaRepository<library, Integer> {

    public boolean existsByIsbn(String isbn);

    @Query("UPDATE library p SET p.title = ?2 , p.genre = ?3 where p.isbn  = ?1")
    @Modifying
    @Transactional
    public int updatebookbyTitleandGenre(String isbn, String title, String genre);

}
