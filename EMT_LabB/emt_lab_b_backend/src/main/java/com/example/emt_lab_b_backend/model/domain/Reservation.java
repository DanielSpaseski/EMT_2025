package com.example.emt_lab_b_backend.model.domain;

import com.example.emt_lab_b_backend.model.enumerations.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customer_reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Accommodation> accommodations;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public Reservation() {
    }

    public Reservation(User user) {
        this.user = user;
        accommodations = new ArrayList<>();
        status = ReservationStatus.PENDING;
    }
    public void confirm() {
        status = ReservationStatus.CONFIRMED;
    }

    public void cancel() {
        status = ReservationStatus.CANCELED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
