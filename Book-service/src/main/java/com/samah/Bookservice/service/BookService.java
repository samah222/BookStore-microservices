package com.samah.Bookservice.service;

import com.samah.Bookservice.exception.BookNotFoundException;
import com.samah.Bookservice.entity.Book;
import com.samah.Bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {

    public Book addBook(Book book);
    public Book getBook(int id) ;

    public List<Book> getAllBooks();

}
