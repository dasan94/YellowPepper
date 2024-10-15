package org.example.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoreSteps {
    private Map<String, String> orderDetails;
    private Response response;

    @Given("I have the following order details")
    public void i_have_the_following_order_details(DataTable dataTable) {
        orderDetails = dataTable.asMaps(String.class, String.class).get(0); // Obtener el primer mapa de detalles
    }

    @When("I send a POST request to create the order")
    public void i_send_a_post_request_to_create_the_order() {
        String body = String.format(
                "{\"id\": %s, \"petId\": %s, \"quantity\": %s, \"shipDate\": \"%s\", \"status\": \"%s\", \"complete\": %s}",
                orderDetails.get("id"),
                orderDetails.get("petId"),
                orderDetails.get("quantity"),
                orderDetails.get("shipDate"),
                orderDetails.get("status"),
                orderDetails.get("complete")
        );

        response = given().header("Content-type", "application/json")
                .body(body)
                .post("http://localhost:8080/api/v3/store/order");

        response.prettyPrint();
    }

    @Then("the response contains the order id {string}")
    public void the_response_contains_the_order_id(String orderId) {
        response.then().body("id", equalTo(Integer.parseInt(orderId)));
    }

    @Then("the response contains the petId {string}")
    public void the_response_contains_the_petId(String petId) {
        response.then().body("petId", equalTo(Integer.parseInt(petId)));
    }

    @Then("the response contains the order status {string}")
    public void the_response_contains_the_order_status(String status) {
        response.then().body("status", equalTo(status));
    }

    @Given("I have an existing order with id {string}")
    public void i_have_an_existing_order_with_id(String orderId) {
        response = given().get("http://localhost:8080/api/v3/store/order/{orderId}", orderId);
        response.then().statusCode(200);
    }

    @When("I send a GET request to retrieve the order details")
    public void i_send_a_GET_request_to_retrieve_the_order_details() {
        response.prettyPrint();
    }

    @When("I send a DELETE request to remove the order with id {string}")
    public void i_send_a_DELETE_request_to_remove_the_order_with_id(String orderId) {
        response = given().delete("http://localhost:8080/api/v3/store/order/{orderId}", orderId);
        response.then().statusCode(200);
    }

    @When("I send a GET request to retrieve the pet inventory")
    public void i_send_a_GET_request_to_retrieve_the_pet_inventory() {
        response = given().get("http://localhost:8080/api/v3/store/inventory");
        response.prettyPrint();
    }

    @Then("the response contains the available pets count")
    public void the_response_contains_the_available_pets_count() {
        response.then().statusCode(200);
    }

    @And("the response contains a message confirming the order deletion")
    public void the_response_contains_a_message_confirming_the_order_deletion() {
        response.then().statusCode(200);
    }
}
