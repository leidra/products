Feature: Retrieving product price
  Scenario: client makes call to GET /prices
    #noinspection CucumberUndefinedStep
    When the client makes a GET request to /api/ecommerce/brands/<brand-id>/products/<product-id>/prices?date=<tariff-date> providing:
      | brand-id  | product-id  | tariff-date            |
      | 1         | 35455       | 2020-06-14T10:00:00Z   |
      | 1         | 35455       | 2020-06-14T16:00:00Z   |
      | 1         | 35455       | 2020-06-14T21:00:00Z   |
      | 1         | 35455       | 2020-06-15T10:00:00Z   |
      | 1         | 35455       | 2020-06-16T21:00:00Z   |
    Then the client receives status code of 200 and the response contains:
      | brand-id  | product-id | tariff-id  | tariff-date-from        | tariff-date-to        | price-value     | price  |
      | 1         | 35455      | 1          | 2020-06-14T00:00:00Z    | 2020-12-31T23:59:59Z  | 35.50           | 35.50€ |
      | 1         | 35455      | 2          | 2020-06-14T15:00:00Z    | 2020-06-14T18:30:00Z  | 25.45           | 25.45€ |
      | 1         | 35455      | 1          | 2020-06-14T00:00:00Z    | 2020-12-31T23:59:59Z  | 35.50           | 35.50€ |
      | 1         | 35455      | 3          | 2020-06-15T00:00:00Z    | 2020-06-15T11:00:00Z  | 30.50           | 30.50€ |
      | 1         | 35455      | 4          | 2020-06-15T16:00:00Z    | 2020-12-31T23:59:59Z  | 35.50           | 35.50€ |
