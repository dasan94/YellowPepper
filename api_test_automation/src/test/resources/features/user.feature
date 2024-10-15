Feature: User Management

  Scenario: Create a new user
    Given I have a user with the following details
      | id        | username  | firstName | lastName  | email              | password | phone        | userStatus |
      | 123       | user123   | John      | Doe       | john.doe@test.com   | pass123  | 555-555-5555 | 1         |
    When I send a POST request to create the user
    And the response contains the username "user123"

  Scenario: Get user details by username
    Given I have an existing user with username "theUser"
    When I send a GET request to retrieve the user details
    And the response contains the username "theUser"
    And the response contains the first name "John"
    And the response contains the last name "James"

  Scenario: Update user details
    Given I have an existing user with username "theUser"
    And I have updated details for the user
      | username | firstName | lastName  | email               | password |
      | theUser  | Jane      | Smith     | jane.smith@test.com  | newpass  |
    When I send a PUT request to update the user details
    And the response contains the first name "Jane"
    And the response contains the last name "Smith"
    And the response contains the email "jane.smith@test.com"

  Scenario: Update user details
    Given I have an existing user with username "theUser"
    And I have updated details for the user
      | username | firstName | lastName  | email               | password |
      | theUser  | Jane      | Smith     | jane.smith@test.com  | newpass  |
    When I send a PUT request to update the user details
    And the response contains the first name "Jane"
    And the response contains the last name "Smith"
    And the response contains the email "jane.smith@test.com"

  Scenario: Restore data
    Given I have an existing user with username "theUser"
    And I have updated details for the user
      | username | firstName | lastName  | email               | password |
      | theUser  | John      | James     | john.doe@test.com  | pass123  |
    When I send a PUT request to update the user details
    And the response contains the first name "John"
    And the response contains the last name "James"
    And the response contains the email "john.doe@test.com"
