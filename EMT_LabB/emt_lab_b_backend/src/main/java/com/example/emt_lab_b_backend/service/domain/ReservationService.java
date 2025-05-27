package com.example.emt_lab_b_backend.service.domain;

import com.example.emt_lab_b_backend.model.domain.Reservation;

import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> findPending(String username);

    Reservation findOrCreatePending(String username);

    Optional<Reservation> confirm(String username);

    Optional<Reservation> cancel(String username);
}
