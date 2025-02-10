package com.buixzy.mylibrary.entities.user;

import com.buixzy.mylibrary.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identification;

    private String ddi;

    private String ddd;

    private String number;

    @ManyToOne
    @JsonIgnore
    private User user;
}
