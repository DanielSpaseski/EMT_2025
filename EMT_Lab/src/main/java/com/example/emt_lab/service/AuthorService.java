package com.example.emt_lab.service;

import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(Long id);

    Optional<Author> addAuthor(AuthorDto author);

    Optional<Author> editAuthor(Long id, AuthorDto author);

    void deleteAuthor(Long id);
}
