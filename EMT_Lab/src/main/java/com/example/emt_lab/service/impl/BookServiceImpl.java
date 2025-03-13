package com.example.emt_lab.service.impl;

import com.example.emt_lab.model.Book;
import com.example.emt_lab.model.dto.BookDto;
import com.example.emt_lab.repository.BookRepository;
import com.example.emt_lab.service.AuthorService;
import com.example.emt_lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService){
        this.authorService = authorService;
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> addBook(BookDto book) {
        if(book.getAuthor() != null &&
                authorService.getAuthorById(book.getAuthor()).isPresent()){
            return Optional.of(
                    bookRepository.save(new Book(book.getName(), book.getCategory(), authorService.getAuthorById(book.getAuthor()).get(), book.getAvailableCopies()))
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> editBook(Long id, BookDto book) {
        return bookRepository.findById(id)
                .map(b -> {
                    if(book.getName() != null){
                        b.setName(book.getName());
                    }
                    if(book.getCategory() != null){
                        b.setCategory(book.getCategory());
                    }
                    if(book.getAvailableCopies() != null){
                        b.setAvailableCopies(book.getAvailableCopies());
                    }
                    if(book.getAuthor() != null && authorService.getAuthorById(book.getAuthor()).isPresent()){
                        b.setAuthor(authorService.getAuthorById(book.getAuthor()).get());
                    }
                    return bookRepository.save(b);
                });
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void markTaken(Long id) {
        Book b = bookRepository.findById(id).orElse(null);
        if(b == null)
            return;
        b.setAvailableCopies(b.getAvailableCopies() - 1);
        bookRepository.save(b);
    }
}
