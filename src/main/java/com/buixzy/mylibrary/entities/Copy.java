package com.buixzy.mylibrary.entities;

import com.buixzy.mylibrary.enums.StateCopies;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
    private StateCopies state;

    @Column(name = "code", columnDefinition = "INT")
    private Integer code;

    @Column(name = "available", columnDefinition = "BOOLEAN")
    private Boolean available;

    @ManyToOne
    @JsonIgnore
    private Book book;

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
