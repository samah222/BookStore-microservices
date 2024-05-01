package com.samah.bookservice.service.impl;

import com.samah.bookservice.dto.BookDto;
import com.samah.bookservice.entity.Book;
import com.samah.bookservice.exception.BookNotFoundException;
import com.samah.bookservice.mapper.Mapper;
import com.samah.bookservice.repository.BookRepository;
import com.samah.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private Mapper mapper;

    public BookDto addBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return mapper.BookToBookDto(savedBook);
    }

    public BookDto getBook(int id) {
        Optional<Book> bookdb = bookRepository.findById(id);
        if (bookdb.isPresent())
            return mapper.BookToBookDto(bookdb.get());
        else throw new BookNotFoundException("Book not found");
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(b -> mapper.BookToBookDto(b)).toList();
    }

    @Override
    public BookDto updateBook(BookDto bookDto, int id) {
        if (bookDto == null)
            throw new NullPointerException("bookDto is null");
        Book book = mapper.BookDtoToBook(bookDto);
        book.setId(id);
        return mapper.BookToBookDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(int id) {
        if (id <= 0)
            throw new RuntimeException("id can not be 0 or negative number");
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getBooksByISBN(String isbn) {
        List<Book> books = bookRepository.findByIsbnContaining(isbn);
        return books.isEmpty() ? null : books.stream().map(b -> mapper.BookToBookDto(b)).toList();
    }

    @Override
    public List<BookDto> getBooksByLanguage(String language) {
        List<Book> books = bookRepository.findByLanguage(language);
        return books.isEmpty() ? null : books.stream().map(b -> mapper.BookToBookDto(b)).toList();
    }

    @Override
    public List<BookDto> getBooksByPublisher(String publisher) {
        List<Book> books = bookRepository.findByPublisher(publisher);
        return books.isEmpty() ? null : books.stream().map(b -> mapper.BookToBookDto(b)).toList();
    }

    @Override
    public List<BookDto> getBooksByPublicationYearBetween(int year1, int year2) {
        List<Book> books;
        if (year1 == year2) {
            books = bookRepository.findByPublicationYear(year1);
            return books.isEmpty() ? null : books.stream().map(b -> mapper.BookToBookDto(b)).toList();
        }
        if (year1 > year2) {
            int temp = year1;
            year1 = year2;
            year2 = temp;
        }
        books = bookRepository.findByPublicationYearBetween(year1, year2);
        return books.isEmpty() ? null : books.stream().map(b -> mapper.BookToBookDto(b)).toList();
    }

}
