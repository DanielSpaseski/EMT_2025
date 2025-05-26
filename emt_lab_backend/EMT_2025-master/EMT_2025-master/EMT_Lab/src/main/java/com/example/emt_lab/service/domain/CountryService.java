package com.example.emt_lab.service.domain;

import com.example.emt_lab.model.domain.Country;
import com.example.emt_lab.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> getAllCountries();

    Optional<Country> getCountryById(Long country);

    Optional<Country> addCountry(Country country);

    Optional<Country> editCountry(Long id, Country country);

    void deleteCountry(Long id);
}
