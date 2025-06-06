package com.example.emt_lab_b_backend.model.enumerations;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CUSTOMER,
    ROLE_OWNER,
    ROLE_ADMIN
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}

