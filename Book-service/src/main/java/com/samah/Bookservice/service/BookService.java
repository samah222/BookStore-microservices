package com.samah.Bookservice.service;

import com.samah.Bookservice.exception.BookNotFoundException;
import com.samah.Bookservice.model.Book;
import com.samah.Bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book){
       Book savedBook = bookRepository.save(book);
       return savedBook;
    }
    public Book getBook(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
