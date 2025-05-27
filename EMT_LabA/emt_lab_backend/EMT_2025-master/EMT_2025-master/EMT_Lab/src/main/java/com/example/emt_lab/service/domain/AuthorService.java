package com.example.emt_lab.service.domain;

import com.example.emt_lab.model.domain.Author;
import com.example.emt_lab.dto.AuthorDto;
import com.example.emt_lab.model.projections.AuthorProjection;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(Long id);

    Optional<Author> addAuthor(Author author);

    Optional<Author> editAuthor(Long id, Author author);

    void deleteAuthor(Long id);
    List<AuthorProjection> getAllAuthorNames();
    void refreshMaterializedView();
}
