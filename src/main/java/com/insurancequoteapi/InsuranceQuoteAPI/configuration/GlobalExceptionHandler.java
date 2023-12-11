package com.insurancequoteapi.InsuranceQuoteAPI.configuration;

import com.insurancequoteapi.InsuranceQuoteAPI.exceptions.InvalidApiKeyException;
import com.insurancequoteapi.InsuranceQuoteAPI.exceptions.PremiumCalculationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PremiumCalculationException.class)
    public ResponseEntity<String> handlePremiumCalculationException(PremiumCalculationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // Or any appropriate status
                .body("Error calculating premium: " + e.getMessage());
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    public ResponseEntity<String> handleInvalidApiKeyException(InvalidApiKeyException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED) // HTTP 401 Unauthorized
                .body("Server error: " + e.getMessage());
    }
}
