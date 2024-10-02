# Remoti API - Automated Testing with Cucumber and RestAssured

## Overview

This project contains automated API tests for the Remoti technical interview, using the following technologies:
- **Cucumber**: For behavior-driven testing (BDD)
- **RestAssured**: For API testing and interacting with REST services
- **JUnit**: For running the test cases
- **Maven**: For managing dependencies and build automation

## Features

- **User Management**: Create, update, and retrieve user details.
- **Store Management**: Manage pet orders, including creating and deleting orders.
- **Pet Management**: Find pets by status and retrieve inventory data.

## Getting Started

### Dependencies
#### Cucumber-JUnit
#### RestAssured
#### JUnit
#### Maven-Cucumber-Reporting

### Install dependencies

```bash
mvn clean install
```

### Running the test

```bash
mvn test verify
```

### Test Reports

```bash
target/cucumber-reports/cucumber-html-reports/
```

### How to Add New Test Scenarios
#### Add the feature scenario: Create a new feature file or update existing ones in src/test/resources/features.
#### Define the steps: Implement step definitions by updating or creating new classes in src/main/java/org/example/steps.
#### Run the tests: Execute the tests to validate your new scenarios.

### Example Scenario

```bash
Scenario: Add a new pet to the inventory
  Given I have a pet with the following details
    | id     | name  | status   |
    | 12345  | Rex   | available|
  When I send a POST request to add the pet
  Then the status code is 200
  And the response contains the name "Rex"
```


### Running Specific Tests

```bash
mvn test -Dcucumber.options="src/test/resources/features/user.feature"
```


### Author
Santiago Arizabaletra – caribaroja@hotmail.com