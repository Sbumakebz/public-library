package com.public_library.controllers;

import com.public_library.dto.BookDTO;
import com.public_library.model.Book;
import com.public_library.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/book", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Book Controller", description = "Create, Update, Delete, Retrieve Book data")
//@PreAuthorize("isAuthenticated()")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books", description = "Provides a list of all books")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all books", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))
    @ApiResponse(responseCode = "204", description = "No book exists", content = @Content())
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> findAllBooks() {
        LOGGER.info("getAllBooks Invoked");
        List<BookDTO> books = bookService.findAllBooks();
        return new ResponseEntity<>(books, books.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @Operation(summary = "Get a book by name", description = "Provides a book based on the provided Name")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of a book", content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "204", description = "A book with the specified name does not exist", content = @Content())
    @GetMapping(value = "/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> findBookByName(@PathVariable String title) {
        try {
            LOGGER.info("getBookByTitle Invoked");
            BookDTO book = bookService.findBookByTitle(title);
            LOGGER.info("Retrieved book title : {}", book.title());
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Create Book", description = "Creates a book from the provided payload")
    @ApiResponse(responseCode = "201", description = "Successfully created of a book", content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content())
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO book, BindingResult result) {
        LOGGER.info("createBook Invoked");

        BookDTO newBook = bookService.createBook(book);
        LOGGER.info("Successfully created book");
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Book", description = "Updates a book from the provided payload")
    @ApiResponse(responseCode = "200", description = "Successful update of a book", content = @Content(schema = @Schema(implementation = Book.class)))
    @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content())
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO book, BindingResult result) {
        LOGGER.info("updateBook Invoked : book name = {0} , book author = {1}", book.title(), book.author());

        BookDTO updatedBook = bookService.updateBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Book", description = "Delete a book based on the provided ID")
    @ApiResponse(responseCode = "200", description = "Successfully deleted a book", content = @Content())
    @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful ", content = @Content())
    @DeleteMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable String title) {
        LOGGER.info("deleteBook Invoked : Name = {}", title);
        bookService.deleteBook(title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
