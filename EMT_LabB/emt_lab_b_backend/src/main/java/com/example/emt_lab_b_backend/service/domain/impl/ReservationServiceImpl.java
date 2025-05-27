package com.example.emt_lab_b_backend.service.domain.impl;

import com.example.emt_lab_b_backend.model.domain.Reservation;
import com.example.emt_lab_b_backend.model.domain.User;
import com.example.emt_lab_b_backend.model.enumerations.ReservationStatus;
import com.example.emt_lab_b_backend.repository.ReservationRepository;
import com.example.emt_lab_b_backend.repository.UserRepository;
import com.example.emt_lab_b_backend.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Reservation> findPending(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        return reservationRepository.findByUserAndStatus(user, ReservationStatus.PENDING);
    }

    @Override
    public Reservation findOrCreatePending(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        if(findPending(username).isPresent())
            return findPending(username).get();
        return reservationRepository.save(new Reservation(user));
    }

    @Override
    public Optional<Reservation> confirm(String username) {
        Optional<Reservation> reservation = findPending(username);
        if(reservation.isPresent() && reservation.get().getAccommodations().isEmpty()){
            throw new RuntimeException();
        }
        return reservation.map(reservation1 -> {
            reservation1.confirm();
            return reservationRepository.save(reservation1);
        });
    }

    @Override
    public Optional<Reservation> cancel(String username) {
        Optional<Reservation> reservation = findPending(username);
        if(reservation.isPresent() && reservation.get().getAccommodations().isEmpty()){
            throw new RuntimeException();
        }
        return reservation.map(reservation1 -> {
            reservation1.cancel();
            return reservationRepository.save(reservation1);
        });
    }
}
