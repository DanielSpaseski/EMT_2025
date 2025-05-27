package com.example.emt_lab_b_backend.service.application;

import com.example.emt_lab_b_backend.dto.domain.CreateHostDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long id);

    DisplayHostDto save(CreateHostDto createHostDto);

    Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto);

    Optional<DisplayHostDto> deleteById(Long id);
}
