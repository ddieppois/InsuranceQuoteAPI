package com.insurancequoteapi.InsuranceQuoteAPI.controllers;

import com.insurancequoteapi.InsuranceQuoteAPI.exceptions.PremiumCalculationException;
import com.insurancequoteapi.InsuranceQuoteAPI.models.InsuranceQuoteRequest;
import com.insurancequoteapi.InsuranceQuoteAPI.models.InsuranceQuoteResponse;
import com.insurancequoteapi.InsuranceQuoteAPI.services.InsuranceQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insurance-quote")
@CrossOrigin(origins = "http://localhost:4200")
public class InsuranceQuoteController {

    @Autowired
    private InsuranceQuoteService insuranceQuoteService;

    @PostMapping
    public ResponseEntity<InsuranceQuoteResponse> calculateInsuranceQuote(@RequestBody InsuranceQuoteRequest insuranceQuoteRequest) throws PremiumCalculationException {
        InsuranceQuoteResponse insuranceQuoteResponse = new InsuranceQuoteResponse(insuranceQuoteService.calculatePremium(insuranceQuoteRequest), insuranceQuoteRequest.referenceNumber());
        return ResponseEntity.ok(insuranceQuoteResponse);
    }
}
