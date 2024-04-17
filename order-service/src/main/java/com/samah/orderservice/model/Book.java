package com.samah.orderservice.model;

import lombok.*;

import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    private Integer id;
    private String name;
    private String description;
    private String title;
    private List<String> authors;
    private String isbn;
    private List<String>genres;
    private double price;
    private int publicationYear;
    private String publisher;
    private String language;
    private int pages;
}
