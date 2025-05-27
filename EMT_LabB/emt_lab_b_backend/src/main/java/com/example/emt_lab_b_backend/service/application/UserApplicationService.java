package com.example.emt_lab_b_backend.service.application;


import com.example.emt_lab_b_backend.dto.domain.LoginUserRequestDto;
import com.example.emt_lab_b_backend.dto.domain.LoginUserResponseDto;
import com.example.emt_lab_b_backend.dto.domain.RegisterUserRequestDto;
import com.example.emt_lab_b_backend.dto.domain.RegisterUserResponseDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto);

    Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto);

    Optional<RegisterUserResponseDto> findByUsername(String username);
}
