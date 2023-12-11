package com.insurancequoteapi.InsuranceQuoteAPI.controllers;

import com.insurancequoteapi.InsuranceQuoteAPI.exceptions.InvalidApiKeyException;
import com.insurancequoteapi.InsuranceQuoteAPI.exceptions.PremiumCalculationException;
import com.insurancequoteapi.InsuranceQuoteAPI.models.InsuranceQuoteRequest;
import com.insurancequoteapi.InsuranceQuoteAPI.models.InsuranceQuoteResponse;
import com.insurancequoteapi.InsuranceQuoteAPI.services.InsuranceQuoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/insurance-quote")
@CrossOrigin(origins = "http://localhost:4200")
public class InsuranceQuoteController {

    @Autowired
    private InsuranceQuoteService insuranceQuoteService;

    @Value("${api.security.header.x-api-key}")
    private String baseApiKey;

    @PostMapping
    public ResponseEntity<InsuranceQuoteResponse> calculateInsuranceQuote(
            @RequestHeader(value = "X-API-KEY") String apiKey,
            @RequestBody @Valid InsuranceQuoteRequest insuranceQuoteRequest) throws PremiumCalculationException {
        if (!isApiKeyValid(apiKey)) {
            throw new InvalidApiKeyException();
        }
        InsuranceQuoteResponse insuranceQuoteResponse = new InsuranceQuoteResponse(insuranceQuoteService.calculatePremium(insuranceQuoteRequest), insuranceQuoteRequest.referenceNumber());
        return ResponseEntity.ok(insuranceQuoteResponse);
    }

    private boolean isApiKeyValid(String apiKey) {
        return apiKey.equals(baseApiKey);
    }
}
