package com.example.emt_lab_b_backend.repository;

import com.example.emt_lab_b_backend.model.domain.Reservation;
import com.example.emt_lab_b_backend.model.domain.User;
import com.example.emt_lab_b_backend.model.enumerations.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByUserAndStatus(User user, ReservationStatus status);

}
