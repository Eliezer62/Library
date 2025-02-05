package com.buixzy.mylibrary.dtos;

import java.util.Date;

import com.buixzy.mylibrary.entities.Book;

public record BookDTO(
    String name,
    String ISBN,
    String EAN,
    String publisher,
    Date publisherDate,
    Integer pages,
    String synopsis,
    Long authorId
) {
    public Book toBook()
    {
        Book book = new Book();
        book.setName(name);
        book.setISBN(ISBN);
        book.setEAN(EAN);
        book.setPublisher(publisher);
        book.setPublisherDate(publisherDate);
        book.setPages(pages);
        book.setSynopsis(synopsis);
        
        return book;
    }
}
