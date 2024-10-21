package com.sofistai.back.controller;

import com.sofistai.back.dto.UserRequestDTO;
import com.sofistai.back.dto.UserResponseDTO;
import com.sofistai.back.mapper.UserMapper;
import com.sofistai.back.model.User;
import com.sofistai.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            if (userRequestDTO.getFirstName() == null || userRequestDTO.getLastName() == null ||
                    userRequestDTO.getUsername() == null || userRequestDTO.getEmail() == null ||
                    userRequestDTO.getPassword() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            if (userService.emailExists(userRequestDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }

            User savedUser = userService.saveUser(userRequestDTO);
            UserResponseDTO response = UserMapper.userToUserResponseDTO(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}