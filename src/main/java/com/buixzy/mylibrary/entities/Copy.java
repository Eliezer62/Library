package com.buixzy.mylibrary.entities;

import com.buixzy.mylibrary.enums.StateCopies;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GenerationType;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.buixzy.mylibrary.converters.StateCopiesToString;

@Entity
@Table(name = "copies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state")
    @Convert(converter = StateCopiesToString.class)
    private StateCopies state=StateCopies.NEW;

    @Column(name = "code", columnDefinition = "INT")
    private Integer code;

    @Column(name = "available", columnDefinition = "BOOLEAN")
    private Boolean available=true;

    @ManyToOne
    @JsonIgnore
    private Book book;

    @JsonProperty("book")
    public Map<String, Object> bookProperty()
    {
        Map<String, Object> map = new HashedMap<>();
        map.put("id", book.getId());
        map.put("name", book.getName());
        map.put("ISBN", book.getISBN());
        map.put("author", book.getAuthor().getName());
        return map;
    }

    public Boolean isAvailable()
    {
        return this.available;
    }

    public Boolean isLoanable()
    {
        if(state == StateCopies.DESTROYED || !available)
            return false;
        
        else
            return true;
    }
}
