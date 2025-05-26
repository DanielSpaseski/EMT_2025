package com.example.emt_lab.config;

import com.example.emt_lab.model.domain.Author;
import com.example.emt_lab.model.domain.Book;
import com.example.emt_lab.model.domain.User;
import com.example.emt_lab.model.enumerations.Category;
import com.example.emt_lab.model.domain.Country;
import com.example.emt_lab.model.enumerations.Role;
import com.example.emt_lab.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    public static List<Country> countries = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;


    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, BooksPerAuthorViewRepository booksPerAuthorViewRepository, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }

//    @PostConstruct
    public void init() {
        countries = new ArrayList<>();
        if(this.countryRepository.count() == 0) {
            countries.add(new Country("Macedonia", "Europe"));
            countries.add(new Country("USA", "NorthAmerica"));
            countries.add(new Country("Japan", "Asia"));
            countryRepository.saveAll(countries);
        }

        authors = new ArrayList<>();
        if(this.authorRepository.count() == 0){
            authors.add(new Author("Name1", "Surname1", countries.get(1)));
            authors.add(new Author("Name2", "Surname2", countries.get(2)));
            authors.add(new Author("Name3", "Surname3", countries.get(0)));
            authorRepository.saveAll(authors);
        }


        bookRepository.save(new Book("Book1", Category.DRAMA, authors.get(0), 1000));
        bookRepository.save(new Book("Book2", Category.CLASSICS, authors.get(1), 5000));
        bookRepository.save(new Book("Book3", Category.BIOGRAPHY, authors.get(2), 200));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));

        userRepository.save(new User(
                "lib",
                passwordEncoder.encode("lib"),
                "lib",
                "lib",
                Role.ROLE_LIBRARIAN
        ));
        booksPerAuthorViewRepository.refreshMaterializedView();
        authorsPerCountryViewRepository.refreshMaterializedView();

    }




}
