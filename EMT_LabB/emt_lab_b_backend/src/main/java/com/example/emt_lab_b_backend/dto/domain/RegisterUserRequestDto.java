package com.example.emt_lab_b_backend.dto.domain;


import com.example.emt_lab_b_backend.model.domain.User;

public record RegisterUserRequestDto(
        String username,
        String password,
        String name,
        String surname,
        String email
) {

    public User toUser() {
        return new User(username, password, name, surname, email);
    }

}
