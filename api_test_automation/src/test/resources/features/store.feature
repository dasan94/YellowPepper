Feature: Store Management

  Scenario: Create an order for purchasing a pet
    Given I have the following order details
    | id  | petId | quantity | shipDate              | status   | complete |
    | 101 | 12    | 2        | 2024-10-03T00:00:00Z | placed   | true     |
    When I send a POST request to create the order
    And the response contains the order id "101"
    And the response contains the petId "12"
    And the response contains the order status "placed"

  Scenario: Get an order by ID
    Given I have an existing order with id "101"
    When I send a GET request to retrieve the order details
    And the response contains the order id "101"
    And the response contains the petId "12"
    And the response contains the order status "placed"

  Scenario: Delete an order by ID
    Given I have an existing order with id "101"
    When I send a DELETE request to remove the order with id "101"
    Then the response contains a message confirming the order deletion

  Scenario: Get store inventory
    When I send a GET request to retrieve the pet inventory
    And the response contains the available pets count
