package com.example.emt_lab_b_backend.service.application.impl;

import com.example.emt_lab_b_backend.dto.domain.LoginUserRequestDto;
import com.example.emt_lab_b_backend.dto.domain.LoginUserResponseDto;
import com.example.emt_lab_b_backend.dto.domain.RegisterUserRequestDto;
import com.example.emt_lab_b_backend.dto.domain.RegisterUserResponseDto;
import com.example.emt_lab_b_backend.helpers.JwtHelper;
import com.example.emt_lab_b_backend.model.domain.User;
import com.example.emt_lab_b_backend.service.application.UserApplicationService;
import com.example.emt_lab_b_backend.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto) {
        User user = userService.register(registerUserRequestDto.toUser());
        RegisterUserResponseDto displayUserDto = RegisterUserResponseDto.from(user);
        return Optional.of(displayUserDto);
    }

    @Override
    public Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto) {
        User user = userService.login(loginUserRequestDto.username(), loginUserRequestDto.password());

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginUserResponseDto(token));
    }

    @Override
    public Optional<RegisterUserResponseDto> findByUsername(String username) {
        return userService
                .findByUsername(username)
                .map(RegisterUserResponseDto::from);
    }

}
