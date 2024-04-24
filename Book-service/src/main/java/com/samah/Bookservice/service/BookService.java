package com.samah.Bookservice.service;

import com.samah.Bookservice.dto.BookDto;
import com.samah.Bookservice.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {

     BookDto addBook(Book book);
     BookDto getBook(int id);
     List<BookDto> getAllBooks();
     BookDto updateBook(BookDto bookDto, int id);
     void deleteBook(int id);
}
