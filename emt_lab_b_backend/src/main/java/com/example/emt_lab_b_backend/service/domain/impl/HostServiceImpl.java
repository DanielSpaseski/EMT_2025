package com.example.emt_lab_b_backend.service.domain.impl;

import com.example.emt_lab_b_backend.model.domain.Host;
import com.example.emt_lab_b_backend.repository.HostRepository;
import com.example.emt_lab_b_backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Host save(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return findById(id)
                .map(existingItem -> {
                   existingItem.setName(host.getName());
                   existingItem.setSurname(host.getSurname());
                   return hostRepository.save(existingItem);
                });
    }

    @Override
    public Optional<Host> deleteById(Long id) {
        Optional<Host> host = hostRepository.findById(id);
        host.ifPresent(hostRepository::delete);
        return host;
    }
}
