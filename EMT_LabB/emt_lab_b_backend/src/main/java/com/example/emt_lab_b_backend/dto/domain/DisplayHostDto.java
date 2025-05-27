package com.example.emt_lab_b_backend.dto.domain;

import com.example.emt_lab_b_backend.model.domain.Host;

import java.util.List;

public record DisplayHostDto(
        Long id,
        String name,
        String surname
) {
    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(
                host.getId(),
                host.getName(),
                host.getSurname()
        );
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts
                .stream()
                .map(DisplayHostDto::from)
                .toList();
    }

}
