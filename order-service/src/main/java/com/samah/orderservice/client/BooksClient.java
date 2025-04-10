package com.samah.orderservice.client;

import com.samah.orderservice.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "books", url = "${application-config.book-service}")
public interface BooksClient {
    @GetMapping("v1/books/{bookId}")
    BookDto getBooksDetails(@PathVariable int bookId);

    @PutMapping("v1/books/{bookId}")
    BookDto updateBookQuantity(@RequestBody BookDto bookDto, @PathVariable int bookId);
}
