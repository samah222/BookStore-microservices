package com.samah.bookservice.service.impl;

import com.samah.bookservice.dto.BookDto;
import com.samah.bookservice.entity.Book;
import com.samah.bookservice.exception.BookNotFoundException;
import com.samah.bookservice.exception.InvalidDataException;
import com.samah.bookservice.mapper.Mapper;
import com.samah.bookservice.repository.BookRepository;
import com.samah.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private Mapper mapper;

    public BookDto addBook(Book book) {
        if (book == null
                || book.getTitle() == null
                || book.getTitle().isBlank()
        )
            throw new InvalidDataException("Book data can not be empty");
        Book savedBook = bookRepository.save(book);
        return mapper.BookToBookDto(savedBook);
    }

    public BookDto getBook(int id) {
        return mapper.BookToBookDto(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found")));
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(b -> mapper.BookToBookDto(b)).toList();
    }

    @Override
    public BookDto updateBook(BookDto bookDto, int id) {
        if (bookDto == null)
            throw new NullPointerException("bookDto is null");
        Book book = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new InvalidDataException("Book id is not valid"));
        if (bookDto.getTitle() != null && !bookDto.getTitle().isBlank()) {
            book.setTitle(bookDto.getTitle());
        }
        if (bookDto.getDescription() != null) {
            book.setDescription(bookDto.getDescription());
        }
        if (bookDto.getIsbn() != null) {
            book.setIsbn(bookDto.getIsbn());
        }
        if (bookDto.getGenres() != null) {
            book.setGenres(bookDto.getGenres());
        }
        if (bookDto.getAuthors() != null) {
            book.setAuthors(bookDto.getAuthors());
        }
        if (bookDto.getPages() > 0) {
            book.setPages(bookDto.getPages());
        }
        if (bookDto.getLanguage() != null) {
            book.setLanguage(bookDto.getLanguage());
        }
        if (bookDto.getPrice() != null) {
            book.setPrice(bookDto.getPrice());
        }
        if (bookDto.getPublisher() != null) {
            book.setPublisher(bookDto.getPublisher());
        }
        if (bookDto.getPublicationYear() > 0) {
            book.setPublicationYear(bookDto.getPublicationYear());
        }
        if (bookDto.getQuantity() >= 0) {
            book.setQuantity(bookDto.getQuantity());
        }

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
