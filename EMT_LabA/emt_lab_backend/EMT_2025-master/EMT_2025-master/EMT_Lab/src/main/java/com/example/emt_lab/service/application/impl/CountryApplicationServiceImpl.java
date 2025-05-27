package com.example.emt_lab.service.application.impl;

import com.example.emt_lab.dto.CreateCountyDto;
import com.example.emt_lab.dto.DisplayCountyDto;
import com.example.emt_lab.model.views.AuthorsPerCountryView;
import com.example.emt_lab.repository.AuthorsPerCountryViewRepository;
import com.example.emt_lab.service.application.CountryApplicationService;
import com.example.emt_lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService  countryService;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;

    public CountryApplicationServiceImpl(CountryService countryService, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.countryService = countryService;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }

    @Override
    public List<DisplayCountyDto> getAllCountries() {
        return DisplayCountyDto.from(countryService.getAllCountries());
    }

    @Override
    public Optional<DisplayCountyDto> getCountryById(Long country) {
        return countryService.getCountryById(country).map(DisplayCountyDto::from);
    }

    @Override
    public Optional<DisplayCountyDto> addCountry(CreateCountyDto country) {
        return countryService.addCountry(country.toCounty()).map(DisplayCountyDto::from);
    }

    @Override
    public Optional<DisplayCountyDto> editCountry(Long id, CreateCountyDto country) {
        return countryService.editCountry(id, country.toCounty()).map(DisplayCountyDto::from);
    }

    @Override
    public void deleteCountry(Long id) {
        countryService.deleteCountry(id);
    }

    @Override
    public List<AuthorsPerCountryView> getAllAuthorsPerCountry() {
        return authorsPerCountryViewRepository.findAll();
    }

    @Override
    public AuthorsPerCountryView getAuthorsPerCountry(Long id) {
        return authorsPerCountryViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        authorsPerCountryViewRepository.refreshMaterializedView();
    }
}
