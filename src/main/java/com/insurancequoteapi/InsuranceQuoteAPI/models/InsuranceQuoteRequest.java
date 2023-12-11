package com.insurancequoteapi.InsuranceQuoteAPI.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Valid
public record InsuranceQuoteRequest(@NotNull String referenceNumber, @NotNull @PositiveOrZero int age,
                                    @NotNull @Email String email, @NotNull String fullName, @NotNull String birthDate,
                                    @NotNull String category, @NotNull String make, @NotNull String model,
                                    @NotNull int year, @NotNull String drivingExperience,
                                    @NotNull String atFaultAccident, @NotNull String claimsNumber,
                                    @NotNull String annualMileage,
                                    @NotNull String previousInsurance, @NotNull @PositiveOrZero double price) {
}
