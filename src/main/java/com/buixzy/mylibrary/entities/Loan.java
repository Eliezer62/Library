package com.buixzy.mylibrary.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.buixzy.mylibrary.converters.StatusLoanConverter;
import com.buixzy.mylibrary.enums.StatusLoan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("copy")
    private Map<String, Object> copyJson() {
        return new HashMap<>(Map.of(
            "id", copy.getId(),
            "book", copy.getBook().getName(),
            "author",copy.getBook().getAuthor(),
            "isbn", copy.getBook().getISBN(),
            "code", copy.getCode()
        ));
    }

    @JsonProperty("user")
    private Map<String, Object> userJson() {
        return new HashMap<>(Map.of(
            "id", user.getId(),
            "name", user.getName(),
            "cpf", user.getCpf(),
            "cardId", user.getCardId(),
            "statusUser", user.getStatus().getStatusName()
        ));
    }

}
