package com.example.emt_lab.web;

import com.example.emt_lab.dto.CreateBookDto;
import com.example.emt_lab.dto.DisplayBookDto;
import com.example.emt_lab.model.views.BooksPerAuthorView;
import com.example.emt_lab.security.JwtConstants;
import com.example.emt_lab.service.application.AuthorApplicationService;
import com.example.emt_lab.service.application.BookApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "Endpoints for managing books")
public class BookController {
    private final BookApplicationService bookApplicationService;
    private final AuthorApplicationService authorApplicationService;

    public BookController(BookApplicationService bookApplicationService, AuthorApplicationService authorApplicationService){
        this.bookApplicationService = bookApplicationService;
        this.authorApplicationService = authorApplicationService;
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all available books.")
    @GetMapping
    public List<DisplayBookDto> getAllBooks() {
        return bookApplicationService.getAllBooks();
    }

    @Operation(summary = "Get book by ID", description = "Finds a book by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> getBookById(@PathVariable Long id){
        return bookApplicationService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new book",
            description = "Creates a new book based on the given BookDto."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> addBook(@RequestBody CreateBookDto book){
        return bookApplicationService.addBook(book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update an existing book", description = "Updates a book by ID using BookDto."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> editBook(@PathVariable Long id, @RequestBody CreateBookDto book){
        return bookApplicationService.editBook(id, book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookApplicationService.getBookById(id).isPresent()) {
            bookApplicationService.deleteBook(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<DisplayBookDto> markTaken(@PathVariable Long id){
        if(bookApplicationService.getBookById(id).isPresent()){
            bookApplicationService.markTaken(id);
            return ResponseEntity.ok(bookApplicationService.getBookById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get number of books per author",
            description = "Returns a list of authors with the number of books they have written."
    )
    @GetMapping("/by-author")
    public ResponseEntity<?> getAllBooksPerAuthor() {
        return ResponseEntity.ok().body(authorApplicationService.getAllBooksPerAuthor());
    }

    @GetMapping("by-author/{id}")
    public ResponseEntity<BooksPerAuthorView> getBooksPerAuthor(@PathVariable Long id){
        return ResponseEntity.ok().body(authorApplicationService.getBooksPerAuthor(id));
    }

    public String extractTokenFromRequest(HttpServletRequest request){
        return request.getHeader(JwtConstants.HEADER).substring(JwtConstants.TOKEN_PREFIX.length());
    }

    @GetMapping("/wishlist")
    @Operation(summary = "List all books in wishlist", description = "Retrieves all books from the authenticated user's wishlist")
    public ResponseEntity<?> listAllInWishList(HttpServletRequest request) {
        return ResponseEntity.ok(bookApplicationService.findAllInWishlist(extractTokenFromRequest(request)));
    }

    @PostMapping("/wishlist/add/{id}")
    public ResponseEntity<?> addBookToWishlist(@PathVariable Long id, HttpServletRequest request){
        bookApplicationService.addBookToWishlist(id,extractTokenFromRequest(request));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rent/all")
    @Operation(summary = "Rent all books from wishlist", description = "Rents all books present in the authenticated user's wishlist")
    public ResponseEntity<?> rentAllFromWishList(HttpServletRequest request) {
        boolean result = bookApplicationService.rentAllFromWishlist(extractTokenFromRequest(request));
        return ResponseEntity.ok(result);
    }



}
