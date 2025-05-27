package com.example.emt_lab_b_backend.repository;

import com.example.emt_lab_b_backend.model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
