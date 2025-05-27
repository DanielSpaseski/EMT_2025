package com.example.emt_lab_b_backend.web.controllers;

import com.example.emt_lab_b_backend.dto.domain.DisplayReservationDto;
import com.example.emt_lab_b_backend.model.domain.User;
import com.example.emt_lab_b_backend.service.application.ReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationApplicationService reservationApplicationService;

    public ReservationController(ReservationApplicationService reservationApplicationService) {
        this.reservationApplicationService = reservationApplicationService;
    }
    @GetMapping("/pending")
    public ResponseEntity<DisplayReservationDto> findOrCreatePending(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(reservationApplicationService.findOrCreatePending(user.getUsername()));
    }

    @PutMapping("/pending/confirm")
    public ResponseEntity<DisplayReservationDto> confirm(@AuthenticationPrincipal User user){
        return reservationApplicationService.confirm(user.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/pending/cancel")
    public ResponseEntity<DisplayReservationDto> cancel(@AuthenticationPrincipal User user){
        return reservationApplicationService.cancel(user.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
