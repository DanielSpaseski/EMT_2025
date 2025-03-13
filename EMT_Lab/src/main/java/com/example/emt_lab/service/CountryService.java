package com.example.emt_lab.service;

import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.Country;
import com.example.emt_lab.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> getAllCountries();

    Optional<Country> getCountryById(Long country);

    Optional<Country> addCountry(CountryDto country);

    Optional<Country> editCountry(Long id, CountryDto country);

    void deleteCountry(Long id);
}
