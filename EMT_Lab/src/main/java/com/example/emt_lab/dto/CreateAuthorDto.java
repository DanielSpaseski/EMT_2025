package com.example.emt_lab.dto;

import com.example.emt_lab.model.domain.Author;
import com.example.emt_lab.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAuthorDto(String name, String surname, Long country) {
    public static CreateAuthorDto from(Author author){
        return new CreateAuthorDto(author.getName(), author.getSurname(), author.getCountry().getId());
    }

    public static List<CreateAuthorDto> from(List<Author> authors){
        return authors.stream().map(CreateAuthorDto::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country){
        return new Author(name, surname, country);
    }
}
