package com.example.emt_lab.service.application;

import com.example.emt_lab.dto.CreateAuthorDto;
import com.example.emt_lab.dto.CreateCountyDto;
import com.example.emt_lab.dto.DisplayAuthorDto;
import com.example.emt_lab.dto.DisplayCountyDto;
import com.example.emt_lab.model.domain.Country;
import com.example.emt_lab.model.views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountyDto> getAllCountries();

    Optional<DisplayCountyDto> getCountryById(Long country);

    Optional<DisplayCountyDto> addCountry(CreateCountyDto country);

    Optional<DisplayCountyDto> editCountry(Long id, CreateCountyDto country);

    void deleteCountry(Long id);

    List<AuthorsPerCountryView> getAllAuthorsPerCountry();
    AuthorsPerCountryView getAuthorsPerCountry(Long id);
    void refreshMaterializedView();
}
