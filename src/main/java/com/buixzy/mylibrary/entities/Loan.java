package com.buixzy.mylibrary.entities;

import java.util.Date;

import com.buixzy.mylibrary.converters.StatusLoanConverter;
import com.buixzy.mylibrary.enums.StatusLoan;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Convert;
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
@Table(name = "loans")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date loanDate;
    
    private Date dueDate;

    private Date returnDate;

    @Convert(converter = StatusLoanConverter.class)
    private StatusLoan status;

    private Boolean renew;

    private String notes;

    @ManyToOne
    @JsonIgnore
    private Copy copy;

    @ManyToOne
    @JsonIgnore
    private User user;

}
