package com.sofistai.back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}