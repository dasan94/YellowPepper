Feature: Validate PET's Endpoints

Scenario: Add a new pet to the inventory
  Given I have a pet with the following details
    | id     | name  | status   |
    | 12345  | Rex   | available|
  When I send a POST request to add the pet
  Then the status code is 200
  And the response contains the name "Rex"

Scenario: Get pet by ID
  Given I have a pet with the following details
    | id     |
    | 5      |
  When I send a GET request to retrieve the pet
  Then the status code is 200
  And the response contains the name "Dog 2"

Scenario: Update a pet's status
  Given I have a pet with the following details
    | id     | name  | status   |
    | 12345  | Rex   | sold     |
  When I send a PUT request to update the pet
  Then the status code is 200
  And the response contains the status "sold"

Scenario: Delete a pet from the inventory
  Given I have a pet with the following details
    | id     |
    | 9      |
  When I send a DELETE request to remove the pet
  Then the status code is 200

Scenario: Find pets by status
  When I send a GET request to find pets by status "available"
  Then the status code is 200
  And the response contains pets with status "available"
