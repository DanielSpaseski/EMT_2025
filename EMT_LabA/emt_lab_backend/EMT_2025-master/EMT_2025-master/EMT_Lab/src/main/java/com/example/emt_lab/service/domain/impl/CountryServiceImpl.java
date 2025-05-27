package com.example.emt_lab.service.domain.impl;

import com.example.emt_lab.model.domain.Country;
import com.example.emt_lab.repository.CountryRepository;
import com.example.emt_lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    public CountryServiceImpl(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }
    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getCountryById(Long country) {
        return countryRepository.findById(country);
    }

    @Override
    public Optional<Country> addCountry(Country country) {
        return Optional.of(
                countryRepository.save(new Country(country.getName(),country.getContinent()))
        );
    }

    @Override
    public Optional<Country> editCountry(Long id, Country country) {
        return countryRepository.findById(id)
                .map(c -> {
                    if(country.getName() != null){
                        c.setName(country.getName());
                    }
                    if(country.getContinent() != null){
                        c.setContinent(country.getContinent());
                    }
                    return countryRepository.save(c);
                });
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);

    }
}
