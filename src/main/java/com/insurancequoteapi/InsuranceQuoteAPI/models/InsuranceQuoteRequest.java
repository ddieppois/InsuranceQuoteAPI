package com.insurancequoteapi.InsuranceQuoteAPI.models;

public record InsuranceQuoteRequest(String referenceNumber, int age, String email, String fullName, String birthDate,
                                    String category, String make, String model, int year, String drivingExperience,
                                    String atFaultAccident, String claimsNumber, String annualMileage,
                                    String previousInsurance, double price) {
}
