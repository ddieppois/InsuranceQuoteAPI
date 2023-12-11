# InsuranceQuoteAPI
Spring boot back end api for InsuranceQuote

**How to start the API**: Simply run `mvn spring-boot:run` to start the application.

Endpoint used to calculate the insurance quote for a given user and vehicle.

hostname:http://localhost:8080/insurance-quote

**To be used with InsuranceQuoteWeb or Postman with the following request**

A convenient postman collection is provided in the root directory of the project.

Request body : InsuranceQuoteRequest
```json
{
  "referenceNumber": "AB12",
  "age": 25,
  "email": "test@email.com",
  "fullName": "fullName",
  "birthDate": "birthDate",
  "category": "category",
  "make": "make",
  "model": "model",
  "year": 2022,
  "drivingExperience": "5-10 years",
  "atFaultAccident": "1",
  "claimsNumber": "0",
  "annualMileage": "20,000-30,000 km",
  "previousInsurance": "> 2 years",
  "price": 40000.00
}
```

Response body: InsuranceQuoteResponse
```json
{
    "premium": 1980.0,
    "referenceNumber": "AB12"
}
```

No issues faced during development, only the deploy to the cloud was an issue.