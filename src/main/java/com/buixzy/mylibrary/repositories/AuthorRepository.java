package com.buixzy.mylibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buixzy.mylibrary.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    
}
