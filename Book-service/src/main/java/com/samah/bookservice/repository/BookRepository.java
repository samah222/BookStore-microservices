package com.samah.bookservice.repository;

import com.samah.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    //List<Book> findByAuthorsContaining(List<String> authors);
    List<Book> findByIsbnContaining(String keyword);

    List<Book> findByPriceBetween(double min, double max);

    List<Book> findByPublicationYearBetween(int year1, int year2);

    List<Book> findByPublicationYear(int year);

    List<Book> findByPublicationYearLessThan(int year);

    List<Book> findByAuthorsOrderByPublicationYear(List<String> authors);

    List<Book> findByPublisher(String publisher);

    List<Book> findByAuthorsAndPublisher(List<String> authors, String publisher);

    List<Book> findByLanguage(String language);

}
