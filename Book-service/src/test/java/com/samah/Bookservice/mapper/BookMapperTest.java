package com.samah.Bookservice.mapper;

import com.samah.Bookservice.dto.BookDto;
import com.samah.Bookservice.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {
    Mapper mapper;
    Book book;
    BookDto dto;
    @BeforeEach
    void setup(){
        mapper = new Mapper();
    }
    @Test
    void should_map_bookDto_to_Book() {
        BookDto dto = new BookDto(1,"Java book", "The guide of Learning Java", List.of("samah"),
                "isbn 1",List.of("fiction"), BigDecimal.valueOf(100), 5,2024,"publisher company 1"
                , "English",500) ;
        book = mapper.BookDtoToBook(dto);
        assertEquals(book.getId() ,dto.getId());
        assertEquals(book.getDescription(), dto.getDescription());
        assertEquals(book.getTitle(), dto.getTitle());
        assertEquals(book.getAuthors(), dto.getAuthors());
        assertEquals(book.getGenres(), dto.getGenres());
        assertEquals(book.getIsbn(), dto.getIsbn());
        assertEquals(book.getPrice(), dto.getPrice());
        assertEquals(book.getLanguage(), dto.getLanguage());
        assertEquals(book.getPages(), dto.getPages());
        assertEquals(book.getQuantity(), dto.getQuantity());
        assertEquals(book.getPublicationYear(), dto.getPublicationYear());
        assertEquals(book.getPublisher(), dto.getPublisher());
    }

    @Test
    void should_map_book_to_BookDto() {
        book = new Book(1,"Java book", "The guide of Learning Java", List.of("samah"),
                "isbn 1",List.of("fiction"), BigDecimal.valueOf(100), 5,2024,"publisher company 1"
                , "English",500) ;
        dto = mapper.BookToBookDto(book);
        assertEquals(dto.getId(), book.getId());
        assertEquals(dto.getDescription(), book.getDescription());
        assertEquals(dto.getTitle(), book.getTitle());
        assertEquals(dto.getAuthors(), book.getAuthors());
        assertEquals(dto.getGenres(), book.getGenres());
        assertEquals(dto.getIsbn(), book.getIsbn());
        assertEquals(dto.getPrice(), book.getPrice());
        assertEquals(dto.getLanguage(), book.getLanguage());
        assertEquals(dto.getPages(), book.getPages());
        assertEquals(dto.getQuantity(), book.getQuantity());
        assertEquals(dto.getPublicationYear(), book.getPublicationYear());
        assertEquals(dto.getPublisher(), book.getPublisher());
    }

    @Test
    public void should_throw_Null_Pointer_Exception_when_bookDto_is_null(){
        dto = null;
        var msg = assertThrows(NullPointerException.class, () -> mapper.BookDtoToBook(dto));
        assertEquals(msg.getMessage(),"The Book DTO should not be null");
    }

    @Test
    public void should_throw_Null_Pointer_Exception_when_book_is_null(){
        book = null;
        var msg = assertThrows(NullPointerException.class, () -> mapper.BookToBookDto(book));
        assertEquals(msg.getMessage(),"The Book should not be null");
    }
}