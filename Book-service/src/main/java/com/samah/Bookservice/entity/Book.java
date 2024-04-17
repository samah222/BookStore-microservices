package com.samah.Bookservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Transactional
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String title;
    private List<String> authors;
    private String isbn;
    private List<String> genres;
    private double price;
    private int publicationYear;
    private String publisher;
    private String language;
    private int pages;
}
