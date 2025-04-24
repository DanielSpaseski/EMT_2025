package com.example.emt_lab.dto;

import com.example.emt_lab.model.domain.Country;

public record CreateCountyDto(String name, String continent) {
    public Country toCounty(){
        return new Country(name, continent);
    }
}
