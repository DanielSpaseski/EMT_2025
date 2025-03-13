package com.example.emt_lab.config;

import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.Book;
import com.example.emt_lab.model.Category;
import com.example.emt_lab.model.Country;
import com.example.emt_lab.repository.AuthorRepository;
import com.example.emt_lab.repository.BookRepository;
import com.example.emt_lab.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
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

    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
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


        bookRepository.save(new Book("Book1", Category.FANTASY, authors.get(0), 100));
        bookRepository.save(new Book("Book2", Category.CLASSICS, authors.get(1), 500));
        bookRepository.save(new Book("Book3", Category.BIOGRAPHY, authors.get(2), 200));


    }

}
