package com.example.emt_lab.web;

import com.example.emt_lab.dto.CreateAuthorDto;
import com.example.emt_lab.dto.DisplayAuthorDto;
import com.example.emt_lab.model.projections.AuthorProjection;
import com.example.emt_lab.service.application.AuthorApplicationService;
import com.example.emt_lab.service.application.CountryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for authors")
public class AuthorController {
    private final AuthorApplicationService authorApplicationService;
    private final CountryApplicationService countryApplicationService;
    public AuthorController(AuthorApplicationService authorApplicationService, CountryApplicationService countryApplicationService){
        this.authorApplicationService = authorApplicationService;
        this.countryApplicationService = countryApplicationService;
    }


    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors.")
    @GetMapping()
    public List<DisplayAuthorDto> listAuthors() {
        return authorApplicationService.getAllAuthors();
    }

    @Operation(summary = "Get author by ID", description = "Finds a author by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> getAuthorById(@PathVariable Long id){
        return this.authorApplicationService.getAuthorById(id)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new author",
            description = "Creates a new author based on the given AuthorDto."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> addAuthor(@RequestBody CreateAuthorDto author){
        return this.authorApplicationService.addAuthor(author)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update an existing author", description = "Updates a product by ID using AuthorDto."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAuthorDto> editAuthor(@PathVariable Long id, @RequestBody CreateAuthorDto author){
        return this.authorApplicationService.editAuthor(id, author)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an author", description = "Deletes an author by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        if(authorApplicationService.getAuthorById(id).isPresent()){
            authorApplicationService.deleteAuthor(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("by-country")
    public ResponseEntity<?> getAllAuthorsPerCountry(){
        return ResponseEntity.ok().body(countryApplicationService.getAllAuthorsPerCountry());
    }

    @GetMapping("by-country/{id}")
    public ResponseEntity<?> getAuthorsPerCountry(@PathVariable Long id){
        return ResponseEntity.ok().body(countryApplicationService.getAuthorsPerCountry(id));
    }

    @Operation(summary = "Get all author names", description = "Retrieves only names and surnames of all authors.")
    @GetMapping("/names")
    public List<AuthorProjection> getAuthorNames() {
        return authorApplicationService.getAllAuthorNames();
    }


}
