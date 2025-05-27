package com.example.emt_lab_b_backend.dto.domain;

import com.example.emt_lab_b_backend.model.domain.Accommodation;

public record DisplayAccommodationDetailsDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer quantity,
        DisplayHostDto host
) {
    public static DisplayAccommodationDetailsDto from(Accommodation accommodation) {
        return new DisplayAccommodationDetailsDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getDescription(),
                accommodation.getPrice(),
                accommodation.getQuantity(),
                DisplayHostDto.from(accommodation.getHost())
        );
    }
}
