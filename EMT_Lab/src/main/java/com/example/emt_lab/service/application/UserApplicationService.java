package com.example.emt_lab.service.application;

import com.example.emt_lab.dto.CreateUserDto;
import com.example.emt_lab.dto.DisplayUserDto;
import com.example.emt_lab.dto.LoginResponseDto;
import com.example.emt_lab.dto.LoginUserDto;
import com.example.emt_lab.model.projections.UserProjection;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
    List<UserProjection> getAllUserNames();

}
