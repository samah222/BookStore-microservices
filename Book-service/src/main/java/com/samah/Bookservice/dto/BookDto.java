package com.samah.Bookservice.dto;

import java.util.List;

public record BookDto(Integer id, String description, String title, List<String> authors,
                      String isbn, List<String> genres, double price, int publicationYear,
                      String publisher,  String language, int pages) {

}


 
