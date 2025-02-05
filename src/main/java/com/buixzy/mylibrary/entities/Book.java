package com.buixzy.mylibrary.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(255)", length = 255)
    private String name;

    @Column(name = "isbn", columnDefinition = "VARCHAR(13)", length = 13)
    private String ISBN;

    @Column(name = "ean", columnDefinition = "VARCHAR(13)", length = 13)
    private String EAN;

    @Column(name = "publisher", columnDefinition = "VARCHAR(255)", length = 255)
    private String publisher;

    @Column(name = "publisher_date", columnDefinition = "DATE")
    private Date publisherDate;

    @Column(name = "pages", columnDefinition = "INT")
    private Integer pages;

    @Column(name = "synopsis", columnDefinition = "TEXT")
    private String synopsis;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Copy> copies;

    @ManyToOne
    @JsonIgnore
    private Author author;

    @JsonProperty("author")
    public Map<String, Object> jsonAuthor(){
        Map<String, Object> map = new HashedMap<>();
        if(!(author == null))
        {
            map.put("id", author.getId());
            map.put("name", author.getName());
        }

        return map;
    }

}
