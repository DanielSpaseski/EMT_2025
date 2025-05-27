package com.example.emt_lab_b_backend.dto.domain;

import com.example.emt_lab_b_backend.model.domain.Accommodation;

import java.util.List;

public record DisplayAccommodationDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer quantity,
        Long hostId
) {
    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getDescription(),
                accommodation.getPrice(),
                accommodation.getQuantity(),
                accommodation.getHost().getId()
        );
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> dishes) {
        return dishes
                .stream()
                .map(DisplayAccommodationDto::from)
                .toList();
    }
}
