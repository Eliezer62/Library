package com.buixzy.mylibrary.entities;

import java.util.Date;

import com.buixzy.mylibrary.converters.UserRoleConverter;
import com.buixzy.mylibrary.converters.UserStatusConverter;
import com.buixzy.mylibrary.enums.UserRole;
import com.buixzy.mylibrary.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "email", unique = true, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name = "cpf", unique = true, columnDefinition = "CHAR(11)")
    private String cpf;

    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

    @Column(name = "card_id", unique = true, columnDefinition = "CHAR(13)")
    private String cardId;

    @Convert(converter = UserStatusConverter.class)
    private UserStatus status;

    private Date registrationDate;

    @Column(name = "keycloak_id", unique = true, columnDefinition = "CHAR(255)")
    private String keycloakId;
}
