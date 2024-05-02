package com.samah.userservice.controller;

import com.samah.userservice.service.impl.InfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;

@WebMvcTest(InfoController.class)
class InfoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    InfoServiceImpl infoService;

    @Test
    void shouldReturnAppInfo() throws Exception {
        when(infoService.getAppNameAndVersion()).thenReturn("This application is users and this version: v1");
        this.mockMvc.perform(get("/v1/info")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("This application is users and this version: v1")));
    }

    @Test
    void getJavaVersion() throws Exception {
        when(infoService.getJavaVersion()).thenReturn("java 21.0.2");
        this.mockMvc.perform(get("/v1/java-version")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("java 21.0.2")));
    }
}
