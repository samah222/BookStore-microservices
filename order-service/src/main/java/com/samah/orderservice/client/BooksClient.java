package com.samah.orderservice.client;

import com.samah.orderservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Books-service", url = "${application-config.book-service}")
public interface BooksClient {
    @GetMapping("v1/books/{bookId}")
    Book getBooksdetails(@PathVariable int bookId);
}
