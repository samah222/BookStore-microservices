package com.samah.bookservice.service;

import com.samah.bookservice.dto.BookDto;
import com.samah.bookservice.entity.Book;
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
