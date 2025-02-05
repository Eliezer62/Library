package com.buixzy.mylibrary.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.dtos.CopyDTO;
import com.buixzy.mylibrary.entities.Copy;
import com.buixzy.mylibrary.enums.StateCopies;
import com.buixzy.mylibrary.exceptions.CopyNotFoundException;
import com.buixzy.mylibrary.repositories.CopyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CopyService {
    @Autowired
    CopyRepository rep;
    BookService servBook;

    public Copy createByDTO(CopyDTO dto) {
        Copy copy = dto.toCopy();
        copy.setBook(servBook.findById(dto.bookId()));
        return rep.save(copy);
    }

    public List<Copy> findAll(){
        return rep.findAll();
    }

    public Copy findById(Long id) {
        return rep.findById(id).orElseThrow(CopyNotFoundException::new);
    }

    public Copy patchById(Long id, Map<String, Object> fields) {
        Copy copy = rep.findById(id).orElseThrow(CopyNotFoundException::new);
        fields.forEach((field, value) -> {
            switch (field) {
                case "state":
                    copy.setState(StateCopies.fromString((String)value));
                    break;

                case "code":
                    copy.setCode((Integer)value);;
                    break;

                case "available":
                   copy.setAvailable((Boolean)value);
                    break;

                case "bookId":
                    copy.setBook(servBook.findById((Long)value));
                    break;
            
                default:
                    break;
            }
        });
        return rep.save(copy);
    }

    public Copy updateById(Long id, CopyDTO dto) {
        rep.findById(id).orElseThrow(CopyNotFoundException::new);
        Copy copy = dto.toCopy();
        copy.setId(id);
        copy.setBook(servBook.findById(dto.bookId()));
        return rep.save(copy);
    }

    public void deleteById(Long id) {
        rep.deleteById(id);
    }
}
