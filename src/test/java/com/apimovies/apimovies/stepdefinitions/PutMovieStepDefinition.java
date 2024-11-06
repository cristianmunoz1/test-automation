package com.apimovies.apimovies.stepdefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutMovieStepDefinition {

    private Response response;
    private JSONObject movieRequest;

    @When("I send a PUT request with valid movie data to update movie with id {string}")
    public void iSendAPUTRequestWithValidMovieData(String movieId) throws JSONException {
        movieRequest = new JSONObject();
        movieRequest.put("title", "Movie 3 - Update");
        movieRequest.put("description", "Movie 3 Description - Update");
        movieRequest.put("releaseDate", "2023-01-01T01:12:12");
        movieRequest.put("availableOnDvd", true);

        response = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(movieRequest.toString())
                .when()
                .put("/movies/" + movieId);
    }

    @Then("the movie should be updated successfully")
    public void theMovieShouldBeUpdatedSuccessfully() {
        response.then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Then("the response should contain the updated movie details")
    public void theResponseShouldContainTheUpdatedMovieDetails() throws JSONException {
        response.then()
                .body("title", equalTo(movieRequest.getString("title")))
                .body("description", equalTo(movieRequest.getString("description")))
                .body("releaseDate", equalTo(movieRequest.getString("releaseDate")))
                .body("availableOnDvd", equalTo(movieRequest.getBoolean("availableOnDvd")));
    }

    @When("I send a PUT request with invalid movie data to update movie with id {string}")
    public void iSendAPUTRequestWithInvalidMovieData(String movieId) throws JSONException {
        JSONObject invalidMovieRequest = new JSONObject();
        invalidMovieRequest.put("title", ""); // título vacío
        invalidMovieRequest.put("description", "Movie Description - Update");
        invalidMovieRequest.put("releaseDate", "invalid-date"); // fecha inválida
        invalidMovieRequest.put("availableOnDvd", true);

        response = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(invalidMovieRequest.toString())
                .when()
                .put("/movies/" + movieId);
    }

    @Then("I should receive a bad request error for update")
    public void iShouldReceiveABadRequestErrorForUpdate() {
        response.then()
                .statusCode(400);
    }

    @When("I send a PUT request to update movie with non-existent id {string}")
    public void iSendAPUTRequestToNonExistentMovie(String movieId) throws JSONException {
        movieRequest = new JSONObject();
        movieRequest.put("title", "Movie Update");
        movieRequest.put("description", "Movie Description Update");
        movieRequest.put("releaseDate", "2023-01-01T01:12:12");
        movieRequest.put("availableOnDvd", true);

        response = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(movieRequest.toString())
                .when()
                .put("/movies/" + movieId);
    }

    @Then("I should receive a not found error")
    public void iShouldReceiveANotFoundError() {
        response.then()
                .statusCode(404);
    }
}