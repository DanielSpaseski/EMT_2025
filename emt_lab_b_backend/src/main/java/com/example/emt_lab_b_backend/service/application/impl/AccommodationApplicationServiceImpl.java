package com.example.emt_lab_b_backend.service.application.impl;

import com.example.emt_lab_b_backend.dto.domain.CreateAccommodationDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayAccommodationDetailsDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayAccommodationDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayReservationDto;
import com.example.emt_lab_b_backend.model.domain.Accommodation;
import com.example.emt_lab_b_backend.model.domain.Host;
import com.example.emt_lab_b_backend.model.domain.Reservation;
import com.example.emt_lab_b_backend.service.application.AccommodationApplicationService;
import com.example.emt_lab_b_backend.service.domain.AccommodationService;
import com.example.emt_lab_b_backend.service.domain.HostService;
import com.example.emt_lab_b_backend.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;
    private final ReservationService reservationService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService, ReservationService reservationService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.reservationService = reservationService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDetailsDto> findByIdWithDetails(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDetailsDto::from);
    }

    @Override
    public DisplayAccommodationDto save(CreateAccommodationDto createAccommodationDto) {
        Host host = hostService.findById(createAccommodationDto.hostId()).orElseThrow(RuntimeException::new);
        return DisplayAccommodationDto.from(accommodationService.save(createAccommodationDto.toAccommodation(host)));
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto) {
        Host host = hostService.findById(createAccommodationDto.hostId()).orElseThrow(RuntimeException::new);
        return accommodationService.update(id,createAccommodationDto.toAccommodation(host)).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> deleteById(Long id) {
        return accommodationService.deleteById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public DisplayReservationDto addToReservation(Long id, String username) {
        Accommodation accommodation = accommodationService.findById(id).orElseThrow(RuntimeException::new);
        Reservation reservation = reservationService.findOrCreatePending(username);
        return DisplayReservationDto.from(accommodationService.addToReservation(accommodation,reservation));
    }

    @Override
    public DisplayReservationDto removeFromReservation(Long id, String username) {
        Accommodation accommodation = accommodationService.findById(id).orElseThrow(RuntimeException::new);
        Reservation reservation = reservationService.findOrCreatePending(username);
        return DisplayReservationDto.from(accommodationService.removeFromReservation(accommodation,reservation));
    }
}
