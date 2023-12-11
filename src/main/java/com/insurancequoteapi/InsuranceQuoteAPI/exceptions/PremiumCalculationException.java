package com.insurancequoteapi.InsuranceQuoteAPI.exceptions;

public class PremiumCalculationException extends Exception {

    public PremiumCalculationException() {
        super("Unable to calculate premium: Please contact our customer service for further assistance.");
    }
}
