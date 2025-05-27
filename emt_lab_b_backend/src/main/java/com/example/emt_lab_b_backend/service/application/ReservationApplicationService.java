package com.example.emt_lab_b_backend.service.application;

import com.example.emt_lab_b_backend.dto.domain.DisplayReservationDto;

import java.util.Optional;

public interface ReservationApplicationService {
    DisplayReservationDto findOrCreatePending(String username);

    Optional<DisplayReservationDto> confirm(String username);

    Optional<DisplayReservationDto> cancel(String username);
}
