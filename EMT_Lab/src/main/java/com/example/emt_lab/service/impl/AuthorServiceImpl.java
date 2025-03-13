package com.example.emt_lab.service.impl;

import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.dto.AuthorDto;
import com.example.emt_lab.repository.AuthorRepository;
import com.example.emt_lab.service.AuthorService;
import com.example.emt_lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService){
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> addAuthor(AuthorDto author) {
        if(author.getCountry() != null &&
                countryService.getCountryById(author.getCountry()).isPresent()){
            return Optional.of(
                    authorRepository.save(new Author(author.getName(), author.getSurname(), countryService.getCountryById(author.getCountry()).get()))
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> editAuthor(Long id, AuthorDto author) {
        return authorRepository.findById(id)
                .map(a -> {
                    if(author.getName() != null){
                        a.setName(author.getName());
                    }
                    if(author.getSurname() != null){
                        a.setSurname(author.getSurname());
                    }
                    if(author.getCountry() != null && countryService.getCountryById(author.getCountry()).isPresent()){
                        a.setCountry(countryService.getCountryById(author.getCountry()).get());
                    }
                    return authorRepository.save(a);
                });
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
