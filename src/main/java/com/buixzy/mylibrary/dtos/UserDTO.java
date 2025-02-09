package com.buixzy.mylibrary.dtos;

import com.buixzy.mylibrary.entities.User;
import com.buixzy.mylibrary.enums.UserRole;
import com.buixzy.mylibrary.enums.UserStatus;

public record UserDTO(
    String name,
    String email,
    String cpf,
    UserRole role,
    String cardId,
    UserStatus status,
    String keycloakId
) {
   public User toUser() {
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setCpf(cpf);
    user.setRole(role);
    user.setCardId(cardId);
    user.setStatus(status);
    user.setKeycloakId(keycloakId);

    return user;
   }
}
