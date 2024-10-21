package com.sofistai.back.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String idUser;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // O método setPassword foi modificado pra manter a lógica de criptografia
    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }
}