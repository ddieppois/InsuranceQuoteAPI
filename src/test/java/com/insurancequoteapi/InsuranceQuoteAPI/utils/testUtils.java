package com.insurancequoteapi.InsuranceQuoteAPI.utils;

import com.insurancequoteapi.InsuranceQuoteAPI.models.InsuranceQuoteRequest;

public class testUtils {

    public static InsuranceQuoteRequest createTestInsuranceQuoteRequest() {
        return new InsuranceQuoteRequest("AB12",
                25,
                "test@email.com",
                "fullName",
                "birthDate",
                "category","make","model",
                2022,"5-10 years",
                "1","0",
                "20,000-30,000 km","> 2 years",
                40000.00
        );
    }
}
