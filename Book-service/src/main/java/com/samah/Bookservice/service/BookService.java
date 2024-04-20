package com.samah.Bookservice.service;

import com.samah.Bookservice.dto.BookDto;
import com.samah.Bookservice.exception.BookNotFoundException;
import com.samah.Bookservice.entity.Book;
import com.samah.Bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {

    public BookDto addBook(Book book);
    public BookDto getBook(int id);

    public List<BookDto> getAllBooks();

}
