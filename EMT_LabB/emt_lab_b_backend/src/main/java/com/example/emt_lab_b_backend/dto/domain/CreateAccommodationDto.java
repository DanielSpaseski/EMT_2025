package com.example.emt_lab_b_backend.dto.domain;

import com.example.emt_lab_b_backend.model.domain.Accommodation;
import com.example.emt_lab_b_backend.model.domain.Host;

public record CreateAccommodationDto(
        String name,
        String description,
        Double price,
        Integer quantity,
        Long hostId
) {
    public Accommodation toAccommodation(Host host) {
        return new Accommodation(
                name,
                description,
                price,
                quantity,
                host
        );
    }
}
