# price-service

Spring Boot REST service for product pricing queries, using an in-memory H2 database and priority-based price selection.

---

## Requirements

- Java 21  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- H2  
- Maven  
- JUnit 5 / Spring Boot Test / DataJpaTest  

---

## Data model

The `prices` table defines tariffs with date ranges and a priority value to resolve overlaps.  
If multiple tariffs apply for a given date, the one with the highest priority is selected.

---

## Endpoints

| Method | Endpoint | Parameters | Description | Status Codes |
|------|---------|------------|-------------|--------------|
| GET | `/price/applicable-price` | • `brandId`<br>• `productId`<br>• `applicationDate` | Takes brand, product and application date as input and returns the applicable tariff, its date range and the final price to apply. | • `200 OK`<br>• `400 Bad Request`<br>• `404 Not Found` |

---

## Tests

- Controller integration tests (`PriceControllerIT`) reproduce the cases described in the exercise statement using a fixed dataset defined in `data.sql`.
- Repository integration tests are self-contained and rely on automatic transaction rollback to guarantee isolation between tests.
- Unit tests available in `PriceServiceTest` verify the correct filtering logic applied by the service layer.

