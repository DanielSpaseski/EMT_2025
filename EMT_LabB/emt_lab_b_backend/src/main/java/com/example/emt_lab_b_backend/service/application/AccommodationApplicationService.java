package com.example.emt_lab_b_backend.service.application;

import com.example.emt_lab_b_backend.dto.domain.CreateAccommodationDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayAccommodationDetailsDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayAccommodationDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayReservationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDetailsDto> findByIdWithDetails(Long id);

    DisplayAccommodationDto save(CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> deleteById(Long id);

    DisplayReservationDto addToReservation(Long id, String username);

    DisplayReservationDto removeFromReservation(Long id, String username);
}
