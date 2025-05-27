package com.example.emt_lab_b_backend.dto.domain;

import com.example.emt_lab_b_backend.model.domain.Host;

public record CreateHostDto (
        String name,
        String surname
){
    public Host toHost() {
        return new Host(name, surname);
    }

}
