package com.example.emt_lab.service.domain.impl;

import com.example.emt_lab.model.domain.Author;
import com.example.emt_lab.model.projections.AuthorProjection;
import com.example.emt_lab.repository.AuthorRepository;
import com.example.emt_lab.repository.AuthorsPerCountryViewRepository;
import com.example.emt_lab.service.domain.AuthorService;
import com.example.emt_lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService, AuthorsPerCountryViewRepository authorsPerCountryViewRepository){
        this.authorRepository = authorRepository;
        this.countryService = countryService;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
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
    public Optional<Author> addAuthor(Author author) {
        Optional<Author> savedAuthor = Optional.empty();
        if(author.getCountry() != null && countryService.getCountryById(author.getCountry().getId()).isPresent()){
            savedAuthor = Optional.of(authorRepository.save(new Author(
                    author.getName(),
                    author.getSurname(),
                    author.getCountry()
            )));
            this.refreshMaterializedView();
        }
        return savedAuthor;
    }

    @Override
    public Optional<Author> editAuthor(Long id, Author author) {
        return authorRepository.findById(id).map(a->{
            if(author.getCountry()!=null){
                a.setCountry(author.getCountry());
            }
            if(author.getName()!=null){
                a.setName(author.getName());
            }
            if(author.getSurname()!=null){
                a.setSurname(author.getSurname());
            }
            authorRepository.save(a);
            this.refreshMaterializedView();
            return a;
        });
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorProjection> getAllAuthorNames() {
        return authorRepository.findAllByNameAndSurname();
    }

    @Override
    public void refreshMaterializedView() {
        authorsPerCountryViewRepository.refreshMaterializedView();
    }

}
