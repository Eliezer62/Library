package com.buixzy.mylibrary.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.dtos.AuthorDTO;
import com.buixzy.mylibrary.entities.Author;
import com.buixzy.mylibrary.exceptions.AuthorNotFoundException;
import com.buixzy.mylibrary.repositories.AuthorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorService {
    @Autowired
    AuthorRepository rep;
    
    public Author createByDTO(AuthorDTO dto){
        return rep.save(dto.toAuthor());
    }

    public List<Author> findAll() {
        return rep.findAll();
    }

    public Author findById(Long id) throws AuthorNotFoundException {
        return rep.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

    public Author patchById(Long id, Map<String, Object> fields) throws AuthorNotFoundException {
        Author author = rep.findById(id).orElseThrow(AuthorNotFoundException::new);
        fields.forEach((field, value) -> {
            switch (field) {
                case "name":
                    author.setName((String)value);
                    break;

                case "bibliography":
                    author.setBibliography((String)value);
                    break;

                case "nationality":
                    author.setNationality((String)value);
                    break;

                default:
                    break;
            }
        });

        return rep.save(author);
    }

    public Author updateById(Long id, AuthorDTO dto)
    {
        rep.findById(id).orElseThrow(AuthorNotFoundException::new);
        Author author = dto.toAuthor();
        author.setId(id);
        return rep.save(author);
    }

    public void deleteById(Long id)
    {
        rep.deleteById(id);
    }
}
