package com.samah.Bookservice.controller;

import com.samah.Bookservice.dto.BookDto;
import com.samah.Bookservice.entity.Book;
import com.samah.Bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable int id){
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable int id){
        return new ResponseEntity<>(bookService.updateBook(bookDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
