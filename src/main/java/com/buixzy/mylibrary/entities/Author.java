package com.buixzy.mylibrary.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 255, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "bibliography", columnDefinition = "TEXT")
    private String bibliography;

    @Column(name = "nationality", columnDefinition = "VARCHAR(255)")
    private String nationality;
}
