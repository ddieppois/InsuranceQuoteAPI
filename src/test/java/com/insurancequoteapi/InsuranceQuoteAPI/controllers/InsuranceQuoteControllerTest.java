package com.insurancequoteapi.InsuranceQuoteAPI.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurancequoteapi.InsuranceQuoteAPI.models.InsuranceQuoteRequest;
import com.insurancequoteapi.InsuranceQuoteAPI.utils.testUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class InsuranceQuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${api.security.header.x-api-key}")
    private String baseApiKey;

    private InsuranceQuoteRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = testUtils.createTestInsuranceQuoteRequest();
    }

    @Test
    void whenValidApiKeyAndRequest_thenReturns200() throws Exception {
        mockMvc.perform(post("/insurance-quote")
                        .header("X-API-KEY", baseApiKey)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.premium").exists())
                .andExpect(jsonPath("$.premium").value(1980.0));
    }

    @Test
    void whenInvalidApiKey_thenReturns401() throws Exception {
        mockMvc.perform(post("/insurance-quote")
                        .header("X-API-KEY", "wrongApiKey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isUnauthorized());
    }

}