package com.example.emt_lab.service.domain;

import com.example.emt_lab.model.domain.User;
import com.example.emt_lab.model.enumerations.Role;
import com.example.emt_lab.model.projections.UserProjection;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);
    User getAuthenticatedUser(String token);
    List<UserProjection> getAllUserNames();

}
