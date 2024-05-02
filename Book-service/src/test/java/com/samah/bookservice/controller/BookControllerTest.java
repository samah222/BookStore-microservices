package com.samah.bookservice.controller;

import com.samah.bookservice.dto.BookDto;
import com.samah.bookservice.entity.Book;
import com.samah.bookservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Test
    void getBook() throws Exception{
        BookDto bookdto = new BookDto(1, "Rich dad, poor dad","description", List.of("Author 1"), "ABC",
                List.of("fiction"), new BigDecimal(2000), 1000, 100, "publisher company", "EN",250);

        when(bookService.getBook(1)).thenReturn(bookdto);
        this.mockMvc.perform(get("/v1/books/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"name\":\"Rich dad, poor dad\",\n" +
                        "    \"description\":\"description\",\n" +
                        "    \"title\" : \"Rich dad, poor dad\",\n" +
                        "    \"authors\" : [\"Author 1\"],\n" +
                        "    \"isbn\":\"ABC\",\n" +
                        "    \"genres\" : [\"fiction\"],\n" +
                        "    \"publicationYear\" : 2000,\n" +
                        "    \"price\" : 1000,\n" +
                        "   \"quantity\" : 1000,\n" +
                        "    \"publisher\" : \"publisher company\",\n" +
                        "    \"language\" : \"EN\",\n" +
                        "    \"pages\" : 250\n" +
                        "    \n" +
                        "}"));
    }
}
