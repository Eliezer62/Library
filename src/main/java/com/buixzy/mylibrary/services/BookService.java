package com.buixzy.mylibrary.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.dtos.BookDTO;
import com.buixzy.mylibrary.entities.Book;
import com.buixzy.mylibrary.exceptions.BookNotFoundException;
import com.buixzy.mylibrary.repositories.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    BookRepository rep;
    AuthorService serviceAuthor;

    public Book createByDTO(BookDTO dto) {
        Book book = dto.toBook();
        book.setAuthor(serviceAuthor.findById(dto.authorId()));
        return rep.save(book);
    }

    public List<Book> findAll()
    {
        return rep.findAll();
    }

    public Book findById(Long id)
    {
        return rep.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public Book patchById(Long id, Map<String, Object> fields) {
        Book book = rep.findById(id).orElseThrow(BookNotFoundException::new);
        fields.forEach( (field, value) -> {
            switch (field) {
                case "name":
                    book.setName((String)value);
                    break;

                case "isbn":
                    book.setISBN((String)value);
                    break;

                case "ean":
                    book.setEAN((String)value);
                    break;

                case "publisher":
                    book.setPublisher((String)value);
                    break;

                case "publisherDate":
                    book.setPublisherDate((Date)value);
                    break;

                case "pages":
                    book.setPages((Integer)value);
                    break;

                case "synopsis":
                    book.setSynopsis((String)value);
                    break;

                case "authorId":
                    book.setAuthor(serviceAuthor.findById((Long)value));
            
                default:
                    break;
            }
        });

        return rep.save(book);
    }

    public Book updateById(Long id, BookDTO dto)
    {
        rep.findById(id).orElseThrow(BookNotFoundException::new);
        Book book = dto.toBook();
        book.setId(id);
        book.setAuthor(serviceAuthor.findById(dto.authorId()));
        return rep.save(book);
    }

    public void deleteById(Long id){
        rep.deleteById(id);
    }
}
