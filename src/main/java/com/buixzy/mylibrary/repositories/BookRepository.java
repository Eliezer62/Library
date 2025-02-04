package com.buixzy.mylibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buixzy.mylibrary.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
