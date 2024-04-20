package com.samah.Bookservice.mapper;

import com.samah.Bookservice.dto.BookDto;
import com.samah.Bookservice.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public Book BookDtoToBook(BookDto dto){
        if(dto == null){
            throw new NullPointerException("The Book DTO should not be null");
        }
        Book Book = new Book();
        Book.setId(dto.id());
        Book.setDescription(dto.description());
        Book.setTitle(dto.title());
        Book.setAuthors(dto.authors());
        Book.setIsbn(dto.isbn());
        Book.setGenres(dto.genres());
        Book.setPrice(dto.price());
        Book.setPublicationYear(dto.publicationYear());
        Book.setPublisher(dto.publisher());
        Book.setPages(dto.pages());
        Book.setLanguage(dto.language());

        return Book;
    }

    public BookDto BookToBookDto(Book book){
        BookDto BookDto = new BookDto(book.getId(),book.getDescription(),book.getTitle(),
                book.getAuthors(),book.getIsbn(),book.getGenres(), book.getPrice(),
                book.getPublicationYear(), book.getPublisher(), book.getLanguage(), book.getPages());
        return BookDto;
    }

}
