package com.samah.orderservice.controller;

import com.samah.orderservice.service.impl.InfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InfoController.class)
class InfoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    InfoServiceImpl infoServiceImpl;

    @Test
    void shouldReturnAppInfo() throws Exception {
        when(infoServiceImpl.getAppNameAndVersion()).thenReturn("This application is orders and this version: v1");
        this.mockMvc.perform(get("/v1/info")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("This application is orders and this version: v1")));
    }

    @Test
    void getJavaVersion() throws Exception {
        when(infoServiceImpl.getJavaVersion()).thenReturn("java 21.0.2");
        this.mockMvc.perform(get("/v1/java-version")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("java 21.0.2")));
    }
}
