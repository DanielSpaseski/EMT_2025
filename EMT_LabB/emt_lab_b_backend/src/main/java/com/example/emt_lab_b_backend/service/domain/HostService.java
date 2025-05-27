package com.example.emt_lab_b_backend.service.domain;

import com.example.emt_lab_b_backend.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Host save(Host host);

    Optional<Host> update(Long id, Host host);

    Optional<Host> deleteById(Long id);
}
