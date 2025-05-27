package com.example.emt_lab.service.domain;

import com.example.emt_lab.dto.DisplayBookDto;
import com.example.emt_lab.model.domain.Book;
import com.example.emt_lab.dto.BookDto;
import com.example.emt_lab.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Optional<Book> addBook(Book book);

    Optional<Book> editBook(Long id, Book book);

    void deleteBook(Long id);

    void markTaken(Long id);

    void addBookToWishlist(Long id, String token);

    List<Book> findAllInWishlist(String token);

    boolean rentAllFromWishlist(String token);
    void refreshMaterializedView();
}
