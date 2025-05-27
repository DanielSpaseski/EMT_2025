package com.example.emt_lab_b_backend.dto.domain;


import com.example.emt_lab_b_backend.model.domain.User;
import com.example.emt_lab_b_backend.model.enumerations.Role;

public record RegisterUserResponseDto(
        String username,
        String name,
        String surname,
        String email,
        Role role
) {

    public static RegisterUserResponseDto from(User user) {
        return new RegisterUserResponseDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );
    }

}
