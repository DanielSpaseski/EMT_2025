package com.example.emt_lab_b_backend.service.domain.impl;

import com.example.emt_lab_b_backend.model.domain.Accommodation;
import com.example.emt_lab_b_backend.model.domain.Reservation;
import com.example.emt_lab_b_backend.repository.AccommodationRepository;
import com.example.emt_lab_b_backend.repository.ReservationRepository;
import com.example.emt_lab_b_backend.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final ReservationRepository reservationRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, ReservationRepository reservationRepository) {
        this.accommodationRepository = accommodationRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Accommodation save(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return findById(id)
                .map(existingItem -> {
                    existingItem.setName(accommodation.getName());
                    existingItem.setDescription(accommodation.getDescription());
                    existingItem.setPrice(accommodation.getPrice());
                    existingItem.setQuantity(accommodation.getQuantity());
                    existingItem.setHost(accommodation.getHost());
                    return accommodationRepository.save(existingItem);
                });
    }

    @Override
    public Optional<Accommodation> deleteById(Long id) {
        Optional<Accommodation> accommodation = findById(id);
        accommodation.ifPresent(accommodationRepository::delete);
        return accommodation;
    }

    @Override
    public Reservation addToReservation(Accommodation accommodation, Reservation reservation) {
        if(accommodation.getQuantity()<=0)
            throw new RuntimeException();
        accommodation.decreaseQuantity();
        accommodationRepository.save(accommodation);
        reservation.getAccommodations().add(accommodation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation removeFromReservation(Accommodation accommodation, Reservation reservation) {
        accommodation.increaseQuantity();
        accommodationRepository.save(accommodation);
        reservation.getAccommodations().remove(accommodation);
        return reservationRepository.save(reservation);
    }
}
