package org.example.steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetSteps {

    private Map<String, String> petdetails;
    private Response response;

    @Given("I have a pet with the following details")
    public void i_have_a_pet_with_the_following_details(DataTable dataTable) {
        List<Map<String, String>> petDetailsList = dataTable.asMaps(String.class, String.class);
        petdetails = petDetailsList.getFirst();
    }

    @When("I send a POST request to add the pet")
    public void i_send_a_post_request_to_add_the_pet() {
        String body = String.format(
                "{\"id\": %s, \"name\": \"%s\", \"status\": \"%s\"}",
                petdetails.get("id"),
                petdetails.get("name"),
                petdetails.get("status")
        );

        response = given().header("Content-type", "application/json")
                .body(body)
                .post("http://localhost:8080/api/v3/pet");
        response.prettyPrint();
    }

    @Then("the status code is {int}")
    public void the_status_code_is(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response contains the name {string}")
    public void the_response_contains_the_name(String name){
        response.then().body("name", equalTo(name));
    }

    @When("I send a GET request to retrieve the pet")
    public void i_send_a_GET_request_to_retrieve_the_pet() {
        System.out.println(petdetails.get("id"));
        response = given()
                .get("http://localhost:8080/api/v3/pet/" + petdetails.get("id"));
    }

    @When("I send a PUT request to update the pet")
    public void i_send_a_PUT_request_to_update_the_pet() {
        String body = String.format(
                "{\"id\": %s, \"name\": \"%s\", \"status\": \"%s\"}",
                petdetails.get("id"),
                petdetails.get("name"),
                petdetails.get("status")
        );

        response = given().header("Content-type", "application/json")
                .body(body)
                .put("http://localhost:8080/api/v3/pet");

        System.out.println("Response: ");
        response.prettyPrint();
    }

    @And("the response contains the status {string}")
    public void the_response_contains_the_status(String status) {
        response.then().body("status", equalTo(status));
    }

    @When("I send a DELETE request to remove the pet")
    public void i_send_a_DELETE_request_to_remove_the_pet() {
        response = delete("http://localhost:8080/api/v3/pet/" + petdetails.get("id"));
    }

    @When("I send a GET request to find pets by status {string}")
    public void i_send_a_GET_request_to_find_pets_by_status(String status) {
        response = given()
                .queryParam("status", status)
                .when()
                .get("http://localhost:8080/api/v3/pet/findByStatus");
        response.prettyPrint();
    }

    @And("the response contains pets with status {string}")
    public void the_response_contains_pets_with_status(String status) {
        response.then().body("status", everyItem(equalTo(status)));
    }
}