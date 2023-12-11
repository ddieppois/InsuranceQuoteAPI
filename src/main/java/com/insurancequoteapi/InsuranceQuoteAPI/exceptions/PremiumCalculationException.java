package com.insurancequoteapi.InsuranceQuoteAPI.exceptions;

public class PremiumCalculationException extends Exception {

    public PremiumCalculationException() {
        super("Incorrect factors provided for premium calculation");
    }
}
