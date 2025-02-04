package com.buixzy.mylibrary.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @OneToMany(mappedBy = "copy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Copy> copies;
}
