package com.samah.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
//@Transactional
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Column(nullable = false)
    private String title;
    private List<String> authors;
    private String isbn;
    private List<String> genres;
    private double price;
    private int quantity;
    private int publicationYear;
    private String publisher;
    private String language;
    private int pages;
}
