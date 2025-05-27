package com.example.emt_lab_b_backend.web.controllers;

import com.example.emt_lab_b_backend.dto.domain.CreateHostDto;
import com.example.emt_lab_b_backend.dto.domain.DisplayHostDto;
import com.example.emt_lab_b_backend.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayHostDto>> findAll() {
        return ResponseEntity.ok(hostApplicationService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createHostDto) {
        return ResponseEntity.ok(hostApplicationService.save(createHostDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayHostDto> update(
            @PathVariable Long id,
            @RequestBody CreateHostDto createHostDto
    ) {
        return hostApplicationService.update(id, createHostDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayHostDto> deleteById(@PathVariable Long id) {
        return hostApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
