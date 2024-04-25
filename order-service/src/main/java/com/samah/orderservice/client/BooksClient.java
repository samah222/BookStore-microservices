package com.samah.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Books-service", url = "${application-config.book-service}")
public interface BooksClient {
    @GetMapping("v1/books/{bookId}")
    BookDto getBooksDetails(@PathVariable int bookId);

    @PutMapping("v1/books/{bookId}")
    public ResponseEntity<BookDto> updateBookQuantity(@RequestBody BookDto bookDto, @PathVariable int id);
}
