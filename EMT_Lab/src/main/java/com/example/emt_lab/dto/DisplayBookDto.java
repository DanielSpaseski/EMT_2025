package com.example.emt_lab.dto;

import com.example.emt_lab.model.domain.Author;
import com.example.emt_lab.model.domain.Book;
import com.example.emt_lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(Long id, String name, Category category, Long author, Integer availableCopies) {
    public static DisplayBookDto from(Book book){
        return new DisplayBookDto(book.getId(), book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies());
    }

    public static List<DisplayBookDto> from(List<Book> books){
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author){
        return new Book(name, category, author, availableCopies);
    }
}
