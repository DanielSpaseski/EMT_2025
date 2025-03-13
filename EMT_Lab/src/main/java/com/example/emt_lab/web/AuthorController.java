package com.example.emt_lab.web;

import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.dto.AuthorDto;
import com.example.emt_lab.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping()
    public List<Author> listAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        return this.authorService.getAuthorById(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto author){
        return this.authorService.addAuthor(author)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto author){
        return this.authorService.editAuthor(id, author)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        if(authorService.getAuthorById(id).isPresent()){
            authorService.deleteAuthor(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
