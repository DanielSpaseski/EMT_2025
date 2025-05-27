package com.example.emt_lab.service.domain.impl;

import com.example.emt_lab.model.domain.Book;
import com.example.emt_lab.model.domain.User;
import com.example.emt_lab.repository.BookRepository;
import com.example.emt_lab.repository.BooksPerAuthorViewRepository;
import com.example.emt_lab.repository.UserRepository;
import com.example.emt_lab.service.domain.AuthorService;
import com.example.emt_lab.service.domain.BookService;
import com.example.emt_lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthorService authorService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    public BookServiceImpl(BookRepository bookRepository, UserService userService, UserRepository userRepository, AuthorService authorService, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.authorService = authorService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
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
    public Optional<Book> addBook(Book book) {
        Optional<Book> savedBook = Optional.empty();
        if(book.getAuthor() != null && authorService.getAuthorById(book.getAuthor().getId()).isPresent()){
            savedBook = Optional.of(bookRepository.save(new Book(
                    book.getName(),
                    book.getCategory(),
                    authorService.getAuthorById(book.getAuthor().getId()).get(),
                    book.getAvailableCopies()
            )));
        }
        this.refreshMaterializedView();
        return savedBook;
    }

    @Override
    public Optional<Book> editBook(Long id, Book book) {
        return bookRepository.findById(id).map(b->{
            if(book.getAuthor()!=null){
                b.setAuthor(book.getAuthor());
            }
            if(book.getName()!=null){
                b.setName(book.getName());
            }
            if(book.getCategory()!=null){
                b.setCategory(book.getCategory());
            }
            if(book.getAvailableCopies()!=null){
                b.setAvailableCopies(book.getAvailableCopies());
            }
            bookRepository.save(b);
            this.refreshMaterializedView();
            return b;
        });
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void markTaken(Long id) {
        Book b = bookRepository.findById(id).orElse(null);
        if (b == null)
            return;
        b.setAvailableCopies(b.getAvailableCopies() - 1);
        bookRepository.save(b);
    }

    @Override
    public void addBookToWishlist(Long id, String token) {
        User user = userService.getAuthenticatedUser(token);
        Book book = getBookById(id).orElseThrow();
        user.getBookWishlist().add(book);
        userRepository.save(user);
    }

    @Override
    public List<Book> findAllInWishlist(String token) {
        return userService.getAuthenticatedUser(token).getBookWishlist();
    }

    @Override
    public boolean rentAllFromWishlist(String token) {
        User user = userService.getAuthenticatedUser(token);
        return !user.getBookWishlist().stream().map(b-> rentBook(b.getId(), token)).toList().contains(false);
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();
    }

    private boolean rentBook(Long id, String token) {
        Book book = bookRepository.findById(id).orElseThrow();
        if (book.getAvailableCopies()==0){
            return false;
        }
        User user = userService.getAuthenticatedUser(token);
        user.getRentedBooks().add(book);
        removeBookFromWishList(id, token);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        userRepository.save(user);

        return true;
    }

    private void removeBookFromWishList(Long id, String token) {
        User user = userService.getAuthenticatedUser(token);

        user.getBookWishlist().removeIf(b->b.getId().equals(id));
        userRepository.save(user);
    }


}
