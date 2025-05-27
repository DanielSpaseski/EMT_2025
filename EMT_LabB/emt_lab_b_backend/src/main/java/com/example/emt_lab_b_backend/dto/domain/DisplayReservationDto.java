package com.example.emt_lab_b_backend.dto.domain;

import com.example.emt_lab_b_backend.model.domain.Reservation;
import com.example.emt_lab_b_backend.model.enumerations.ReservationStatus;

import java.util.List;

public record DisplayReservationDto(
        String username,
        List<DisplayAccommodationDto> accommodations,
        ReservationStatus status
) {
    public static DisplayReservationDto from(Reservation reservation) {
        return new DisplayReservationDto(
                reservation.getUser().getUsername(),
                DisplayAccommodationDto.from(reservation.getAccommodations()),
                reservation.getStatus()
        );
    }
}
