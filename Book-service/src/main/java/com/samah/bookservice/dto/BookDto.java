package com.samah.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String description;
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
