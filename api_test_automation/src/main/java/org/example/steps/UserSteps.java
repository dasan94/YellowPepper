package org.example.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserSteps {

    private Map<String, String> userdetails;
    private Response response;

    @Given("I have a user with the following details")
    public void i_have_a_user_with_the_following_details(DataTable dataTable) {
        List<Map<String, String>> userDetailsList = dataTable.asMaps(String.class, String.class);
        userdetails = userDetailsList.getFirst();
    }

    @When("I send a POST request to create the user")
    public void i_send_a_POST_request_to_create_the_user() {
        String body = String.format(
                "{\"id\": %s, \"username\": \"%s\", \"firstName\": \"%s\", \"lastName\": \"%s\"" +
                        ", \"email\": \"%s\", \"password\": \"%s\", \"phone\": \"%s\"" +
                        ", \"userStatus\": \"%s\"}",
                userdetails.get("id"),
                userdetails.get("username"),
                userdetails.get("firstName"),
                userdetails.get("lastName"),
                userdetails.get("email"),
                userdetails.get("password"),
                userdetails.get("phone"),
                userdetails.get("userStatus")
        );

        response = given().header("Content-type", "application/json")
                .body(body)
                .post("http://localhost:8080/api/v3/user");

        response.prettyPrint();
    }

    @And("the response contains the username {string}")
    public void the_response_contains_the_username(String username) {
        response.then().body("username", equalTo(username));
    }

    @Given("I have an existing user with username {string}")
    public void i_have_an_existing_user_with_username(String username) {
        if (userdetails == null) {
            userdetails = new HashMap<>();
        }
        userdetails.put("username", username);
    }

    @When("I send a GET request to retrieve the user details")
    public void i_send_a_GET_request_to_retrieve_the_user_details() {
        response = given()
                .get("http://localhost:8080/api/v3/user/{username}", userdetails.get("username"));
        response.prettyPrint();
    }

    @And("the response contains user details")
    public void the_response_contains_user_details() {
        response.then().body("username", equalTo(userdetails.get("username")))
                .body("firstName", equalTo(userdetails.get("firstName")))
                .body("lastName", equalTo(userdetails.get("lastName")))
                .body("email", equalTo(userdetails.get("email")))
                .body("phone", equalTo(userdetails.get("phone")))
                .body("userStatus", equalTo(userdetails.get("userStatus")));
    }

    @And("the response contains the first name {string}")
    public void the_response_contains_the_first_name(String name){
        response.then()
                .body("firstName", equalTo(name));
    }

    @And("the response contains the last name {string}")
    public void the_response_contains_the_last_name(String lastName) {
        response.then()
                .body("lastName", equalTo(lastName));
    }

    @And("I have updated details for the user")
    public void i_have_updated_details_for_the_user(DataTable dataTable) {
        List<Map<String, String>> updatedDetailsList = dataTable.asMaps(String.class, String.class);
        userdetails = new HashMap<>(updatedDetailsList.getFirst());
    }

    @When("I send a PUT request to update the user details")
    public void i_send_a_PUT_request_to_update_the_user_details() {
        if (userdetails.get("username") == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        String body = String.format(
                "{\"id\": %s, \"username\": \"%s\", \"firstName\": \"%s\", \"lastName\": \"%s\"" +
                        ", \"email\": \"%s\", \"password\": \"%s\", \"phone\": \"%s\"" +
                        ", \"userStatus\": \"%s\"}",
                userdetails.get("id"),
                userdetails.get("username"),
                userdetails.get("firstName"),
                userdetails.get("lastName"),
                userdetails.get("email"),
                userdetails.get("password"),
                userdetails.get("phone"),
                userdetails.get("userStatus")
        );

        response = given().header("Content-type", "application/json")
                .body(body)
                .put("http://localhost:8080/api/v3/user/{username}", userdetails.get("username"));

        response.prettyPrint();
    }

    @And("the response contains the email {string}")
    public void the_response_contains_the_email(String email) {
        response.then()
                .body("email", equalTo(email));
    }
}
