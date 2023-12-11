package com.insurancequoteapi.InsuranceQuoteAPI.exceptions;

public class InvalidApiKeyException extends RuntimeException {
    public InvalidApiKeyException() {
        super("Unauthorized access - wrong configuration.");
    }

    public InvalidApiKeyException(String message) {
        super(message);
    }
}
