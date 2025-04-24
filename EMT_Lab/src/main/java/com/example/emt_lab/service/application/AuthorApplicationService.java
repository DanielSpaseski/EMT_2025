package com.example.emt_lab.service.application;

import com.example.emt_lab.dto.CreateAuthorDto;
import com.example.emt_lab.dto.DisplayAuthorDto;
import com.example.emt_lab.model.projections.AuthorProjection;
import com.example.emt_lab.model.views.BooksPerAuthorView;


import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> getAllAuthors();

    Optional<DisplayAuthorDto> getAuthorById(Long id);

    Optional<DisplayAuthorDto> addAuthor(CreateAuthorDto author);

    Optional<DisplayAuthorDto> editAuthor(Long id, CreateAuthorDto author);

    void deleteAuthor(Long id);

    List<BooksPerAuthorView> getAllBooksPerAuthor();
    BooksPerAuthorView getBooksPerAuthor(Long id);
    void refreshMaterializedView();
    List<AuthorProjection> getAllAuthorNames();
}
