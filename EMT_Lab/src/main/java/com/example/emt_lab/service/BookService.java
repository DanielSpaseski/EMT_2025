package com.example.emt_lab.service;

import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.Book;
import com.example.emt_lab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Optional<Book> addBook(BookDto book);

    Optional<Book> editBook(Long id, BookDto book);

    void deleteBook(Long id);

    void markTaken(Long id);

}
