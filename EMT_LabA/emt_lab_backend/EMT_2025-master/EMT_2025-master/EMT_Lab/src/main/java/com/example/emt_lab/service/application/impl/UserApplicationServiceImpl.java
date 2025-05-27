package com.example.emt_lab.service.application.impl;

import com.example.emt_lab.dto.CreateUserDto;
import com.example.emt_lab.dto.DisplayUserDto;
import com.example.emt_lab.dto.LoginResponseDto;
import com.example.emt_lab.dto.LoginUserDto;
import com.example.emt_lab.model.domain.User;
import com.example.emt_lab.model.projections.UserProjection;
import com.example.emt_lab.security.JwtHelper;
import com.example.emt_lab.service.application.UserApplicationService;
import com.example.emt_lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));

    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public List<UserProjection> getAllUserNames() {
        return userService.getAllUserNames();
    }


}
