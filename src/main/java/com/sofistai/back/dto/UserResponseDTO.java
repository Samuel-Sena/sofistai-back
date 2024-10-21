package com.sofistai.back.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private String idUser;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
