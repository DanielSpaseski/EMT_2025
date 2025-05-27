package com.example.emt_lab.service.application;

import com.example.emt_lab.dto.CreateBookDto;
import com.example.emt_lab.dto.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<DisplayBookDto> getAllBooks();

    Optional<DisplayBookDto> getBookById(Long id);

    Optional<DisplayBookDto> addBook(CreateBookDto book);

    Optional<DisplayBookDto> editBook(Long id, CreateBookDto book);

    void deleteBook(Long id);

    void markTaken(Long id);
    void addBookToWishlist(Long id, String token);
    List<DisplayBookDto> findAllInWishlist(String token);
    boolean rentAllFromWishlist(String token);
}
