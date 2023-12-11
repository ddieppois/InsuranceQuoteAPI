package com.insurancequoteapi.InsuranceQuoteAPI.services;

import com.insurancequoteapi.InsuranceQuoteAPI.exceptions.PremiumCalculationException;
import com.insurancequoteapi.InsuranceQuoteAPI.utils.testUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class InsuranceQuoteServiceTest {

    private InsuranceQuoteService insuranceQuoteService = new InsuranceQuoteService();

    @Test
    void getAgeFactor() throws PremiumCalculationException {
        assertEquals(1.3, insuranceQuoteService.getAgeFactor(24));
        assertEquals(1.0, insuranceQuoteService.getAgeFactor(25));
        assertEquals(0.9, insuranceQuoteService.getAgeFactor(40));
        assertThrows(PremiumCalculationException.class, () -> insuranceQuoteService.getAgeFactor(70));
    }

    @Test
    void getCarValueFactor() throws PremiumCalculationException {
        assertEquals( 0.8, insuranceQuoteService.getCarValueFactor(30000.00, 2020));
        assertEquals(1.0, insuranceQuoteService.getCarValueFactor(40000.00, 2022));

        assertThrows(PremiumCalculationException.class, () -> insuranceQuoteService.getCarValueFactor(350000.00, 2020));
    }

    @Test
    void calculatePremium() throws PremiumCalculationException {
        assertEquals( 1980.0, insuranceQuoteService.calculatePremium(testUtils.createTestInsuranceQuoteRequest()));
    }



}