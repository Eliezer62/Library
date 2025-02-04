package com.buixzy.mylibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buixzy.mylibrary.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    
}
