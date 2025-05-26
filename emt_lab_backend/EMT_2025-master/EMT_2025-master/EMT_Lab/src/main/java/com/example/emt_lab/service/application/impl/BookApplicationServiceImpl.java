package com.example.emt_lab.service.application.impl;

import com.example.emt_lab.dto.CreateBookDto;
import com.example.emt_lab.dto.DisplayBookDto;
import com.example.emt_lab.model.domain.Author;
import com.example.emt_lab.service.application.BookApplicationService;
import com.example.emt_lab.service.domain.AuthorService;
import com.example.emt_lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public List<DisplayBookDto> getAllBooks() {
        return bookService.getAllBooks().stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public Optional<DisplayBookDto> getBookById(Long id) {
        return bookService.getBookById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> addBook(CreateBookDto book) {
        Optional<Author> author = authorService.getAuthorById(book.author());
        if(author.isPresent()){
            return bookService.addBook(book.toBook(author.get()))
                              .map(DisplayBookDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayBookDto> editBook(Long id, CreateBookDto book) {
        Optional<Author> author = authorService.getAuthorById(book.author());
        return bookService.editBook(id, book.toBook(author.orElse(null)))
                          .map(DisplayBookDto::from);
    }

    @Override
    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }

    @Override
    public void markTaken(Long id) {
        bookService.markTaken(id);
    }

    @Override
    public void addBookToWishlist(Long id, String token) {
        bookService.addBookToWishlist(id,token);
    }

    @Override
    public List<DisplayBookDto> findAllInWishlist(String token) {
        return bookService.findAllInWishlist(token).stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public boolean rentAllFromWishlist(String token) {
        bookService.rentAllFromWishlist(token);
        return true;
    }
}
