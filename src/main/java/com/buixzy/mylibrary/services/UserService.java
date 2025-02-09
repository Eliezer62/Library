package com.buixzy.mylibrary.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.buixzy.mylibrary.dtos.UserDTO;
import com.buixzy.mylibrary.entities.User;
import com.buixzy.mylibrary.enums.UserRole;
import com.buixzy.mylibrary.enums.UserStatus;
import com.buixzy.mylibrary.exceptions.UserNotFoundException;
import com.buixzy.mylibrary.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository rep;

    @Autowired
    LoggerService logger;

    public User createByDTO(UserDTO dto) {
        User user = dto.toUser();
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setPassword(new BCryptPasswordEncoder().encode((CharSequence)user.getCpf()));
        logger.info("Created User: "+user.getName()+" CPF: "+user.getCpf());
        return rep.save(user);
    }

    public List<User> findAll() {
        return rep.findAll();
    }

    public User findById(Long id) {
        return rep.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User patchById(Long id, Map<String, Object> fields) {
        User user = rep.findById(id).orElseThrow(UserNotFoundException::new);

        fields.forEach((field, value) -> {
            switch (field) {
                case "name":
                    user.setName((String)value);
                    break;

                case "email":
                   user.setEmail((String)value);
                   break;

                case "cpf":
                    user.setCpf((String)value);
                    break;

                case "role":
                    user.setRole(UserRole.fromString((String)value));
                    break;

                case "cardId":
                    user.setCardId((String)value);
                    break;

                case "status":
                    user.setStatus(UserStatus.fromString((String)value));
                    break;

                case "keycloakId":
                    user.setKeycloakId((String)value);
                    break;
            
                default:
                    break;
            }
        });

        return rep.save(user);
    }

    public User updateByDTO(Long id, UserDTO dto) {
        User last = rep.findById(id).orElseThrow(UserNotFoundException::new);
        User updated = dto.toUser();
        updated.setRegistrationDate(last.getRegistrationDate());
        updated.setPassword(last.getPassword());
        updated.setId(id);
        
        return rep.save(updated);
    }

    public void deleteById(Long id) {
        rep.deleteById(id);
    }
}
