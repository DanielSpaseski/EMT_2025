package com.example.emt_lab_b_backend.web.controllers;

import com.example.emt_lab_b_backend.dto.domain.CreateAccommodationDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayAccommodationDetailsDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayAccommodationDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayReservationDto;
import com.example.emt_lab_b_backend.model.domain.User;
import com.example.emt_lab_b_backend.service.application.AccommodationApplicationService;
import com.example.emt_lab_b_backend.service.domain.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayAccommodationDto>> findAll(){
        return ResponseEntity.ok(accommodationApplicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id){
        return accommodationApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<DisplayAccommodationDetailsDto> findByIdWithDetails(@PathVariable Long id){
        return accommodationApplicationService.findByIdWithDetails(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto createAccommodationDto){
        return ResponseEntity.ok(accommodationApplicationService.save(createAccommodationDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto createAccommodationDto){
        return accommodationApplicationService.update(id,createAccommodationDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayAccommodationDto> deleteById(@PathVariable Long id){
        return accommodationApplicationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/add-to-reservation")
    public ResponseEntity<DisplayReservationDto> addToReservation(@PathVariable Long id, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(accommodationApplicationService.addToReservation(id, user.getUsername()));
    }

    @PostMapping("/{id}/remove-from-reservation")
    public ResponseEntity<DisplayReservationDto> removeFromReservation(@PathVariable Long id, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(accommodationApplicationService.removeFromReservation(id, user.getUsername()));
    }
}
