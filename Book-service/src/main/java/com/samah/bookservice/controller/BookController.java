package com.samah.bookservice.controller;

import com.samah.bookservice.dto.BookDto;
import com.samah.bookservice.entity.Book;
import com.samah.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
@Tag(name = "Book Controller", description = "All CRUD operations for Book service and many APIs to be used by other micro-services")

public class BookController {
    @Autowired
    BookService bookService;

    @Operation(
            summary = "Get a book",
            description = "Get a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@Parameter(description = "ID of book to be retrieved",
            required = true) @PathVariable int id) {
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Get all book",
            description = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @Operation(
            summary = "Add new book",
            description = "Add new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201")
    })
    @PostMapping
    public ResponseEntity<BookDto> addBook(@Validated @RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit a book",
            description = "Edit a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@Validated @RequestBody BookDto bookDto,
                                              @Parameter(description = "ID of book to be updated",
                                                      required = true) @PathVariable int id) {
        return new ResponseEntity<>(bookService.updateBook(bookDto, id), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a book",
            description = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
