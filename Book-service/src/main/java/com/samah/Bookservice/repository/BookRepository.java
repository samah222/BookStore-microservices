package com.samah.Bookservice.repository;

import com.samah.Bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthorContaining(String keyword);
    List<Book> findByIsbnContaining(String keyword);
    List<Book> findByPriceBetween(double min, double max);
    List<Book> findByPublicationYearBetween(int year1, int year2);
    List<Book> findByPublisher(String publisher);
    List<Book> findByAuthorAndPublisher(String publisher);

}
