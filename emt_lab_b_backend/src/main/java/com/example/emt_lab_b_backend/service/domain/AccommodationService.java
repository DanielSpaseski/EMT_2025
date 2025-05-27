package com.example.emt_lab_b_backend.service.domain;

import com.example.emt_lab_b_backend.model.domain.Accommodation;
import com.example.emt_lab_b_backend.model.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Accommodation save(Accommodation accommodation);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> deleteById(Long id);

    Reservation addToReservation(Accommodation accommodation, Reservation reservation);

    Reservation removeFromReservation(Accommodation accommodation, Reservation reservation);
}
