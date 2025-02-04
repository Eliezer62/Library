package com.buixzy.mylibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buixzy.mylibrary.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
