package com.example.emt_lab.service.application.impl;

import com.example.emt_lab.dto.CreateAuthorDto;
import com.example.emt_lab.dto.DisplayAuthorDto;
import com.example.emt_lab.events.AuthorChangedEvent;
import com.example.emt_lab.events.AuthorCreatedEvent;
import com.example.emt_lab.events.AuthorDeletedEvent;
import com.example.emt_lab.model.domain.Author;
import com.example.emt_lab.model.domain.Country;
import com.example.emt_lab.model.projections.AuthorProjection;
import com.example.emt_lab.model.views.BooksPerAuthorView;
import com.example.emt_lab.repository.BooksPerAuthorViewRepository;
import com.example.emt_lab.service.application.AuthorApplicationService;
import com.example.emt_lab.service.domain.AuthorService;
import com.example.emt_lab.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService, BooksPerAuthorViewRepository booksPerAuthorViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<DisplayAuthorDto> getAllAuthors() {
        return authorService.getAllAuthors().stream().map(DisplayAuthorDto::from).toList();
    }

    @Override
    public Optional<DisplayAuthorDto> getAuthorById(Long id) {
        return authorService.getAuthorById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> addAuthor(CreateAuthorDto author) {
        Optional<Country> country = countryService.getCountryById(author.country());
        Author a = author.toAuthor(country.orElse(null));
        this.applicationEventPublisher.publishEvent(new AuthorCreatedEvent(a));
        return authorService.addAuthor(a).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> editAuthor(Long id, CreateAuthorDto author) {
        Optional<Country> country = countryService.getCountryById(author.country());
        Author a = author.toAuthor(country.orElse(null));
        this.applicationEventPublisher.publishEvent(new AuthorChangedEvent(a));
        return authorService.editAuthor(id, a).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorService.getAuthorById(id).orElseThrow();
        authorService.deleteAuthor(id);
        this.applicationEventPublisher.publishEvent(new AuthorDeletedEvent(author));
    }

    @Override
    public List<BooksPerAuthorView> getAllBooksPerAuthor() {
        return booksPerAuthorViewRepository.findAll();
    }

    @Override
    public BooksPerAuthorView getBooksPerAuthor(Long id) {
        return booksPerAuthorViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();
    }

    @Override
    public List<AuthorProjection> getAllAuthorNames() {
        return authorService.getAllAuthorNames();
    }
}
