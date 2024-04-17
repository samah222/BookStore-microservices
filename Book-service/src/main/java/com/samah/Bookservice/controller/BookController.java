package com.samah.Bookservice.controller;

import com.samah.Bookservice.entity.Book;
import com.samah.Bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.application.version}")
    private String applicationVersion;
    @GetMapping("/info")
    public String getInfo(){
        return "This application is "+applicationName+" and this version: "+applicationVersion;
    }
    @Autowired
    BookService bookService;
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id){
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }


}
