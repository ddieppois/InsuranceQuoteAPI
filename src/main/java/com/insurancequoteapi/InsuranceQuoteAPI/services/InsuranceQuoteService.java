package com.insurancequoteapi.InsuranceQuoteAPI.services;

import com.insurancequoteapi.InsuranceQuoteAPI.exceptions.PremiumCalculationException;
import com.insurancequoteapi.InsuranceQuoteAPI.models.InsuranceQuoteRequest;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Map;

@Service
public class InsuranceQuoteService {

    private static Double BASE_PREMIUM = 2000.00;
    private static Double INVALID_FACTOR = -1.0;

    public Double getAgeFactor(int age) throws PremiumCalculationException {
        if (age < 25) {
            return 1.3;
        } else if (age >= 25 && age < 40) {
            return 1.0;
        } else if (age >= 40 && age < 70) {
            return 0.9;
        } else {
            throw new PremiumCalculationException();
        }
    }

    public Double getCarValueFactor(double price, int carYear) throws PremiumCalculationException {
        int currentYear = Year.now().getValue();
        int age = currentYear - carYear;
        double depreciationRate = 0.0;
        if (age <= 3) {
            depreciationRate = 0.15;
        } else {
            depreciationRate = 0.10;
        }
        double carValue = price;
        for (int i = 0; i < age; i++) {
            carValue = carValue - (carValue * depreciationRate);
        }
        if (carValue < 30000) {
            return 0.8;
        } else if (carValue >= 30000 && carValue < 60000) {
            return 1.0;
        } else if (carValue >= 60000 && carValue < 100000) {
            return 1.2;
        } else if (carValue >= 100000 && carValue < 150000) {
            return 1.5;
        } else if (carValue >= 150000 && carValue < 200000) {
            return 2.0;
        } else {
            throw new PremiumCalculationException();
        }
    }

    private static final Map<String, Double> drivingExperienceFactors = Map.of(
            "0-2 years", 1.5,
            "2-5 years", 1.3,
            "5-10 years", 1.0,
            "10+ years", 0.9
    );

    private static final Map<String, Double> atFaultAccidentFactors = Map.of(
            "0", 1.0,
            "1", 1.1,
            "2-3", 1.3,
            "3+", -1.0
    );

    private static final Map<String, Double> claimsNumberFactors = Map.of(
            "0", 0.9,
            "1", 1.2,
            "2-3", 1.5,
            "3+", -1.0
    );

    private static final Map<String, Double> annualMileageOptions = Map.of(
            "<20,000 km", 0.9,
            "20,000-30,000 km", 1.0,
            "30,000-50,000 km", 1.1,
            ">50,000 km", 1.3
    );

    private static final Map<String, Double> previousInsuranceOptions = Map.of(
            "No previous insurance", 1.2,
            "<= 2 years", 1.1,
            "> 2 years", 1.0
    );

    private List<Double> getFactors(InsuranceQuoteRequest insuranceQuoteRequest) throws PremiumCalculationException {
        double drivingExperienceFactor = drivingExperienceFactors.getOrDefault(insuranceQuoteRequest.drivingExperience(), INVALID_FACTOR);
        double atFaultAccidentFactor = atFaultAccidentFactors.getOrDefault(insuranceQuoteRequest.atFaultAccident(), INVALID_FACTOR);
        double claimsNumberFactor = claimsNumberFactors.getOrDefault(insuranceQuoteRequest.claimsNumber(), INVALID_FACTOR);
        double annualMileageFactor = annualMileageOptions.getOrDefault(insuranceQuoteRequest.annualMileage(), INVALID_FACTOR);
        double previousInsuranceFactor = previousInsuranceOptions.getOrDefault(insuranceQuoteRequest.previousInsurance(), INVALID_FACTOR);
        double carValueFactor = getCarValueFactor(insuranceQuoteRequest.price(), insuranceQuoteRequest.year());
        double ageFactor = getAgeFactor(insuranceQuoteRequest.age());
        if (ageFactor == INVALID_FACTOR
                || carValueFactor == INVALID_FACTOR
                || drivingExperienceFactor == INVALID_FACTOR
                || atFaultAccidentFactor == INVALID_FACTOR
                || claimsNumberFactor == INVALID_FACTOR
                || annualMileageFactor == INVALID_FACTOR
                || previousInsuranceFactor == INVALID_FACTOR) {
            throw new PremiumCalculationException();
        }
        return List.of(ageFactor, carValueFactor, drivingExperienceFactor, atFaultAccidentFactor, claimsNumberFactor, annualMileageFactor, previousInsuranceFactor);
    }

    public Double calculatePremium(InsuranceQuoteRequest insuranceQuoteRequest) throws PremiumCalculationException {
        double finalPremium = BASE_PREMIUM;
        for (double factor : getFactors(insuranceQuoteRequest)) {
            finalPremium *= factor;
        }
        return finalPremium;
    }
}
