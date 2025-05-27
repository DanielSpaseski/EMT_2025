package com.example.emt_lab_b_backend.service.application.impl;

import com.example.emt_lab_b_backend.dto.domain.DisplayReservationDto;
import com.example.emt_lab_b_backend.service.application.ReservationApplicationService;
import com.example.emt_lab_b_backend.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService {
    private final ReservationService reservationService;

    public ReservationApplicationServiceImpl(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public DisplayReservationDto findOrCreatePending(String username) {
        return DisplayReservationDto.from(reservationService.findOrCreatePending(username));
    }

    @Override
    public Optional<DisplayReservationDto> confirm(String username) {
        return reservationService.confirm(username).map(DisplayReservationDto::from);
    }

    @Override
    public Optional<DisplayReservationDto> cancel(String username) {
        return reservationService.cancel(username).map(DisplayReservationDto::from);
    }
}
