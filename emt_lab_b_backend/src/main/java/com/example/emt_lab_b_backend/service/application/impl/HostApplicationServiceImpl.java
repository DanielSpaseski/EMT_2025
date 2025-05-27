package com.example.emt_lab_b_backend.service.application.impl;

import com.example.emt_lab_b_backend.dto.domain.CreateHostDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayHostDto;
import com.example.emt_lab_b_backend.service.application.HostApplicationService;
import com.example.emt_lab_b_backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;

    public HostApplicationServiceImpl(HostService hostService) {
        this.hostService = hostService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public DisplayHostDto save(CreateHostDto createHostDto) {
        return DisplayHostDto.from(hostService.save(createHostDto.toHost()));
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {
        return hostService.update(id, createHostDto.toHost()).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> deleteById(Long id) {
        return hostService.deleteById(id).map(DisplayHostDto::from);
    }
}
