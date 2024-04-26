package com.samah.bookservice.mapper;

import com.samah.bookservice.dto.BookDto;
import com.samah.bookservice.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public Book BookDtoToBook(BookDto dto) {
        if (dto == null) {
            throw new NullPointerException("The Book DTO should not be null");
        }
        Book Book = new Book();
        Book.setId(dto.getId());
        Book.setDescription(dto.getDescription());
        Book.setTitle(dto.getTitle());
        Book.setAuthors(dto.getAuthors());
        Book.setIsbn(dto.getIsbn());
        Book.setGenres(dto.getGenres());
        Book.setPrice(dto.getPrice());
        Book.setQuantity(dto.getQuantity());
        Book.setPublicationYear(dto.getPublicationYear());
        Book.setPublisher(dto.getPublisher());
        Book.setPages(dto.getPages());
        Book.setLanguage(dto.getLanguage());

        return Book;
    }

    public BookDto BookToBookDto(Book book) {
        if (book == null)
            throw new NullPointerException("The Book should not be null");
        BookDto BookDto = new BookDto(book.getId(), book.getDescription(), book.getTitle(),
                book.getAuthors(), book.getIsbn(), book.getGenres(), book.getPrice(), book.getQuantity(),
                book.getPublicationYear(), book.getPublisher(), book.getLanguage(), book.getPages());
        return BookDto;
    }

}
