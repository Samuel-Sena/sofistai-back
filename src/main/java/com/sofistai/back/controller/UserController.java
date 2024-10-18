package com.sofistai.back.controller;

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
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            if (user.getFirstName() == null || user.getLastName() == null || user.getUsername() == null ||
                    user.getEmail() == null || user.getPassword() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos os campos são obrigatórios.");
            }

            if (userService.emailExists(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já está em uso.");
            }

            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar o usuário.");
        }
    }
}
