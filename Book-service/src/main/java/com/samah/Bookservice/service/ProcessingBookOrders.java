package com.samah.Bookservice.service;

import com.samah.Bookservice.dto.BookDto;
import com.samah.Bookservice.entity.Book;
import com.samah.Bookservice.exception.BookNotFoundException;
import com.samah.Bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProcessingBookOrders {
//    @Autowired
//    BookRepository bookRepository;
//    public BookDto checkAvailability(BookDto bookDto){
//          Optional<Book> dbBook = bookRepository.findById(bookDto.getId());
//          if(!dbBook.isPresent())
//              throw new BookNotFoundException("Book id not exist");
//          //bookDto.quantity()
//    }
}
