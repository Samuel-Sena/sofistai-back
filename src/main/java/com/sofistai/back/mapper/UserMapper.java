package com.sofistai.back.mapper;

import com.sofistai.back.dto.UserRequestDTO;
import com.sofistai.back.dto.UserResponseDTO;
import com.sofistai.back.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public interface UserMapper {

    static User userRequestToNewUser(UserRequestDTO userRequestDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .idUser(UUID.randomUUID().toString())
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .username(userRequestDTO.getUsername())
                .email(userRequestDTO.getEmail())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .build();
    }

    static User userRequestToUser(UserRequestDTO userRequestDTO, String id) {
        return User.builder()
                .idUser(id)
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .username(userRequestDTO.getUsername())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .build();
    }

    static UserResponseDTO userToUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .idUser(user.getIdUser())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}