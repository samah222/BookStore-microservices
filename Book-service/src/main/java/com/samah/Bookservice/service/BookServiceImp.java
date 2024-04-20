package com.samah.Bookservice.service;

import com.samah.Bookservice.dto.BookDto;
import com.samah.Bookservice.entity.Book;
import com.samah.Bookservice.exception.BookNotFoundException;
import com.samah.Bookservice.mapper.Mapper;
import com.samah.Bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private Mapper mapper;

    public BookDto addBook(Book book){
       Book savedBook = bookRepository.save(book);
       return mapper.BookToBookDto(savedBook);
    }
    public BookDto getBook(int id) {
        Optional<Book> bookdb = bookRepository.findById(id);
        if(bookdb.isPresent())
            return mapper.BookToBookDto(bookdb.get());
        else throw new BookNotFoundException("Book not found");
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(b -> mapper.BookToBookDto(b)).toList();
    }

}
